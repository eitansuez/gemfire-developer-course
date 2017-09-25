package io.pivotal.bookshop.buslogic;

import lombok.extern.log4j.Log4j2;
import org.apache.geode.cache.query.CqEvent;
import org.apache.geode.cache.query.CqListener;

@Log4j2
public class SimpleCQListener implements CqListener {

  @Override
  public void close() {
    log.info("SimpleCQListener:Received Close Event");
  }

  @Override
  public void onError(CqEvent event) {
    log.info("SimpleCQListener:Received onError event");
    log.info("SimpleCQListener:Throwable: " + event.getThrowable());
  }

  @Override
  public void onEvent(CqEvent event) {
    // TODO-06: Implement functionality in this method to print out various items on the CqEvent object to stdout
    // Examples: the key, new value, the operation that triggered the event, etc.
    log.info("received a cq event!  key is: "+event.getKey()+"; new value is: "+event.getNewValue());
    log.info("base operation: "+event.getBaseOperation());
    log.info("query operation: "+event.getQueryOperation());

  }

}

