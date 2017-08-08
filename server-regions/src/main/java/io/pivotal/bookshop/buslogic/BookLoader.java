package io.pivotal.bookshop.buslogic;

import io.pivotal.bookshop.domain.Book;
import lombok.extern.log4j.Log4j2;
import org.apache.geode.cache.Region;
import org.apache.geode.cache.client.ClientCache;
import org.apache.geode.cache.client.ClientCacheFactory;

@Log4j2
public class BookLoader {

  public static void main(String[] args) {

    try (ClientCache cache = new ClientCacheFactory().create()) {
      Region<Long, Book> bookRegion = cache.getRegion("Book");

//      Book book = Book.builder()
//          .itemNumber(123)
//          .description("Run on sentences and drivel on all things mundane")
//          .retailCost(34.99f)
//          .yearPublished(2011)
//          .author("Daisy Mae West")
//          .title("A Treatise of Treatises")
//          .build();

      Book book = new Book(123,
          "Run on sentences and drivel on all things mundane",
          34.99f,
          2011,
          "Daisy Mae West",
          "A Treatise of Treatises");

      addBook(bookRegion, book);

      Book book2 = new Book(
          456,
          "A book about a dog",
          11.99f,
          1971,
          "Clarence Meeks",
          "Clifford the Big Red Dog");
      addBook(bookRegion, book2);

      Book book3 = new Book(
          789,
          "Theoretical information about the structure of Operating Systems",
          59.99f,
          2011,
          "Jim Heavisides",
          "Operating Systems: An Introduction");
      addBook(bookRegion, book3);
    }
  }

  private static void addBook(Region<Long, Book> bookRegion, Book book) {
    Long key = book.getItemNumber();
    bookRegion.put(key, book);
    log.info("Inserted a book: "+book);
  }


}
