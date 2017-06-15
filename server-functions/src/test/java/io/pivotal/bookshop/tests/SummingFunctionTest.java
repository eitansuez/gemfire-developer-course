package io.pivotal.bookshop.tests;

import io.pivotal.bookshop.buslogic.SummingResultCollector;
import io.pivotal.bookshop.domain.BookOrder;
import org.apache.geode.cache.Region;
import org.apache.geode.cache.client.ClientCache;
import org.apache.geode.cache.client.ClientCacheFactory;
import org.apache.geode.cache.execute.Execution;
import org.apache.geode.cache.execute.FunctionService;
import org.apache.geode.cache.execute.ResultCollector;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class SummingFunctionTest {
  private Region<Long, BookOrder> bookOrders;

  @Before
  public void setup() {
    ClientCache cache = new ClientCacheFactory().create();
    bookOrders = cache.getRegion("BookOrder");
  }

  @Test
  // TODO-11: Run the test verifying the function performs as expected
  public void testSummingFunction() {
    Execution execution = FunctionService
        .onRegion(bookOrders)
        .withArgs("totalPrice")
        .withCollector(new SummingResultCollector());

    ResultCollector rc = execution.execute("GenericSumFunction");

    BigDecimal result = (BigDecimal) rc.getResult();
    assertEquals(new BigDecimal("93.95"), result); // 40.98 + 52.97
  }
}
