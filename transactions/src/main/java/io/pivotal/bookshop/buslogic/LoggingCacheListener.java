package io.pivotal.bookshop.buslogic;

import lombok.extern.log4j.Log4j2;
import org.apache.geode.cache.Declarable;
import org.apache.geode.cache.EntryEvent;
import org.apache.geode.cache.util.CacheListenerAdapter;

import java.util.Properties;

@Log4j2
public class LoggingCacheListener<K,V> extends CacheListenerAdapter<K,V> implements Declarable {

	@Override
	public void init(Properties properties) {
		// nothing to do
	}

	@Override
	public void afterUpdate(EntryEvent<K, V> event) {
		log.info("afterUpdate:   Entry updated for key: " + event.getKey() +
				    "\n               Old value: " + event.getOldValue() + 
				    "\n               New Value: " + event.getNewValue());
	}

}
