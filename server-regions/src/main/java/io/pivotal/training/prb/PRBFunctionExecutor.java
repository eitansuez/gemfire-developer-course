package io.pivotal.training.prb;

import io.pivotal.bookshop.domain.Customer;
import lombok.extern.log4j.Log4j2;
import org.apache.geode.cache.Region;
import org.apache.geode.cache.client.ClientCache;
import org.apache.geode.cache.client.ClientCacheFactory;
import org.apache.geode.cache.execute.FunctionService;

@Log4j2
public class PRBFunctionExecutor {

  public static void main(String[] args) {

    try (ClientCache cache = new ClientCacheFactory().create()) {
      Region<Long, Customer> customerRegion = cache.getRegion("Customer");

      String result = (String) FunctionService.onRegion(customerRegion)
          .withCollector(new PRBResultCollector())
          .execute("PRBFunction")
          .getResult();

      log.info(result);
    }
  }

}
