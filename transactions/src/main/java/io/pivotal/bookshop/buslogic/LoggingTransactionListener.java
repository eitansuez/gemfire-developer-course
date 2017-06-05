package io.pivotal.bookshop.buslogic;

import lombok.extern.log4j.Log4j2;
import org.apache.geode.cache.Declarable;
import org.apache.geode.cache.util.TransactionListenerAdapter;

import java.util.Properties;

@Log4j2
public class LoggingTransactionListener extends TransactionListenerAdapter implements Declarable {

  @Override
  public void init(Properties properties) {
    // nothing to do
  }

  // TODO-08: Add an afterCommit method that overrides the adapter method. Write a logger message that notes when a transaction is committed.
  //          Write code that will get the list of events and print the key, old value and new value for each (you can use the code from the
  //          LoggingCacheListener as a guide).


  // TODO-09: Add an afterRollback method and use the method to log that the rollback occurred.


}
