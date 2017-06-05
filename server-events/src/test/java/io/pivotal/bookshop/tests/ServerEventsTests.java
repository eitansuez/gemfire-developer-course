package io.pivotal.bookshop.tests;

import static org.junit.Assert.*;

import org.apache.geode.cache.CacheWriterException;
import org.apache.geode.cache.Region;
import org.apache.geode.cache.client.ClientCache;
import org.apache.geode.cache.client.ClientCacheFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import io.pivotal.bookshop.domain.Book;

public class ServerEventsTests {

  private ClientCache clientCache;
  private Region<String, Book> bookRegion;

  @Before
  public void setup() {
    clientCache = new ClientCacheFactory().create();
    bookRegion = clientCache.getRegion("Book");

    // Ensure that no entries exist before test runs:
    bookRegion.removeAll(bookRegion.keySetOnServer());
  }

  @After
  public void teardown() {
    clientCache.close();
  }

  /**
   * Asserts that when a key ('Key00') is requested that a new one is created by the cache loader.
   * First asserts that there is no entry with Key00, then performs a get operation, which should
   * trigger the CacheLoader behavior. Then, asserts that a non-null value was returned AND that
   * there is now a key = 'Key00' on the server AND the author and item number are as expected.
   * Note: These last two tests are probably unnecessary but were at least interesting to validate.
   */
  @Test
  public void shouldLoadEntryOnCacheMiss() {
    String key = "Key00";
    assertFalse("Region is not properly initialized", bookRegion.containsKeyOnServer(key));

    Book newBook = bookRegion.get(key);
    assertNotNull("BookCacheLoader failed to create book for Key00", newBook);
    assertTrue("Key " + key + " not found on server", bookRegion.containsKeyOnServer(key));
    assertEquals("BookCacheLoader created the incorrect book", "Daisy Mae West", newBook.getAuthor());
    assertEquals("BookCacheLoader created the incorrect book", 123, newBook.getItemNumber());
  }

  /**
   * Asserts that when inserting a valid entry that no errors occur
   */
  @Test
  public void shouldAllowEntryWrite() {
    bookRegion.put("Key01", new Book(124, "A spy fiction thriller about a retrograde amnesiac who must discover who he is ", (float) 34.99, 2011, "Robert Ludlum", "Bourne Identity"));
  }

  /**
   * Asserts that when attempting to insert two entries with the same itemNumber, the second one fails with an expected
   * CacheWriterException.
   */
  @Test()
  public void shouldVetoEntryWithDuplicateItemNumber() {
    bookRegion.put("Key01", new Book(124, "A spy fiction thriller about a retrograde amnesiac who must discover who he is ", (float) 34.99, 2011, "Robert Ludlum", "Bourne Identity"));
    try {
      bookRegion.put("Key02", new Book(124, "A spy fiction thriller about a retrograde amnesiac who must discover who he is ", (float) 34.99, 2011, "Robert Ludlum", "Bourne Identity"));
      fail("Failed to throw an exception");
    } catch (Exception e) {
      assert e.getCause() instanceof CacheWriterException;
    }
  }
}
