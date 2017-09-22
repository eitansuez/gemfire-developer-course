package io.pivotal.bookshop.buslogic;

import io.pivotal.bookshop.domain.BookOrder;
import io.pivotal.bookshop.domain.BookOrderItem;
import lombok.extern.log4j.Log4j2;
import org.apache.geode.cache.Region;
import org.apache.geode.cache.client.ClientCache;
import org.apache.geode.cache.client.ClientCacheFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;

// TODO: make this test more meaningful
// consider: updated the price of a low order to something over $100 and vice versa
@Log4j2
public class DataProducer {

  public static void main(String[] args) throws Exception {

    ClientCache cache = new ClientCacheFactory()
        .set("cache-xml-file", "clientWorkerCache.xml")
        .create();

    Region<Long, BookOrder> orderRegion = cache.getRegion("BookOrder");

    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    System.out.println("Press enter to populate an order over $100");
    bufferedReader.readLine();
    addAnotherOrder(orderRegion);

    System.out.println("Press enter to populate an order less than $100");
    bufferedReader.readLine();
    addLowOrder(orderRegion);

    cache.close();
  }


  private static void addLowOrder(Region<Long, BookOrder> orderRegion) {
    BookOrder ord1 = new BookOrder(18049, new Date(), (float) 5.99, new Date(), new ArrayList<>(), 5543, (float) 80.94);
    ord1.addOrderItem(BookOrderItem.builder()
        .orderLine(1).itemNumber(123).quantity(5).discount(100f).build());
    orderRegion.put(18049L, ord1);
    log.info("Added: " + ord1);
  }

  private static void addAnotherOrder(Region<Long, BookOrder> orderRegion) {
    BookOrder ord1 = new BookOrder(18009, new Date(), (float) 5.99, new Date(), new ArrayList<>(), 5543, (float) 180.94);
    ord1.addOrderItem(BookOrderItem.builder()
      .orderLine(1).itemNumber(123).quantity(5).build());
    orderRegion.put(18009L, ord1);
    log.info("Added: " + ord1);
  }

}
