package io.pivotal.bookshop.buslogic;

import io.pivotal.bookshop.domain.Book;
import lombok.extern.log4j.Log4j2;
import org.apache.geode.cache.Region;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class BookLoader {

  public void populateBooks(Region<Long, Book> bookRegion) {
    Book book = Book.builder()
        .itemNumber(123L)
        .author("Daisy Mae West")
        .title("A Treatise of Treatises")
        .description("Run on sentences and drivel on all things mundane")
        .yearPublished(2011)
        .retailCost(34.99f)
        .build();

    addBook(book, bookRegion);

    Book book2 = Book.builder()
        .itemNumber(456L)
        .author("Clarence Meeks")
        .title("Clifford the Big Red Dog")
        .description("A book about a dog")
        .yearPublished(1971)
        .retailCost(11.99f)
        .build();

    addBook(book2, bookRegion);

    Book book3 = Book.builder()
        .itemNumber(789L)
        .author("Jim Heavisides")
        .title("Operating Systems: An Introduction")
        .description("Theoretical information about the structure of Operating Systems")
        .yearPublished(2011)
        .retailCost(59.99f)
        .build();

    addBook(book3, bookRegion);
  }

  private void addBook(Book book, Region<Long, Book> bookRegion) {
    bookRegion.put(book.getItemNumber(), book);
    log.info("Inserted book: " + book);
  }


}
