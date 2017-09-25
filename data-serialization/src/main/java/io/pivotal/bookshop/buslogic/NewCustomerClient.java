package io.pivotal.bookshop.buslogic;

import io.pivotal.bookshop.domain.Customer;
import lombok.extern.log4j.Log4j2;
import org.apache.geode.cache.Region;
import org.apache.geode.cache.client.ClientCache;
import org.apache.geode.cache.client.ClientCacheFactory;

@Log4j2
public class NewCustomerClient {

  public static void main(String[] args) {
    ClientCache cache = new ClientCacheFactory().create();
    Region<Long, Customer> customerRegion = cache.getRegion("Customer");
    log.info("Got the Customer Region: " + customerRegion);

    // TODO-08: Add the code to create a new customer, including setting the new field, telephoneNumber.
    // Insert it into the cache with key 9999.
    Customer customer = Customer.builder()
        .customerNumber(9999L)
        .firstName("John")
        .lastName("Doe")
        .telephoneNumber("512 333-4444")
        .build();
    customerRegion.put(9999L, customer);


    // TODO-09: Add code to fetch the Customer entry for key 9999 and print it out.
    Customer c = customerRegion.get(9999L);
    System.out.println(c);


    cache.close();
  }

}
