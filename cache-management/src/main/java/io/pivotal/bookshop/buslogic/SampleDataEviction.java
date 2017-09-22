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
    Customer cust1 = Customer.builder().customerNumber(5540)
        .firstName("Lula").lastName("Wax")
        .address(Address.builder()
            .addressLine1("123 Main St")
            .city("Topeka").state("KS").postalCode("50505")
            .country("US").phoneNumber("423 555-3322").addressTag("HOME")
            .build())
        .bookOrder(17699L)
        .build();
    customer.put(5540L, cust1);

    Customer cust2 = Customer.builder().customerNumber(5541)
        .firstName("Tom").lastName("Mcginns")
        .address(Address.builder().addressLine1("123 Main St")
            .city("San Francisco").state("CA").postalCode("50505")
            .country("US").phoneNumber("423 555-3322").addressTag("HOME")
            .build())
        .bookOrder(17699L)
        .build();
    customer.put(5541L, cust2);

    Customer cust3 = Customer.builder().customerNumber(5542)
        .firstName("Peter").lastName("Fernandez")
        .address(Address.builder().addressLine1("123 Main St")
            .city("San Francisco").state("CA").postalCode("50505")
            .country("US").phoneNumber("423 555-3322").addressTag("HOME")
            .build())
        .bookOrder(17699L)
        .build();

    customer.put(5542L, cust3);

    Customer cust4 = Customer.builder().customerNumber(5543)
        .firstName("Jenny").lastName("Tsai")
        .address(Address.builder().addressLine1("123 Main St")
            .city("Topeka").state("CA").postalCode("50505")
            .country("US").phoneNumber("423 555-3322").addressTag("HOME")
            .build())
        .bookOrder(17699L)
        .build();
    customer.put(5543L, cust4);

    customerRegion.putAll(customer);
    log.info("************ Loaded customers data ****************");
  }

  private static void insertOneMoreCustomer(Region<Long, Customer> customerRegion) {
    Customer cust5 = Customer.builder().customerNumber(5544)
        .firstName("Fifth").lastName("Customer")
        .address(Address.builder().addressLine1("123 Main St")
            .city("Topeka").state("CA").postalCode("50505")
            .country("US").phoneNumber("423 555-3322").addressTag("HOME")
            .build())
        .bookOrder(17699L)
        .build();
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