package io.pivotal.bookshop.buslogic;

import io.pivotal.bookshop.domain.*;
import lombok.extern.log4j.Log4j2;
import org.apache.geode.cache.Region;
import org.apache.geode.cache.client.ClientCache;
import org.apache.geode.cache.client.ClientCacheFactory;

import java.util.Date;

@Log4j2
public class DataLoader {

  // TODO-06: Run this class to load Customer and BookOrder entries.
  public static void main(String[] args) {

    try (ClientCache cache = new ClientCacheFactory().create()) {
      populateCustomers(cache);
      populateBookOrders(cache);
    }

  }

  // TODO-05: Observe the values of the keys used for both Customer entries and BookOrder entries below.
  private static void populateCustomers(ClientCache cache) {
    Region<Long, Customer> customers = cache.getRegion("Customer");
    log.info("Got the Customer Region: " + customers);

    Customer cust1 = Customer.builder().customerNumber(5598)
        .firstName("Kari").lastName("Powell")
        .address(Address.builder().postalCode("44444").build())
        .bookOrder(17600L)
      .build();
    customers.put(cust1.getCustomerNumber(), cust1);
    log.info("Inserted a customer: " + cust1);

    Customer cust2 = Customer.builder().customerNumber(5542)
        .firstName("Lula").lastName("Wax")
        .address(Address.builder().postalCode("12345").build())
      .build();
    customers.put(cust2.getCustomerNumber(), cust2);
    log.info("Inserted a customer: " + cust2);

    Customer cust3 = Customer.builder().customerNumber(6024)
        .firstName("Trenton").lastName("Garcia")
        .address(Address.builder().postalCode("88888").build())
        .bookOrder(17700L)
      .build();
    customers.put(cust3.getCustomerNumber(), cust3);
    log.info("Inserted a customer: " + cust3);
  }

  private static void populateBookOrders(ClientCache cache) {
    Region<OrderKey, BookOrder> orders = cache.getRegion("BookOrder");
    // Order for Kari Powell for book: A Treatise of Treatises
    OrderKey key1 = new OrderKey(5598, 17600);
    BookOrder order1 = BookOrder.builder().orderNumber(17600).orderDate(new Date())
        .shippingCost(5.99f).shipDate(new Date())
        .customerNumber(5598).totalPrice(40.98f)
        .orderItem(BookOrderItem.builder().orderLine(1).itemNumber(123).build())
      .build();
    orders.put(key1, order1);

    // Order for Lula Wax   book: A Treatise of Treatises & Clifford the Big Red Dog
    OrderKey key2 = new OrderKey(6024, 17700);
    BookOrder order2 = BookOrder.builder().orderNumber(17700).orderDate(new Date())
        .shippingCost(5.99f).shipDate(new Date())
        .customerNumber(6024).totalPrice(52.97f)
        .orderItem(BookOrderItem.builder().orderLine(1).itemNumber(123).build())
        .orderItem(BookOrderItem.builder().orderLine(2).itemNumber(456).build())
      .build();
    orders.put(key2, order2);
  }

}
