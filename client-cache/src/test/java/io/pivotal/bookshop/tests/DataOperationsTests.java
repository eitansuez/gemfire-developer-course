package io.pivotal.bookshop.tests;

import io.pivotal.bookshop.buslogic.BookDao;
import io.pivotal.bookshop.domain.Book;
import org.apache.geode.cache.EntryExistsException;
import org.apache.geode.cache.Region;
import org.apache.geode.cache.client.ClientCache;
import org.apache.geode.cache.client.ClientCacheFactory;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

// TODO-10: Open this file and inspect the various test methods.
// TODO-15: When you have completed implementing the BookDao functionality, run this JUnit test to validate correct implementation
public class DataOperationsTests {
  private static long key = 12345L;
  private BookDao dao;
  private Region<Long, Book> books;

  private Book book = Book.builder()
      .itemNumber(key)
        .title("All About GemFire")
        .author("Some Author")
        .description("New test book")
        .retailCost(28.77f)
        .yearPublished(2014)
        .build();

  private Book book2 = Book.builder()
      .itemNumber(key)
      .title("All About GemFire, part 2")
      .author("Some Other Author")
      .description("Another new test book")
      .retailCost(33.77f)
      .yearPublished(2015)
      .build();


  @Before
  public void setUp() throws Exception {
    ClientCache cache = new ClientCacheFactory().create();

    dao = new BookDao(cache);
    books = cache.getRegion("Book");

    books.removeAll(books.keySetOnServer());
  }

  @Test
  public void shouldInsertBook() {
    // given a book

    // when put book into region via dao's "insertBook" method
    dao.insertBook(key, book);

    // then can independently verify that book now exists in that region
    assertThat(books.get(key)).isNotNull();
    assertThat(books.get(key)).isEqualTo(book);
  }

  @Test
  public void shouldFailToInsertBookIfAlreadyExists() {
    // given a book already exists in the Book region
    books.put(key, book);

    try {
      // when i attempt to insert another book using same key
      dao.insertBook(key, book2);
      fail("Should have thrown an EntryExistsException");
    } catch (EntryExistsException ex) {
      // then throws EntryExistsException
      // test passed.
    }

  }

  @Test
  public void shouldFetchBookByKey() {
    // given a book exists in the Book region
    books.put(key, book);

    // when i fetch the book via dao's "getBook" method
    Book fetchedBook = dao.getBook(key);

    // then retrieved book matches the book in the region
    assertThat(fetchedBook).isNotNull();
    assertThat(fetchedBook).isEqualTo(book);
  }

  @Test
  public void shouldUpdateExistingBook() {
    // given a book exists in the Book region
    books.put(key, book);

    // when i update the title of the book with the dao's "updateBook" method
    Book updatedBook = book.toBuilder().title("New book title").build();
    dao.updateBook(key, updatedBook);

    // then i can independently verify that the stored book has been updated
    // author should be the same as it was..
    assertThat(books.get(key).getAuthor()).isEqualTo("Some Author");
    // title should have been updated..
    assertThat(books.get(key).getTitle()).isEqualTo("New book title");
    assertThat(books.get(key)).isEqualTo(book2);
  }

  @Test
  public void shouldNotCreateRecordWhenUpdatingNonExistingBook() {
    // given a book

    // when I try to call updateBook when in fact no entry for key exists
    dao.updateBook(key, book);

    // then no book should have been inserted/placed in the region
    assertThat(dao.getBook(key)).isNull();
  }

  @Test
  public void shouldDeleteBook() {
    // given a book exists in the Book region
    books.put(key, book);

    // when i remove the book via the dao "removeBook" method
    dao.removeBook(key);

    // then the entry no longer exists in the region
    assertThat(books.get(key)).isNull();
  }

}
