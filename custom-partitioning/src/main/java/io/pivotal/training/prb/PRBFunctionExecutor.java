package io.pivotal.training.prb;

import io.pivotal.bookshop.domain.BookOrder;
import io.pivotal.bookshop.domain.Customer;
import io.pivotal.bookshop.domain.OrderKey;
import lombok.extern.log4j.Log4j2;
import org.apache.geode.cache.Region;
import org.apache.geode.cache.client.ClientCache;
import org.apache.geode.cache.client.ClientCacheFactory;
import org.apache.geode.cache.execute.Execution;
import org.apache.geode.cache.execute.FunctionService;
import org.apache.geode.cache.execute.ResultCollector;

@Log4j2
// TODO-07: Run this function executor to obtain and print out bucket assignment info for the Customer and BookOrder regions
public class PRBFunctionExecutor {

  public static void main(String[] args) {
    try (ClientCache cache = new ClientCacheFactory().create()) {

      Region<Long, Customer> customers = cache.getRegion("Customer");
      Region<OrderKey, BookOrder> bookOrders = cache.getRegion("BookOrder");

      log.info("\nCustomer buckets");
      executePRB(customers);

      log.info("\nBookOrder buckets");
      executePRB(bookOrders);
    }
  }

  private static void executePRB(Region r) {
    Execution execution = FunctionService.onRegion(r).withCollector(new PRBResultCollector());
    ResultCollector collector = execution.execute("PRBFunction");
    String result = (String) collector.getResult();
    log.info(result);
  }

}
