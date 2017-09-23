package io.pivotal.bookshop.buslogic;

import io.pivotal.bookshop.domain.Customer;
import io.pivotal.bookshop.fixtures.Fixtures;
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
    assertThat(customerRegion).isNotNull();
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
    Fixtures.someCustomers().forEach(customer -> {
      customerRegion.put(customer.getCustomerNumber(), customer);
      log.info("Inserted a customer: " + customer);
    });
  }


}