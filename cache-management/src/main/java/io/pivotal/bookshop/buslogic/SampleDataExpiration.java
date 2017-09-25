package io.pivotal.bookshop.buslogic;

import io.pivotal.bookshop.domain.Address;
import io.pivotal.bookshop.domain.Customer;
import lombok.extern.log4j.Log4j2;
import org.apache.geode.cache.Cache;
import org.apache.geode.cache.CacheFactory;
import org.apache.geode.cache.Region;

import java.util.Arrays;

import static io.pivotal.bookshop.buslogic.CustomerLoader.populateCustomerRegion;

@Log4j2
public class SampleDataExpiration {

  public static void main(String[] args) throws InterruptedException {
    Cache cache = new CacheFactory()
        .set("log-level", "info")
        .set("cache-xml-file", "cluster.xml")
        .create();

    Region<Long , Customer> customerRegion = cache.getRegion("Customer");

    // TODO-06a: call CustomerLoader.populateCustomerRegion() to populate the customer region with sample data
    populateCustomerRegion(customerRegion);

    // TODO-06b: Show the contents by calling retrieveAndPrintRegionData for the customer region
    retrieveAndPrintRegionData(customerRegion);

    log.info("Before the idle time expiration, access and update one entry each...\n");

    // TODO-06c: Get the expiration time by calling the getExpirationTime() method and use that value to calculate
    //          a sleep time. You want to sleep perhaps 1 second less than the configured idle time so you have time
    //          to modify some entries before they expire.
    int seconds = getExpirationTime(customerRegion);
    Thread.sleep(1000 * ( seconds - 1 ) );

    // TODO-06d: Make a call to access a customer (5540) and update customer 5541. These methods have been written for you.
    accessCustomerFromRegion(customerRegion, 5540L);
    updateCustomer(customerRegion, 5541);


    // TODO-06e: Perform another sleep for perhaps 1/2 the idle time

    Thread.sleep(2000);

    log.info("After the expiration timeout, the cache contains:\n");

    // TODO-06f: Show the contents of the region again
    retrieveAndPrintRegionData(customerRegion);

    log.info("Closing the cache and disconnecting.");
    cache.close();
  }

  private static int getExpirationTime(Region<Long, Customer> customerRegion) {
    // TODO-03: Add call to get the expiration time from the customer region and return that value
    return customerRegion.getAttributes().getEntryIdleTimeout().getTimeout();
  }

  private static void accessCustomerFromRegion(Region<Long, Customer> customerRegion, long key) {
    Customer cust = customerRegion.get(key);
    log.info("Accessed customer, " + cust.getFirstName() + "  data !!");
  }

  private static void updateCustomer(Region<Long, Customer> customerRegion, long customerKey) {
    // TODO-04: Add code to modify a Customer entry per the key supplied and store it back in the region.
    //          Tip: Try just adding a new address

    Customer customer = customerRegion.get(customerKey);
    Address address = Address.builder().addressLine1("123 Some St")
        .city("Austin")
        .state("TX")
        .postalCode("77777")
        .country("USA")
        .phoneNumber("333 333-3333")
        .addressTag("home")
        .build();
    customer.addAddress(address);
    customerRegion.put(customerKey, customer);

    log.info("************ Updated customer data ****************\n");
  }


  /**
   * TODO-05: Review this code.
   * The goal is to retrieve the entries from the region supplied and
   * print out their details
   *
   * @param region The region to print entries for
   */
  private static void retrieveAndPrintRegionData(Region<?, ?> region) {
    Object[] keys = region.keySet().toArray();
    Arrays.sort(keys);
    for (Object key : keys) {
      log.info("    " + key + " => " + region.get(key));
    }
  }

}
