package io.pivotal.bookshop.buslogic;

import io.pivotal.bookshop.domain.Customer;
import io.pivotal.bookshop.domain.Order;
import org.apache.geode.cache.CacheTransactionManager;
import org.apache.geode.cache.Region;
import org.apache.geode.cache.client.ClientCache;

import java.util.Date;

public class TransactionalService {

  private CacheTransactionManager tx;
  private Region<Long, Customer> customerRegion;
  private Region<Long, Order> orderRegion;

  public TransactionalService(ClientCache cache) {
    customerRegion = cache.getRegion("Customer");
    orderRegion = cache.getRegion("Order");

    // TODO-02: Initialize the reference to the CacheTransactionManager
    tx = cache.getCacheTransactionManager();

  }

  public void updateCustomerAndOrder(long customerKey, long orderKey, String updatedCustomerPhone, Date updatedOrderDate) {

    try {
      tx.begin();

      Customer c = customerRegion.get(customerKey);
      Order order = orderRegion.get(orderKey);

      c.setPhoneNumber(updatedCustomerPhone);
      order.setOrderDate(updatedOrderDate);

      customerRegion.put(customerKey, c);
      orderRegion.put(orderKey, order);

      tx.commit();
    } catch (RuntimeException ex) {
      tx.rollback();
      throw ex;
    }
    // TODO-03: Use a try/catch block to implement transactional operations to fetch a customer given the customer key,
    //          Order given the orderKey and make updates to the appropriate fields, save each and commit the work, rolling back on any exceptions.

    // TODO-03a: Begin the transaction

    // TODO-03b: Fetch the customer and order using keys passed in and update with values from updatedCustomerPhone and updatedOrderDate

    // TODO-03c: Save the customer and order objects back using the same keys used to fetch them

    // TODO-03d: Commit the transaction

    // TODO-04: Catch any exceptions that may be thrown, roll back the transaction and re-throw the exception

  }

}
