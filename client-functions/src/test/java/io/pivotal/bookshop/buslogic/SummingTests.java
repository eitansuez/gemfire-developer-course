package io.pivotal.bookshop.buslogic;

import io.pivotal.bookshop.domain.BookOrder;
import lombok.extern.log4j.Log4j2;
import org.apache.geode.cache.Region;
import org.apache.geode.cache.client.ClientCache;
import org.apache.geode.cache.client.ClientCacheFactory;
import org.apache.geode.cache.execute.FunctionService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

// TODO-09: Run these tests to verify correct implementation
// (be sure to start server & locators using server-bootstrap starServers script)
@Log4j2
public class SummingTests {

  private ClientCache cache;
  private Region<Long, BookOrder> bookOrderRegion;

  @Before
  public void setup() {
    cache = new ClientCacheFactory().create();
    bookOrderRegion = cache.getRegion("BookOrder");
  }

  @After
  public void teardown() {
    log.info("********************************************");
    log.info("Closing the cache and disconnecting.");
    cache.close();
  }

  @Test
  public void shouldComputeTotalForAllOrders() throws Exception {
    // TODO-07: execute the function using the totalPrice field on the BookOrder object

    // TODO-08: Get result and assert that the two orders total $93.95

    assertThat(FunctionService.onRegion(bookOrderRegion)
      .setArguments("totalPrice")
      .withCollector(new SummingResultCollector())
      .execute("GenericSumFunction")
      .getResult()
      .toString()).isEqualTo("93.95");

  }

}
