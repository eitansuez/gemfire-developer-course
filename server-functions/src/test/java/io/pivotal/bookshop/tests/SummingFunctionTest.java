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

import static org.assertj.core.api.Assertions.assertThat;

public class SummingFunctionTest {
  private Region<Long, BookOrder> bookOrders;

  @Before
  public void setup() {
    ClientCache cache = new ClientCacheFactory().create();
    bookOrders = cache.getRegion("BookOrder");
  }

  @Test
  // TODO-09: Run the test verifying the function performs as expected
  public void testSummingFunction() {
    Execution<String, BigDecimal, BigDecimal> execution = FunctionService
        .onRegion(bookOrders)
        .setArguments("totalPrice")
        .withCollector(new SummingResultCollector());

    ResultCollector<BigDecimal, BigDecimal> rc = execution.execute("GenericSumFunction");

    BigDecimal result = rc.getResult();
    assertThat(result.toString()).isEqualTo("93.95"); // 40.98 + 52.97
  }
}
