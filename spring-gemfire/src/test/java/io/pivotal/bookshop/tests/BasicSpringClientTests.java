package io.pivotal.bookshop.tests;

import io.pivotal.bookshop.domain.Book;
import org.apache.geode.cache.Region;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-config.xml")
// TODO-04: Open and review basic Spring Test configuration
public class BasicSpringClientTests {

  @Resource(name="Book")
  private Region<Long, Book> books;

  // TODO-07: Use the @Autowired annotation to autowire in the GemFire template

  // TODO-12: Use the @Autowired annotation to autowire your newly created Repository interface

  @Test
  // TODO-05: Run first test to verify basic configuration
  public void simpleClientTest() {
    Book book = books.get(456L);
    assertEquals("Clifford the Big Red Dog", book.getTitle());
  }

  @Test
  public void testGemfireTemplate() {
    // TODO-08: Implement this test by writing a query to return books having the author 'Daisy Mae West'
    //          Assert you only get one item and that it's title is "A Treatise of Treatises"
    fail("Not Implemented");
  }

  @Test
  public void testGemfireRepositories() {
    // TODO-13: Write a test that calls your findBy method. Use the above query as an example of how to perform the query
    //          and assert the correct results
    fail("Not Implemented");
  }
}
