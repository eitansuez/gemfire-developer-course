package io.pivotal.bookshop;

import io.pivotal.bookshop.domain.Customer;
import lombok.extern.log4j.Log4j2;
import org.apache.geode.cache.Cache;
import org.apache.geode.cache.CacheFactory;
import org.apache.geode.cache.Region;
import org.apache.geode.cache.client.ClientCache;
import org.apache.geode.cache.client.ClientCacheFactory;

@Log4j2
public class SimpleClientReader {

  public static void main(String[] args) throws InterruptedException {
    try ( ClientCache cache = new ClientCacheFactory().create() ) {
      Region<Long, Customer> customerRegion = cache.getRegion("Customer");
      Customer c = customerRegion.get(1001L);
      System.out.println(c);
    }
  }

}
