package io.pivotal.bookshop.buslogic;

import io.pivotal.bookshop.domain.Address;
import io.pivotal.bookshop.domain.Customer;
import lombok.extern.log4j.Log4j2;
import org.apache.geode.cache.Region;
import org.apache.geode.cache.client.ClientCache;
import org.apache.geode.cache.client.ClientCacheFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Log4j2
public class VerifyCluster {

  private ClientCache clientCache;
  private Region<Long, Customer> customerRegion;

  @Before
  public void setup() {
    clientCache = new ClientCacheFactory().create();
    customerRegion = clientCache.getRegion("Customer");
  }

  @After
  public void teardown() {
    clientCache.close();
  }

  @Test
  public void shouldWriteCustomersToRegion() {
    populateCustomers();
    assertThat(customerRegion.getAll(customerRegion.keySetOnServer()).size()).isEqualTo(3);
  }

  private void populateCustomers() {
    Customer cust1 = Customer.builder().customerNumber(5598)
        .firstName("Kari").lastName("Powell")
        .address(Address.builder().postalCode("44444").build())
        .bookOrder(17699L).bookOrder(18009L).bookOrder(18049L)
        .build();

    assertThat(customerRegion).isNotNull();
    customerRegion.put(5598L, cust1);
    log.info("Inserted a customer: " + cust1);

    Customer cust2 = Customer.builder().customerNumber(5543)
        .firstName("Lula").lastName("Wax")
        .address(Address.builder().postalCode("12345").build())
        .bookOrder(17699L)
        .build();
    customerRegion.put(5543L, cust2);
    log.info("Inserted a customer: " + cust2);

    Customer cust3 = Customer.builder().customerNumber(6024)
        .firstName("Trenton").lastName("Garcia")
        .address(Address.builder().postalCode("88888").build()).build();

    customerRegion.put(6024L, cust3);
    log.info("Inserted a customer: " + cust3);
  }


}