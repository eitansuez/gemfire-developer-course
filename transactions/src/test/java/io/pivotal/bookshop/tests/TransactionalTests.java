package io.pivotal.bookshop.tests;

import io.pivotal.bookshop.buslogic.TransactionalService;
import io.pivotal.bookshop.domain.Address;
import io.pivotal.bookshop.domain.Customer;
import io.pivotal.bookshop.domain.Order;
import io.pivotal.bookshop.domain.ProductItem;
import org.apache.geode.cache.Region;
import org.apache.geode.cache.TransactionException;
import org.apache.geode.cache.client.ClientCache;
import org.apache.geode.cache.client.ClientCacheFactory;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;

// TODO-01: Open the test harness and observe the two transaction tests to be run
// TODO-07: Ensure the locator & servers have been started, then run the tests, verifying both tests pass.
// TODO-11: Stop & restart the locator & servers and re-run the tests. Verify the output of the listeners.log file
public class TransactionalTests {
  private ClientCache cache;
  private Region<Long, Order> orderRegion;
  private Region<Long, Customer> customerRegion;

  @Before
  public void setUp() throws Exception {
    this.cache = new ClientCacheFactory().create();
    orderRegion = cache.getRegion("Order");
    customerRegion = cache.getRegion("Customer");

    // Force the region data to be removed so log will only show true
    // updates. The region.clear() method doesn't
    // work for clients calling clear() on partitioned regions.
    orderRegion.removeAll(orderRegion.keySetOnServer());
    customerRegion.removeAll(customerRegion.keySetOnServer());

    loadCustomers();
    loadOrders();
  }

  @Test
  public void shouldCommitTransaction() {
    TransactionalService svc = new TransactionalService(cache);

    // Parametrized data for test
    long customerKey = 1001;
    long orderKey = 1001;
    Calendar updatedOrderDate = Calendar.getInstance();
    updatedOrderDate.set(2013, Calendar.APRIL, 25, 0, 0, 0);
    String updatedCustomerPhone = "222-22222-0000";

    svc.updateCustomerAndOrder(customerKey, orderKey, updatedCustomerPhone, updatedOrderDate.getTime());

    // Verify that changes were made in both regions
    Customer cust = customerRegion.get(customerKey);
    Order order = orderRegion.get(orderKey);

    assertThat(cust.getPhoneNumber()).isEqualTo(updatedCustomerPhone);
    assertThat(order.getOrderDate()).isEqualTo(updatedOrderDate.getTime());
  }

  @Test(expected = TransactionException.class)
  public void shouldRollbackTransaction() {
    TransactionalService svc = new TransactionalService(cache);

    // Parametrized data for test
    long customerKey = 1001;
    long orderKey = 1002;
    Calendar updatedOrderDate = Calendar.getInstance();
    updatedOrderDate.set(2013, Calendar.APRIL, 25, 0, 0, 0);
    String updatedCustomerPhone = "222-22222-0000";

    svc.updateCustomerAndOrder(customerKey, orderKey, updatedCustomerPhone, updatedOrderDate.getTime());
  }

  private void loadOrders() {
    Calendar orderDate = Calendar.getInstance();
    orderDate.set(2013, Calendar.DECEMBER, 3, 0, 0, 0);

    Order firstOrder = Order.builder().orderNumber("ORD001")
        .orderDate(orderDate.getTime())
        .item(ProductItem.builder().itemNumber("P001").description("Toy").retailCost(30.5f).build())
        .item(ProductItem.builder().itemNumber("P002").description("Watch").retailCost(60.5f).build())
        .item(ProductItem.builder().itemNumber("P003").description("Pen").retailCost(12.5f).build())
        .customerNumber("C001")
        .totalPrice(103.5f)
        .build();
    orderRegion.put(1001L, firstOrder);

    Order otherOrder = Order.builder().orderNumber("ORD002")
        .orderDate(orderDate.getTime())
        .item(ProductItem.builder().itemNumber("P004").description("Shirt").retailCost(60.5f).build())
        .item(ProductItem.builder().itemNumber("P005").description("Socks").retailCost(12.5f).build())
        .customerNumber("C002")
        .totalPrice(73.0f)
        .build();
    orderRegion.put(1002L, otherOrder);
  }

  private void loadCustomers() {
    Customer cust1 = Customer.builder().customerNumber("C001")
        .firstName("Lula").lastName("Wax").phoneNumber("123 654-543")
        .address(Address.builder().addressLine1("123 Main St")
            .city("Topeka").state("KS").postalCode("50505")
            .country("US").addressTag("HOME").build()).build();
    customerRegion.put(1001L, cust1);

    Customer cust2 = Customer.builder().customerNumber("C002")
        .firstName("Tom").lastName("Mcginns").phoneNumber("123 456-789")
        .address(Address.builder().addressLine1("123 Main St")
            .city("San Francisco").state("CA").postalCode("50505")
            .country("US").addressTag("HOME").build()).build();
    customerRegion.put(1002L, cust2);

    Customer cust3 = Customer.builder().customerNumber("C003")
        .firstName("Peter").lastName("Fernandez").phoneNumber("123 456-789")
        .address(Address.builder().addressLine1("123 Main St")
            .city("San Francisco").state("CA").postalCode("50505")
            .country("US").addressTag("HOME").build()).build();
    customerRegion.put(1003L, cust3);
  }

}
