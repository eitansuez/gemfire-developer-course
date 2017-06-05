package io.pivotal.bookshop.buslogic;

import io.pivotal.bookshop.domain.Book;
import lombok.extern.log4j.Log4j2;
import org.apache.geode.cache.Region;
import org.apache.geode.cache.client.ClientCache;
import org.apache.geode.cache.client.ClientCacheFactory;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class BookLoader {

  public void populateBooks(Region<Long, Book> bookRegion) {
    Book book = new Book(123, "Run on sentences and drivel on all things mundane",
        (float) 34.99, 2011, "Daisy Mae West", "A Treatise of Treatises");
    bookRegion.put(123L, book);
    log.info("Inserted a book: " + book);

    Book book2 = new Book(456, "A book about a dog",
        (float) 11.99, 1971, "Clarence Meeks", "Clifford the Big Red Dog");
    bookRegion.put(456L, book2);
    log.info("Inserted a book: " + book2);

    Book book3 = new Book(789, "Theoretical information about the structure of Operating Systems",
        (float) 59.99, 2011, "Jim Heavisides", "Operating Systems: An Introduction");
    bookRegion.put(789L, book3);
    log.info("Inserted a book: " + book3);
  }


}
