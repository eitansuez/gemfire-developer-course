package io.pivotal.bookshop.domain;

import org.apache.geode.cache.Declarable;
import org.apache.geode.cache.EntryOperation;
import org.apache.geode.cache.PartitionResolver;

import java.io.Serializable;
import java.util.Properties;

public class CustomerPartitionResolver implements PartitionResolver<OrderKey, BookOrder>, Serializable, Declarable {

  private static final long serialVersionUID = 1L;

  @Override
  public String getName() {
    return getClass().getName();
  }

  @Override
  public Serializable getRoutingObject(EntryOperation<OrderKey, BookOrder> eo) {
    // TODO-03: Implement getRoutingObject so the customer number is used as the basis for 'routing'
    return eo.getKey().getCustomerNumber();
  }

  //from Declarable
  @Override
  public void init(Properties p) {
    // Auto generated - we're not using properties so, nothing to do
  }

  @Override
  public void close() {
    //nothing to do when the Region closes
  }

}
