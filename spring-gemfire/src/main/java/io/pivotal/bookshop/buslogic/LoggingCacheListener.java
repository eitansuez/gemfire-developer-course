package io.pivotal.bookshop.buslogic;

import lombok.extern.log4j.Log4j2;
import org.apache.geode.cache.Declarable;
import org.apache.geode.cache.EntryEvent;
import org.apache.geode.cache.RegionEvent;
import org.apache.geode.cache.util.CacheListenerAdapter;

import java.util.Properties;

@Log4j2
public class LoggingCacheListener<K, V> extends CacheListenerAdapter<K, V> implements Declarable {

  public void afterCreate(EntryEvent<K, V> e) {
    log.info("Received afterCreate event for entry: " + e.getKey() + ", " + e.getNewValue());
  }

  public void afterUpdate(EntryEvent<K, V> e) {
    log.info("Received afterUpdate event for entry: " + e.getKey() + ", " + e.getNewValue());
  }

  public void afterDestroy(EntryEvent<K, V> e) {
    log.info("Received afterDestroy event for entry: " + e.getKey());
  }

  public void afterInvalidate(EntryEvent<K, V> e) {
    log.info("Received afterInvalidate event for entry: " + e.getKey());
  }

  public void afterRegionLive(RegionEvent e) {
    log.info("Received afterRegionLive event, sent to durable clients after the server has finished replaying stored events.");
  }

  public void init(Properties props) {
  }

}