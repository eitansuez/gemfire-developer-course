package io.pivotal.bookshop.buslogic;


import io.pivotal.bookshop.domain.Book;
import lombok.extern.log4j.Log4j2;
import org.apache.geode.cache.Region;
import org.apache.geode.cache.client.ClientCache;
import org.apache.geode.cache.client.ClientCacheFactory;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@Log4j2
public class ReplicationTest {

  @Test
  public void shouldRetrieveSomeBooks() {
    try (ClientCache cache = new ClientCacheFactory().create()) {
      Region<Long, Book> books = cache.getRegion("Book");

      assertNotNull(books.get(123L));
      assertNotNull(books.get(456L));
      assertNotNull(books.get(789L));

      log.info("Book retrieved: " + books.get(123L));
      log.info("Book retrieved: " + books.get(456L));
      log.info("Book retrieved: " + books.get(789L));
    }
  }

}
