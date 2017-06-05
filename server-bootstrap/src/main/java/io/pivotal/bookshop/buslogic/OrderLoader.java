package io.pivotal.bookshop.buslogic;

import io.pivotal.bookshop.domain.BookOrder;
import io.pivotal.bookshop.domain.BookOrderItem;
import io.pivotal.bookshop.domain.InventoryItem;
import lombok.extern.log4j.Log4j2;
import org.apache.geode.cache.Region;
import org.apache.geode.cache.client.ClientCache;
import org.apache.geode.cache.client.ClientCacheFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;

@Component
@Log4j2
public class OrderLoader {

  public void populateBookOrders(Region<Long, BookOrder> orderRegion) {
    // Order for Kari Powell for book: A Treatise of Treatises
    BookOrder order1 = new BookOrder(17699, new Date(), (float) 5.99, new Date(), new ArrayList<>(), 5598, (float) 40.98);
    order1.addOrderItem(new BookOrderItem(1, 123, 1, (float) 0));
    orderRegion.put(17699L, order1);
    log.info("Inserted a book order: " + order1);

    // Order for Lula Wax   book: A Treatise of Treatises & Clifford the Big Red Dog
    BookOrder order2 = new BookOrder(17700, new Date(), (float) 5.99, new Date(), new ArrayList<>(), 5543, (float) 52.97);
    order2.addOrderItem(new BookOrderItem(1, 123, 1, (float) 0));
    order2.addOrderItem(new BookOrderItem(2, 456, 1, (float) 0));
    orderRegion.put(17700L, order2);
    log.info("Inserted a book order: " + order2);
  }

  public void populateInventory(Region<Long, InventoryItem> inventoryRegion) {

    InventoryItem item1 = new InventoryItem(123, (float) 12.50, (float) 34.99, 12, "BookRUs", "Seattle");
    inventoryRegion.put(123L, item1);
    log.info("Inserted an inventory item: " + item1);

    InventoryItem item2 = new InventoryItem(456, (float) 12.50, (float) 11.99, 1, "BookRUs", "Seattle");
    inventoryRegion.put(456L, item2);
    log.info("Inserted an inventory item: " + item2);

  }

}
