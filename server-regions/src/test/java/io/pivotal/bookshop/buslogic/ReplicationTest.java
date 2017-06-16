package io.pivotal.bookshop.buslogic;


import io.pivotal.bookshop.domain.Book;
import lombok.extern.log4j.Log4j2;
import org.apache.geode.cache.Region;
import org.apache.geode.cache.client.ClientCache;
import org.apache.geode.cache.client.ClientCacheFactory;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Log4j2
public class ReplicationTest {

  @Test
  public void shouldRetrieveSomeBooks() {
    try (ClientCache cache = new ClientCacheFactory().create()) {
      Region<Long, Book> books = cache.getRegion("Book");

      assertThat(books.get(123L)).isNotNull();
      assertThat(books.get(456L)).isNotNull();
      assertThat(books.get(789L)).isNotNull();

      log.info("Book retrieved: " + books.get(123L));
      log.info("Book retrieved: " + books.get(456L));
      log.info("Book retrieved: " + books.get(789L));
    }
  }

}
