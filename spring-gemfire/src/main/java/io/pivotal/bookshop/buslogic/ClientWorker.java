package io.pivotal.bookshop.buslogic;

import io.pivotal.bookshop.domain.Book;
import lombok.extern.log4j.Log4j2;
import org.apache.geode.cache.Region;
import org.apache.geode.cache.client.ClientCache;
import org.apache.geode.cache.client.ClientCacheFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// TODO-18: Run this program. Note that it will pause for input so you can read instructions before proceeding with
//          the creation of the entry with key 999 and then deleting it. You should see the CacheListener reporting
//          these events on the ClientConsumer console output.
@Log4j2
public class ClientWorker {

  public static void main(String[] args) throws Exception {

    log.info("Connecting to the distributed system and creating the cache.");
    // Create the cache which causes the cache-xml-file to be parsed
    ClientCache cache = new ClientCacheFactory().
        set("cache-xml-file", "clientWorkerCache.xml")
        .set("log-level", "info")
        .create();

    // Get the exampleRegion
    Region<Long, Book> region = cache.getRegion("Book");

    log.info("Note the other client's region listener in response to these gets.");
    log.info("Press Enter to continue.");
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    bufferedReader.readLine();

    log.info("Changing the data in my cache - all destroys and updates are forwarded");
    log.info("through the server to other clients. Invalidations are not forwarded.");

    // Update one value in the cache
    log.info("Putting new value for 999");
    Book book = Book.builder()
        .itemNumber(999)
        .title("Bourne Identity")
        .author("Robert Ludlum")
        .description("A spy fiction thriller about a retrograde amnesiac who must discover who he is")
        .yearPublished(2011)
        .retailCost(34.99f)
        .build();
    region.put(999L, book);

    // Destroy one entry in the cache
    log.info("Destroying 999");
    region.destroy(999L);

    // Close the cache and disconnect from GemFire distributed system
    log.info("Closing the cache and disconnecting.");
    cache.close();

    log.info("In the other session, please hit Enter in the Consumer client");
    log.info("and then stop the cacheserver with 'cacheserver stop'.");
  }
}