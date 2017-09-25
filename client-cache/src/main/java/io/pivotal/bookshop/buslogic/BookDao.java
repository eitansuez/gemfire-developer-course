package io.pivotal.bookshop.buslogic;

import io.pivotal.bookshop.domain.Book;
import org.apache.geode.cache.Region;
import org.apache.geode.cache.client.ClientCache;

public class BookDao {
  // The region object that stores Book objects
  private Region<Long, Book> books;

  public BookDao(ClientCache cache) {
    this.books = cache.getRegion("Book");
  }

  public void insertBook(Long key, Book book) {
    // TODO-11: Write code to insert book with the given key. Use the method that assumes
    //          the entry doesn't already exist
    books.create(key, book);

  }

  public Book getBook(Long key) {
    // TODO-12: Write code to get & return a book for the specified key
    return books.get(key);
  }

  public void updateBook(Long key, Book book) {
    // TODO-13: Write code to update book for specified key
    books.replace(key, book);

  }

  public void removeBook(Long key) {
    // TODO-14: Implement delete functionality for specified key
    books.remove(key);

  }

}
