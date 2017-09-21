package io.pivotal.bookshop.tests;

import org.apache.geode.cache.CacheWriterException;
import org.apache.geode.cache.Region;
import org.apache.geode.cache.client.ClientCache;
import org.apache.geode.cache.client.ClientCacheFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import io.pivotal.bookshop.domain.Book;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

public class ServerEventsTests {

  private ClientCache clientCache;
  private Region<String, Book> bookRegion;
  private Book book = Book.builder()
      .itemNumber(124)
      .title("Bourne Identity")
      .author("Robert Ludlum")
      .description("A spy fiction thriller about a retrograde amnesiac who must discover who he is")
      .yearPublished(2011)
      .retailCost(34.99f)
      .build();

  @Before
  public void setup() {
    clientCache = new ClientCacheFactory().create();
    bookRegion = clientCache.getRegion("Book");

    // Ensure no entries exist before test runs:
    bookRegion.removeAll(bookRegion.keySetOnServer());
  }

  @After
  public void teardown() {
    clientCache.close();
  }

  @Test
  public void shouldLoadEntryOnCacheMiss() {
    String key = "Key00";
    assertThat(bookRegion.containsKeyOnServer(key)).isFalse();

    Book newBook = bookRegion.get(key);
    assertThat(newBook).isNotNull();
    assertThat(bookRegion.containsKeyOnServer(key)).isTrue();
    assertThat(newBook.getAuthor()).isEqualTo("Daisy Mae West");
    assertThat(newBook.getItemNumber()).isEqualTo(123);
  }

  @Test
  public void shouldAllowEntryWrite() {
    bookRegion.put("Key01", book);
  }

  @Test()
  public void shouldVetoEntryWithDuplicateItemNumber() {
    bookRegion.put("Key01", book);
    try {
      bookRegion.put("Key02", book);
      fail("Failed to throw an exception");
    } catch (Exception e) {
      assert e.getCause() instanceof CacheWriterException;
    }
  }
}
