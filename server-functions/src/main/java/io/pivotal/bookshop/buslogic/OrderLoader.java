package io.pivotal.bookshop.buslogic;

import io.pivotal.bookshop.domain.BookOrder;
import io.pivotal.bookshop.domain.BookOrderItem;
import org.apache.geode.cache.Region;
import org.apache.geode.cache.client.ClientCache;
import org.apache.geode.cache.client.ClientCacheFactory;

import java.util.ArrayList;
import java.util.Date;

// TODO-10: Start locator and two servers. Then Run this OrderLoader class
public class OrderLoader {

  public static void main(String[] args) {
    ClientCache cache = new ClientCacheFactory().create();

    populateBookOrders(cache);

    cache.close();
  }

  private static void populateBookOrders(ClientCache cache) {
    Region<Long, BookOrder> orders = cache.getRegion("BookOrder");
    // Order for Kari Powell for book: A Treatise of Treatises
    BookOrder order1 = new BookOrder(17699, new Date(), (float) 5.99, new Date(), new ArrayList<>(), 5598, (float) 40.98);
    order1.addOrderItem(new BookOrderItem(1, 123, 1, (float) 0));
    orders.put(17699L, order1);

    // Order for Lula Wax   book: A Treatise of Treatises & Clifford the Big Red Dog
    BookOrder order2 = new BookOrder(17700, new Date(), (float) 5.99, new Date(), new ArrayList<>(), 5543, (float) 52.97);
    order2.addOrderItem(new BookOrderItem(1, 123, 1, (float) 0));
    order2.addOrderItem(new BookOrderItem(2, 456, 1, (float) 0));
    orders.put(17700L, order2);
  }

}
