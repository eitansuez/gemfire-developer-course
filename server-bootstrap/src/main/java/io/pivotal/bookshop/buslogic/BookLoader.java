package io.pivotal.bookshop.buslogic;

import io.pivotal.bookshop.domain.Book;
import lombok.extern.log4j.Log4j2;
import org.apache.geode.cache.Region;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class BookLoader {

  public void populateBooks(Region<Long, Book> bookRegion) {
    Book book = new Book(123,
        "Run on sentences and drivel on all things mundane",
        34.99f,
        2011,
        "Daisy Mae West",
        "A Treatise of Treatises"
        );
    addBook(book, bookRegion);

    Book book2 = new Book(
        456,
        "A book about a dog",
        11.99f,
        1971,
        "Clarence Meeks",
        "Clifford the Big Red Dog"
        );
    addBook(book2, bookRegion);

    Book book3 = new Book(
        789,
        "Theoretical information about the structure of Operating Systems",
        59.99f,
        2011,
        "Jim Heavisides",
        "Operating Systems: An Introduction"
        );
    addBook(book3, bookRegion);
  }

  private void addBook(Book book, Region<Long, Book> bookRegion) {
    bookRegion.put(book.getItemNumber(), book);
    log.info("Inserted book: " + book);
  }


}
