package io.pivotal.bookshop.buslogic;

import io.pivotal.bookshop.domain.Address;
import io.pivotal.bookshop.domain.Customer;
import lombok.extern.log4j.Log4j2;
import org.apache.geode.cache.Cache;
import org.apache.geode.cache.CacheFactory;
import org.apache.geode.cache.Region;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Log4j2
public class SampleDataExpiration {

  public static void main(String[] args) throws InterruptedException {
    Cache cache = new CacheFactory()
        .set("log-level", "info")
        .set("cache-xml-file", "cluster.xml")
        .create();

    Region<Long , Customer> customerRegion = cache.getRegion("Customer");

    // TODO-06a: call populateCustomerRegion() to populate the customer region with sample data

    // TODO-06b: Show the contents by calling retrieveAndPrintRegionData for the customer region

    log.info("Before the idle time expiration, access and update one entry each...\n");

    // TODO-06c: Get the expiration time by calling the getExpirationTime() method and use that value to calculate
    //          a sleep time. You want to sleep perhaps 1 second less than the configured idle time so you have time
    //          to modify some entries before they expire.

    // TODO-06d: Make a call to access a customer (5540) and update customer 5541. These methods have been written for you.

    // TODO-06e: Perform another sleep for perhaps 1/2 the idle time

    log.info("After the expiration timeout, the cache contains:\n");

    // TODO-06f: Show the contents of the region again

    log.info("Closing the cache and disconnecting.");
    cache.close();
  }

  private static int getExpirationTime(Region<Long, Customer> customerRegion) {
    // TODO-03: Add call to get the expiration time from the customer region and return that value
    return 0;
  }

  private static void accessCustomerFromRegion(Region<Long, Customer> customerRegion, long key) {
    Customer cust = customerRegion.get(key);
    log.info("Accessed customer, " + cust.getFirstName() + "  data !!");
  }

  private static void updateCustomer(Region<Long, Customer> customerRegion, long customerKey) {
    // TODO-04: Add code to modify a Customer entry per the key supplied and store it back in the region.
    //          Tip: Try just adding a new address

    log.info("************ Updated customer data ****************\n");
  }

  private static void populateCustomerRegion(Region<Long, Customer> customerRegion) {
    Map<Long, Customer> customerMap = new HashMap<>();
    Customer cust1 = new Customer(5540, "Lula", "Wax");
    cust1.addAddress(new Address("123 Main St.", null, null, "Topeka",
        "KS", "50505", "US", "423-555-3322", "HOME"));
    cust1.addOrder(17699);
    customerMap.put(5540L, cust1);

    Customer cust2 = new Customer(5541, "Tom", "Mcginns");
    cust2.addAddress(new Address("123 Main St.", null, null,
        "San Fransisco", "CA", "50505", "US", "423-555-3322", "HOME"));
    cust2.addOrder(17699);
    customerMap.put(5541L, cust2);

    Customer cust3 = new Customer(5542, "Peter", "Fernandez");
    cust3.addAddress(new Address("123 Main St.", null, null,
        "San Fransisco", "CA", "50505", "US", "423-555-3322", "HOME"));
    cust3.addOrder(17699);
    customerMap.put(5542L, cust3);

    Customer cust4 = new Customer(5543, "Jenny", "Tsai");
    cust4.addAddress(new Address("123 Main St.", null, null, "Topeka",
        "CA", "50505", "US", "423-555-3322", "HOME"));
    cust4.addOrder(17699);
    customerMap.put(5543L, cust4);

    customerRegion.putAll(customerMap);
    log.info("************ Server 1 : Loaded customers data ****************\n");
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
