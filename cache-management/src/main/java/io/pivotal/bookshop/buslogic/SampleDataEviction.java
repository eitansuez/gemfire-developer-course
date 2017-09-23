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
public class SampleDataEviction {

  public static void main(String[] args) throws InterruptedException {
    try ( Cache cache = new CacheFactory()
          .set("log-level", "info")
          .set("cache-xml-file", "cluster.xml")
          .create() ) {

      Region<Long, Customer> customerRegion = cache.getRegion("Customer");

      printRegionContents(customerRegion);
      populateCustomerRegion(customerRegion);
      insertOneMoreCustomer(customerRegion);
      printRegionContents(customerRegion);
    }

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