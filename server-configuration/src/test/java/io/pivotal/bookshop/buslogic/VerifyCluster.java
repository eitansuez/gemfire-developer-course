package io.pivotal.bookshop.buslogic;

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
    Customer cust1 = new Customer(5598, "Kari", "Powell", "44444");
    cust1.addOrder(17699);
    cust1.addOrder(18009);
    cust1.addOrder(18049);
    assertThat(customerRegion).isNotNull();
    customerRegion.put(5598L, cust1);
    log.info("Inserted a customer: " + cust1);

    Customer cust2 = new Customer(5543, "Lula", "Wax", "12345");
    cust2.addOrder(17699);
    customerRegion.put(5543L, cust2);
    log.info("Inserted a customer: " + cust2);

    Customer cust3 = new Customer(6024, "Trenton", "Garcia", "88888");
    customerRegion.put(6024L, cust3);
    log.info("Inserted a customer: " + cust3);
  }


}