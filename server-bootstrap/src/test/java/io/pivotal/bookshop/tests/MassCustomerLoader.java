package io.pivotal.bookshop.tests;

import io.pivotal.bookshop.domain.Customer;
import lombok.extern.log4j.Log4j2;
import org.apache.geode.cache.Region;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@Log4j2
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:cache-config.xml"})
@ActiveProfiles({"basic"})
public class MassCustomerLoader {

  @Resource(name = "Customer")
  private Region<Long, Customer> customerRegion;

  @Test @Ignore
  public void loadManyCustomers() {
    String firstName = "Kari";
    for (long i=0; i<20000; i++) {
      customerRegion.put(i,
          new Customer(i, firstName+"["+i+"]", "Powell", "44444")
      );
    }
    log.info("populated customer region.");
  }

}
