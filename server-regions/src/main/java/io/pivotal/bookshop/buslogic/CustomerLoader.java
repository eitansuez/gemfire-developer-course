package io.pivotal.bookshop.buslogic;

import io.pivotal.bookshop.domain.Customer;
import lombok.extern.log4j.Log4j2;
import org.apache.geode.cache.Region;
import org.apache.geode.cache.client.ClientCache;
import org.apache.geode.cache.client.ClientCacheFactory;

@Log4j2
public class CustomerLoader {

  public static void main(String[] args) {

    try (ClientCache cache = new ClientCacheFactory().create()) {
      Region<Long, Customer> customerRegion = cache.getRegion("Customer");

      Customer cust1 = new Customer(5598L, "Kari", "Powell", "44444");
      cust1.addOrder(17699L);
      cust1.addOrder(18009L);
      cust1.addOrder(18049L);
      addCustomer(customerRegion, cust1);

      Customer cust2 = new Customer(5543L, "Lula", "Wax", "12345");
      cust2.addOrder(17699L);
      addCustomer(customerRegion, cust2);

      Customer cust3 = new Customer(6024L, "Trenton", "Garcia", "88888");
      addCustomer(customerRegion, cust3);
    }
  }

  private static void addCustomer(Region<Long, Customer> customerRegion, Customer customer) {
    long key = customer.getCustomerNumber();
    customerRegion.put(key, customer);
    log.info("Inserted a customer: " + customer);
  }

}
