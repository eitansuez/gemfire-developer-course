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

      Book book = Book.builder()
          .itemNumber(123)
          .description("Run on sentences and drivel on all things mundane")
          .retailCost(34.99f)
          .yearPublished(2011)
          .author("Daisy Mae West")
          .title("A Treatise of Treatises")
          .build();
      addBook(bookRegion, book);

      Book book2 = Book.builder()
          .itemNumber(456)
          .description("A book about a dog")
          .retailCost(11.99f)
          .yearPublished(1971)
          .author("Clarence Meeks")
          .title("Clifford the Big Red Dog")
          .build();
      addBook(bookRegion, book2);

      Book book3 = Book.builder()
          .itemNumber(789)
          .description("Theoretical information about the structure of Operating Systems")
          .retailCost(59.99f)
          .yearPublished(2011)
          .author("Jim Heavisides")
          .title("Operating Systems: An Introduction")
          .build();
      addBook(bookRegion, book3);
    }
  }

  private static void addBook(Region<Long, Book> bookRegion, Book book) {
    Long key = book.getItemNumber();
    bookRegion.put(key, book);
    log.info("Inserted a book: "+book);
  }


}
