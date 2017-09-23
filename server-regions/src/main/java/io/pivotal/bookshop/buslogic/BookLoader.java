package io.pivotal.bookshop.buslogic;

import io.pivotal.bookshop.domain.Book;
import io.pivotal.bookshop.fixtures.Fixtures;
import lombok.extern.log4j.Log4j2;
import org.apache.geode.cache.Region;
import org.apache.geode.cache.client.ClientCache;
import org.apache.geode.cache.client.ClientCacheFactory;

@Log4j2
public class BookLoader {

  public static void main(String[] args) {

    try (ClientCache cache = new ClientCacheFactory().create()) {
      Region<Long, Book> bookRegion = cache.getRegion("Book");

      Fixtures.someBooks().forEach(book -> {
        bookRegion.put(book.getItemNumber(), book);
        log.info("Inserted book: " + book);
      });
    }
  }

}
