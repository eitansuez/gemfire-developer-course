package io.pivotal.bookshop.buslogic;

import io.pivotal.bookshop.domain.Address;
import io.pivotal.bookshop.domain.Book;
import io.pivotal.bookshop.domain.Customer;
import lombok.extern.log4j.Log4j2;
import org.apache.geode.cache.Region;
import org.apache.geode.cache.client.ClientCache;
import org.apache.geode.cache.client.ClientCacheFactory;
import org.springframework.stereotype.Component;


@Component
@Log4j2
public class CustomerLoader {

  public void populateCustomers(Region<Long, Customer> customerRegion) {
    Customer cust1 = new Customer(5598, "Kari", "Powell", "44444");
    cust1.addOrder(18009);
    cust1.addOrder(18049);
    cust1.addOrder(17699);
    cust1.setPrimaryAddress(new Address("123 Main St.", null, null, "Topeka", "KS", "50505", "US", "423-555-3322", "HOME"));
    customerRegion.put(5598L, cust1);
    log.info("Inserted a customer: " + cust1);

    Customer cust2 = new Customer(5543, "Lula", "Wax", "12345");
    cust2.addOrder(17700);
    cust2.setPrimaryAddress(new Address("123 Main St.", null, null, "Topeka", "KS", "50505", "US", "423-555-3322", "HOME"));
    customerRegion.put(5543L, cust2);
    log.info("Inserted a customer: " + cust2);

    Customer cust3 = new Customer(6024, "Trenton", "Garcia", "88888");
    cust3.setPrimaryAddress(new Address("123 Main St.", null, null, "San Francisco", "CA", "50505", "US", "423-555-3322", "HOME"));
    customerRegion.put(6024L, cust3);
    log.info("Inserted a customer: " + cust3);
  }


}
