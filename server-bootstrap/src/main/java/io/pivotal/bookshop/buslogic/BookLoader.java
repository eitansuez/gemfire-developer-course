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
    Book book = Book.builder()
        .itemNumber(123)
        .description("Run on sentences and drivel on all things mundane")
        .retailCost(34.99f)
        .yearPublished(2011)
        .author("Daisy Mae West")
        .title("A Treatise of Treatises")
        .build();
    addBook(book, bookRegion);

    Book book2 = Book.builder()
        .itemNumber(456)
        .description("A book about a dog")
        .retailCost(11.99f)
        .yearPublished(1971)
        .author("Clarence Meeks")
        .title("Clifford the Big Red Dog")
        .build();
    addBook(book2, bookRegion);

    Book book3 = Book.builder()
        .itemNumber(789)
        .description("Theoretical information about the structure of Operating Systems")
        .retailCost(59.99f)
        .yearPublished(2011)
        .author("Jim Heavisides")
        .title("Operating Systems: An Introduction")
        .build();
    addBook(book3, bookRegion);
  }

  private void addBook(Book book, Region<Long, Book> bookRegion) {
    bookRegion.put(book.getItemNumber(), book);
    log.info("Inserted book: " + book);
  }


}
