package io.pivotal.bookshop;

import io.pivotal.bookshop.domain.Address;
import io.pivotal.bookshop.domain.Customer;
import lombok.extern.log4j.Log4j2;
import org.apache.geode.cache.Region;
import org.apache.geode.cache.client.ClientCache;
import org.apache.geode.cache.client.ClientCacheFactory;

@Log4j2
public class SimpleClientWriter {

  public static void main(String[] args) throws InterruptedException {
    ClientCache cache = new ClientCacheFactory().create();

    Region<Long, Customer> customerRegion = cache.getRegion("Customer");

    Address address = Address.builder()
        .addressLine1("123 Some St")
        .city("Austin").state("TX").postalCode("77777")
        .country("USA").phoneNumber("512 333-4444").addressTag("Home Address").build();

    Customer c = Customer.builder().customerNumber(1001L)
        .firstName("John").lastName("Doe")
        .address(address).build();

    customerRegion.put(1001L, c);

  }

}
