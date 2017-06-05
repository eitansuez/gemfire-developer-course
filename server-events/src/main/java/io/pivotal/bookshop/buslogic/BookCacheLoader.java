package io.pivotal.bookshop.buslogic;

import java.util.Properties;

import io.pivotal.bookshop.domain.Book;
import org.apache.geode.cache.CacheLoader;
import org.apache.geode.cache.Declarable;
import org.apache.geode.cache.LoaderHelper;

public class BookCacheLoader implements CacheLoader<String, Book>, Declarable {

  public Book load(LoaderHelper<String, Book> helper) {
    Book newBook = null;

    // Implement the 'load' functionality to create a new book

    return newBook;
  }

  public void close() {
    // do nothing
  }

  public void init(Properties props) {
    // do nothing
  }
}