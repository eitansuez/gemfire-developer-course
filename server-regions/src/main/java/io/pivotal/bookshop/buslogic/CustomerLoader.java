package io.pivotal.bookshop.buslogic;

import io.pivotal.bookshop.domain.Address;
import io.pivotal.bookshop.domain.Customer;
import io.pivotal.bookshop.fixtures.Fixtures;
import lombok.extern.log4j.Log4j2;
import org.apache.geode.cache.Region;
import org.apache.geode.cache.client.ClientCache;
import org.apache.geode.cache.client.ClientCacheFactory;

@Log4j2
public class CustomerLoader {

  public static void main(String[] args) {

    try (ClientCache cache = new ClientCacheFactory().create()) {
      Region<Long, Customer> customerRegion = cache.getRegion("Customer");

      Fixtures.someCustomers().forEach(customer -> {
        customerRegion.put(customer.getCustomerNumber(), customer);
        log.info("Inserted a customer: " + customer);
      });

    }
  }

}
