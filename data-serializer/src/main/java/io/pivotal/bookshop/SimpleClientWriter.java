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

    Customer c = new Customer(1001L, "John", "Doe");
    Address address = new Address("123 Some St", "", "", "Austin", "TX", "77777", "USA", "512.333.4444", "Home Address");
    c.setPrimaryAddress(address);

    customerRegion.put(1001L, c);

  }

}
