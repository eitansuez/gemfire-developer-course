package io.pivotal.bookshop.buslogic;

import org.apache.geode.GemFireCheckedException;
import org.apache.geode.GemFireException;
import org.apache.geode.cache.client.ClientCache;
import org.apache.geode.cache.client.ClientCacheFactory;
import org.apache.geode.cache.client.Pool;
import org.apache.geode.cache.client.PoolManager;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CQClient {

  public static void main(String[] args) throws Exception {
    ClientCache cache = new ClientCacheFactory()
        .set("name", "CQClient")
        .set("cache-xml-file", "clientConsumerCache.xml")
        .create();

    try {
      registerCq();

      BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

      System.out.println("Press enter to end");
      bufferedReader.readLine();
    } catch (Exception ex) {
      ex.printStackTrace();

    } finally {
      cache.close();
    }
  }

  private static void registerCq() throws GemFireException, GemFireCheckedException {
    // Get a reference to the pool
    Pool myPool = PoolManager.find("client");

    // TODO-07: Get the query service for the Pool


    // TODO-08: Create CQ Attributes, registering the SimpleCQListener
    // implementation class


    // TODO-09: Construct a query that will trigger a CQEvent when a
    // BookOrder has a totalPrice value > $100


    // TODO-10: Create the continuous query and execute it. If executing
    // with initial results, capture
    // the results and iterate over them, printing the orders

  }


}
