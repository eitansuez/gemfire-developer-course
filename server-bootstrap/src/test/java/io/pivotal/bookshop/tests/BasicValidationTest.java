package io.pivotal.bookshop.tests;

import io.pivotal.bookshop.buslogic.Loader;
import io.pivotal.bookshop.domain.Book;
import io.pivotal.bookshop.domain.BookOrder;
import io.pivotal.bookshop.domain.Customer;
import io.pivotal.bookshop.domain.InventoryItem;
import org.apache.geode.cache.Region;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:cache-config.xml"})
@ActiveProfiles({"basic"})
public class BasicValidationTest {

  @Resource(name = "Customer")
  private Region<Long, Customer> customerRegion;

  @Resource(name = "Book")
  private Region<Long, Book> bookRegion;

  @Resource(name = "BookOrder")
  private Region<Long, BookOrder> orderRegion;

  @Resource(name = "InventoryItem")
  private Region<Long, InventoryItem> inventoryRegion;

  @Autowired private Loader loader;

  @Before
  public void seedRegions() {
    loader.populateCustomers(customerRegion);
    loader.populateBooks(bookRegion);
    loader.populateBookOrders(orderRegion);
    loader.populateInventory(inventoryRegion);
  }

  @Test
  public void customerKariPowellShouldExist() {
    Customer kari = customerRegion.get(5598L);
    assertThat(kari).isNotNull();
    assertThat(kari.getFirstName()).isEqualTo("Kari");
    assertThat(kari.getLastName()).isEqualTo("Powell");
  }

}
