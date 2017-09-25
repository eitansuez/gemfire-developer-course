package io.pivotal.bookshop.buslogic;

import lombok.extern.log4j.Log4j2;
import org.apache.geode.cache.Region;
import org.apache.geode.cache.client.ClientCache;
import org.apache.geode.cache.client.ClientCacheFactory;
import org.apache.geode.pdx.PdxInstance;

@Log4j2
public class PdxInstanceClient {

  public static void main(String[] args) {
    ClientCache cache = new ClientCacheFactory().create();

    Region<Long, PdxInstance> customerRegion = cache.getRegion("Customer");
    log.info("Got the Customer Region: " + customerRegion);

    // TODO-10: Add code to fetch the PdxInstance for key 9999 and extract just the telephoneNumber and print it out.
    PdxInstance pdxInstance = customerRegion.get(9999L);
    System.out.println("Customer phone # is: "+pdxInstance.getField("telephoneNumber"));

    cache.close();
  }

}
