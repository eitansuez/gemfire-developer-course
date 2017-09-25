package io.pivotal.bookshop.buslogic;

import io.pivotal.bookshop.domain.BookOrder;
import io.pivotal.bookshop.domain.BookOrderItem;
import org.apache.geode.cache.Region;
import org.apache.geode.cache.client.ClientCache;
import org.apache.geode.cache.client.ClientCacheFactory;

import java.util.Date;

/*
 * This is a fallback class that was used early on to bootstrap the BookOrder data. It presumed you had a locator and server(s) started.
 * In general though, the preferred approach is to run the server-bootstrap start script to start the server processes. That way, this works
 * as a stand alone when a client-only course is defined.
 *
 */
public class OrderLoader {

  public static void main(String[] args) {
    try (ClientCache cache = new ClientCacheFactory().create()) {
      populateBookOrders(cache);
    }
  }

  private static void populateBookOrders(ClientCache cache) {
    Region<Long, BookOrder> orders = cache.getRegion("BookOrder");
    // Order for Kari Powell for book: A Treatise of Treatises
    BookOrder order1 = BookOrder.builder().orderNumber(17699).orderDate(new Date())
        .shippingCost(5.99f).shipDate(new Date())
        .customerNumber(5598).totalPrice(40.98f)
        .build();
    order1.getOrderItems().add(
        BookOrderItem.builder().orderLine(1).itemNumber(123).build()
      );
    orders.put(17699L, order1);

    // Order for Lula Wax   book: A Treatise of Treatises & Clifford the Big Red Dog
    BookOrder order2 = BookOrder.builder().orderNumber(17700).orderDate(new Date())
        .shippingCost(5.99f).shipDate(new Date())
        .customerNumber(5543).totalPrice(52.97f)
        .build();
    order2.getOrderItems().add(BookOrderItem.builder().orderLine(1).itemNumber(123).build());
    order2.getOrderItems().add(BookOrderItem.builder().orderLine(2).itemNumber(456).build());
    orders.put(17700L, order2);
  }


}
