package io.pivotal.bookshop.buslogic;

import io.pivotal.bookshop.domain.Address;
import io.pivotal.bookshop.domain.Customer;
import lombok.extern.log4j.Log4j2;
import org.apache.geode.cache.Region;
import org.apache.geode.cache.client.ClientCache;
import org.apache.geode.cache.client.ClientCacheFactory;

// TODO-06: Execute this class to load Customer entries to the cache server.
@Log4j2
public class CustomerLoader {

  public static void main(String[] args) {

    try ( ClientCache cache = new ClientCacheFactory().create() ) {
      Region<Long, Customer> customerRegion = cache.getRegion("Customer");
      populateCustomers(customerRegion);
    }

  }

  private static void populateCustomers(Region<Long, Customer> customerRegion) {
    Customer cust1 = Customer.builder().customerNumber(5598)
        .firstName("Kari").lastName("Powell")
        .address(Address.builder().postalCode("44444").build())
        .bookOrder(17699L).bookOrder(18009L).bookOrder(18049L)
      .build();
    customerRegion.put(5598L, cust1);
    log.info("Inserted a customer: " + cust1);

    Customer cust2 = Customer.builder().customerNumber(5543)
        .firstName("Lula").lastName("Wax")
        .address(Address.builder().postalCode("12345").build())
        .bookOrder(17699L)
      .build();
    customerRegion.put(5543L, cust2);
    log.info("Inserted a customer: " + cust2);

    Customer cust3 = Customer.builder().customerNumber(6024)
        .firstName("Trenton").lastName("Garcia")
        .address(Address.builder().postalCode("88888").build())
      .build();
    customerRegion.put(6024L, cust3);
    log.info("Inserted a customer: " + cust3);
  }
}
