package io.pivotal.bookshop.buslogic;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// TODO-17: Open the ClientConsumer class and examine the code before executing. When ready, go ahead and run this program.
//          After initializing the Spring ApplicationContext, the application just basically waits for the ClientWorker to run.
//          Note that you will need to end this program once the ClientWorker is finished. You can do this by putting the cursor
//          in the console and hitting enter to continue to the end.
@Log4j2
public class ClientConsumer {

  public static void main(String[] args) throws Exception {
    // Initialize the application context, which defines the Book region and registers interest
    new ClassPathXmlApplicationContext("spring-config.xml");

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
  }

}
