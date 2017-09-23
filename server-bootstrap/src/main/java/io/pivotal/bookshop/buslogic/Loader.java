package io.pivotal.bookshop.buslogic;

import io.pivotal.bookshop.domain.Book;
import io.pivotal.bookshop.domain.BookOrder;
import io.pivotal.bookshop.domain.Customer;
import io.pivotal.bookshop.domain.InventoryItem;
import io.pivotal.bookshop.fixtures.Fixtures;
import lombok.extern.log4j.Log4j2;
import org.apache.geode.cache.Region;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class Loader {

  public void populateBooks(Region<Long, Book> bookRegion) {
    Fixtures.someBooks().forEach(book -> {
      bookRegion.put(book.getItemNumber(), book);
      log.info("Inserted book: " + book);
    });
  }

  public void populateBookOrders(Region<Long, BookOrder> orderRegion) {
    Fixtures.someOrders().forEach(order -> {
          orderRegion.put(order.getOrderNumber(), order);
          log.info("Inserted a book order: " + order);
        }
    );
  }

  public void populateInventory(Region<Long, InventoryItem> inventoryRegion) {
    Fixtures.someInventoryItems().forEach(inventoryItem -> {
      inventoryRegion.put(inventoryItem.getItemNumber(), inventoryItem);
      log.info("Inserted an inventory item: " + inventoryItem);
    });
  }

  public void populateCustomers(Region<Long, Customer> customerRegion) {
    Fixtures.someCustomers().forEach(customer -> {
      customerRegion.put(customer.getCustomerNumber(), customer);
      log.info("Inserted a customer: " + customer);
    });
  }

}
