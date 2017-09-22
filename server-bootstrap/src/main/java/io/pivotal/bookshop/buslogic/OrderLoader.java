package io.pivotal.bookshop.buslogic;

import io.pivotal.bookshop.domain.BookOrder;
import io.pivotal.bookshop.domain.BookOrderItem;
import io.pivotal.bookshop.domain.InventoryItem;
import lombok.extern.log4j.Log4j2;
import org.apache.geode.cache.Region;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Log4j2
public class OrderLoader {

  public void populateBookOrders(Region<Long, BookOrder> orderRegion) {

    BookOrder order1 = BookOrder.builder()
        .orderNumber(17699L)
        .orderDate(new Date())
        .totalPrice(40.98f)
        .shipDate(new Date())
        .shippingCost(5.99f)
        .customerNumber(5598L) // Kari Powell
        .orderItem(BookOrderItem.builder()
            .orderLine(1)
            .itemNumber(123) // A Treatise of Treatises
            .quantity(1)
            .discount(0f)
            .build())
        .build();

    orderRegion.put(17699L, order1);
    log.info("Inserted a book order: " + order1);

    BookOrder order2 = BookOrder.builder()
        .orderNumber(17700L)
        .orderDate(new Date())
        .totalPrice(52.97f)
        .shipDate(new Date())
        .shippingCost(5.99f)
        .customerNumber(5543L) // Lula Wax
        .orderItem(BookOrderItem.builder()
            .orderLine(1)
            .itemNumber(123) // A Treatise of Treatises
            .quantity(1)
            .discount(0f)
            .build())
        .orderItem(BookOrderItem.builder()
            .orderLine(2)
            .itemNumber(456) // Clifford the Big Red Dog
            .quantity(1)
            .discount(0f)
            .build())
        .build();
    orderRegion.put(17700L, order2);
    log.info("Inserted a book order: " + order2);
  }

  public void populateInventory(Region<Long, InventoryItem> inventoryRegion) {

    InventoryItem item1 = InventoryItem.builder().itemNumber(123)
        .costToXYZ(12.5f).priceToCustomer(34.99f)
        .quantityInStock(12)
        .vendor("BooksRUs").location("Seattle").build();
    inventoryRegion.put(123L, item1);
    log.info("Inserted an inventory item: " + item1);

    InventoryItem item2 = InventoryItem.builder().itemNumber(456)
        .costToXYZ(12.50f).priceToCustomer(11.99f)
        .quantityInStock(1)
        .vendor("BooksRUs").location("Seattle").build();
    inventoryRegion.put(456L, item2);
    log.info("Inserted an inventory item: " + item2);

  }

}
