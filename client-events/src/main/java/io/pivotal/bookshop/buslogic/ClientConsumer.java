package io.pivotal.bookshop.buslogic;

import lombok.extern.log4j.Log4j2;
import org.apache.geode.cache.Region;
import org.apache.geode.cache.client.ClientCache;
import org.apache.geode.cache.client.ClientCacheFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Log4j2
public class ClientConsumer {

  public static void main(String[] args) throws Exception {

    log.info("Connecting to the distributed system and creating the cache.");
    // Create the cache which causes the cache-xml-file to be parsed
    ClientCache cache = new ClientCacheFactory()
        .set("name", "ClientConsumer")
        .set("cache-xml-file", "clientConsumerCache.xml")
        .create();

    // Get the Book region
    Region<Long, ?> region = cache.getRegion("Book");
    log.info("Book region \"" + region.getFullPath() + "\" created in cache. ");

    log.info("Asking the server to send me events for data with the keys: 999, which will be inserted by the ClientWorker");

    // TODO-05: Using the Region API, register interest in the entry who's key is 999 (this will be inserted by the ClientWorker)
    region.registerInterest(999L);


		/*
     * This code is simply output to the console to tell the user what's going on. This is especially important as
		 * work will be coordinated between this class and the ClientWorker class running at the same time.
		 */
    log.info("The data region has a listener that reports all changes to standard out.");

    log.info("Please run the worker client in another session. It will update the");
    log.info("cache and the server will forward the updates to me. Note the listener");
    log.info("output in this session.");

    log.info("When the other client finishes, hit Enter to exit this program.");

    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    bufferedReader.readLine();

    log.info("Closing the cache and disconnecting.");
    cache.close();
  }
}