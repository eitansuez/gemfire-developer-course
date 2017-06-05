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
public class SampleDataEviction {

  public static void main(String[] args) throws InterruptedException {
    Cache cache = new CacheFactory()
        .set("log-level", "info")
        .set("cache-xml-file", "cluster.xml")
        .create();

    Region<Long, Customer> customerRegion = cache.getRegion("Customer");

    printRegionContents(customerRegion);

    populateCustomer(customerRegion);

    insertOneMoreCustomer(customerRegion);

    printRegionContents(customerRegion);

    cache.close();
  }

  private static void populateCustomer(Region<Long, Customer> customerRegion) {
    Map<Long, Customer> customer = new HashMap<>();
    Customer cust1 = new Customer(5540, "Lula", "Wax");
    cust1.addAddress(new Address("123 Main St.", null, null, "Topeka",
        "KS", "50505", "US", "423-555-3322", "HOME"));
    cust1.addOrder(17699);
    customer.put(5540L, cust1);

    Customer cust2 = new Customer(5541, "Tom", "Mcginns");
    cust2.addAddress(new Address("123 Main St.", null, null,
        "San Fransisco", "CA", "50505", "US", "423-555-3322", "HOME"));
    cust2.addOrder(17699);
    customer.put(5541L, cust2);

    Customer cust3 = new Customer(5542, "Peter", "Fernandez");
    cust3.addAddress(new Address("123 Main St.", null, null,
        "San Fransisco", "CA", "50505", "US", "423-555-3322", "HOME"));
    cust3.addOrder(17699);
    customer.put(5542L, cust3);

    Customer cust4 = new Customer(5543, "Jenny", "Tsai");
    cust4.addAddress(new Address("123 Main St.", null, null, "Topeka",
        "CA", "50505", "US", "423-555-3322", "HOME"));
    cust4.addOrder(17699);
    customer.put(5543L, cust4);

    customerRegion.putAll(customer);
    log.info("************ Loaded customers data ****************");
  }

  private static void insertOneMoreCustomer(Region<Long, Customer> customerRegion) {
    Customer cust5 = new Customer(5544, "Fifth", "Customer");
    cust5.addAddress(new Address("123 Main St.", null, null, "Topeka",
        "CA", "50505", "US", "423-555-3322", "HOME"));
    cust5.addOrder(17699);
    customerRegion.put(5544L, cust5);
    log.info("************ Inserted one more customer data ****************");
  }

  private static void printRegionContents(Region<?, ?> region) {
    Object[] keys = region.keySet().toArray();
    Arrays.sort(keys);
    for (Object key : keys) {
      log.info("    " + key + " => " + region.get(key));
    }
  }

}