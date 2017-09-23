package io.pivotal.bookshop.buslogic;

import io.pivotal.bookshop.domain.BookOrder;
import io.pivotal.bookshop.domain.BookOrderItem;
import lombok.extern.log4j.Log4j2;
import org.apache.geode.cache.Region;
import org.apache.geode.cache.client.ClientCache;
import org.apache.geode.cache.client.ClientCacheFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Date;

// TODO: make this test more meaningful
// consider: updated the price of a low order to something over $100 and vice versa
@Log4j2
public class DataProducer {

  public static void main(String[] args) throws Exception {

    try (ClientCache cache = new ClientCacheFactory()
        .set("cache-xml-file", "clientWorkerCache.xml")
        .create()) {

      Region<Long, BookOrder> orderRegion = cache.getRegion("BookOrder");

      BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
      System.out.println("Press enter to populate an order over $100");
      bufferedReader.readLine();
      addAnotherOrder(orderRegion);

      System.out.println("Press enter to populate an order less than $100");
      bufferedReader.readLine();
      addLowOrder(orderRegion);

    }

  }


  private static void addLowOrder(Region<Long, BookOrder> orderRegion) {
    BookOrder ord1 = BookOrder.builder()
        .orderNumber(18049).orderDate(new Date())
        .shippingCost(5.99f).shipDate(new Date())
        .customerNumber(5543).totalPrice(80.94f)
        .orderItem(BookOrderItem.builder()
            .orderLine(1).itemNumber(123).quantity(5).discount(100f).build())
        .build();
    orderRegion.put(18049L, ord1);
    log.info("Added: " + ord1);
  }

  private static void addAnotherOrder(Region<Long, BookOrder> orderRegion) {
    BookOrder ord1 = BookOrder.builder()
        .orderNumber(18009).orderDate(new Date())
        .shippingCost(5.99f).shipDate(new Date())
        .customerNumber(5543).totalPrice(180.94f)
        .orderItem(BookOrderItem.builder()
            .orderLine(1).itemNumber(123).quantity(5).build())
        .build();
    orderRegion.put(18009L, ord1);
    log.info("Added: " + ord1);
  }

}
