package io.pivotal.bookshop.buslogic;

import io.pivotal.bookshop.domain.Address;
import io.pivotal.bookshop.domain.Book;
import io.pivotal.bookshop.domain.Customer;
import lombok.extern.log4j.Log4j2;
import org.apache.geode.cache.Region;
import org.apache.geode.cache.client.ClientCache;
import org.apache.geode.cache.client.ClientCacheFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Log4j2
public class CustomerLoader {

  public void populateCustomers(Region<Long, Customer> customerRegion) {

    Address address1 = Address.builder()
        .addressLine1("123 Main St.")
        .city("Topeka")
        .state("KS")
        .postalCode("50505")
        .country("US")
        .phoneNumber("(423) 555-3322")
        .addressTag("HOME")
        .build();

    Customer cust1 = Customer.builder()
        .customerNumber(5598)
        .firstName("Kari")
        .lastName("Powell")
        .primaryAddress(address1)
        .bookOrders(Arrays.asList(18009L, 18049L, 17699L))
        .build();

    customerRegion.put(5598L, cust1);
    log.info("Inserted a customer: " + cust1);

    Address address2 = Address.builder()
        .addressLine1("123 Main St.")
        .city("Topeka")
        .state("KS")
        .postalCode("50505")
        .country("US")
        .phoneNumber("(423) 555-3322")
        .addressTag("HOME")
        .build();

    Customer cust2 = Customer.builder()
        .customerNumber(5543)
        .firstName("Lula")
        .lastName("Wax")
        .primaryAddress(address2)
        .bookOrder(17700L)
        .build();

    customerRegion.put(5543L, cust2);
    log.info("Inserted a customer: " + cust2);

    Address address3 = Address.builder()
      .addressLine1("123 Main St")
        .city("San Francisco")
        .state("CA")
        .postalCode("50505")
        .country("US")
        .phoneNumber("(423) 555-3322")
        .addressTag("HOME")
        .build();

    Customer cust3 = Customer.builder()
        .customerNumber(6024L)
        .firstName("Trenton")
        .lastName("Garcia")
        .primaryAddress(address3)
        .build();

    customerRegion.put(6024L, cust3);
    log.info("Inserted a customer: " + cust3);
  }


}
