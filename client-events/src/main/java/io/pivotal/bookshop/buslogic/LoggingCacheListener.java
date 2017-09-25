package io.pivotal.bookshop.buslogic;

import org.apache.geode.cache.Declarable;
import org.apache.geode.cache.EntryEvent;
import org.apache.geode.cache.RegionEvent;
import org.apache.geode.cache.util.CacheListenerAdapter;

import java.util.Properties;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LoggingCacheListener<K, V> extends CacheListenerAdapter<K, V> implements Declarable {

  private Logger logger = Logger.getLogger(LoggingCacheListener.class.getName());

  // TODO-04a: Implement the init function such that you fetch the property 'filename' as a string and call initializeLogger() with it.
  public void init(Properties props) {
    String filename = props.getProperty("filename");
    initializeLogger(filename);
  }

  private void initializeLogger(String filename) {
    FileHandler fh;
    logger.setLevel(Level.INFO);
    try {
      fh = new FileHandler(filename, true);
      fh.setFormatter(new SimpleFormatter());
      logger.addHandler(fh);
    } catch (Exception e) {
      logger.warning(e.getMessage());
      e.printStackTrace();
    }
  }

  // TODO-04b: Implement afterCreate functionality to log the key and new value to the logger as an info type log entry
  //          Optionally, add log statements or just output to the console for other events (ex afterUpdate, afterDestroy)
  @Override
  public void afterCreate(EntryEvent<K, V> e) {
    logger.info("entry created, key is: "+e.getKey()+"; value is: "+e.getNewValue());
  }

  @Override
  public void afterUpdate(EntryEvent<K, V> e) {
    logger.info("Received afterUpdate event for entry: " + e.getKey() + ", " + e.getNewValue());
  }

  @Override
  public void afterDestroy(EntryEvent<K, V> e) {
    logger.info("Received afterDestroy event for entry: " + e.getKey());
  }

  public void afterInvalidate(EntryEvent<K, V> e) {
    logger.info("Received afterInvalidate event for entry: " + e.getKey());
  }

  public void afterRegionLive(RegionEvent e) {
    logger.info("Received afterRegionLive event, sent to durable clients after \nthe server has finished replaying stored events.");
  }
}