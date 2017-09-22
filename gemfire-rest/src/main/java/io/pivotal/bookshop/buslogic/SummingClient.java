package io.pivotal.bookshop.buslogic;

import io.pivotal.bookshop.domain.Customer;
import lombok.extern.log4j.Log4j2;
import org.apache.geode.cache.Region;
import org.apache.geode.cache.client.ClientCache;
import org.apache.geode.cache.client.ClientCacheFactory;
import org.apache.geode.cache.execute.Execution;
import org.apache.geode.cache.execute.FunctionService;
import org.apache.geode.cache.execute.ResultCollector;

import java.math.BigDecimal;

@Log4j2
public class SummingClient {

  public static void main(String[] args) throws Exception {
    ClientCache cache = new ClientCacheFactory().create();

    Region<Long, Customer> customer = cache.getRegion("BookOrder");

    Execution execution = FunctionService.onRegion(customer)
        .setArguments("totalAmount")
        .withCollector(new SummingResultCollector());

    ResultCollector rc = execution.execute("GenericSumFunction");

    BigDecimal result = (BigDecimal) rc.getResult();
    log.info(result);


    log.info("********************************************");
    log.info("Closing the cache and disconnecting.");
    cache.close();
  }

}
