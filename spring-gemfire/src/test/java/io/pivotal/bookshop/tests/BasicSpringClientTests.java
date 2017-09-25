package io.pivotal.bookshop.tests;

import io.pivotal.bookshop.buslogic.BookRepository;
import io.pivotal.bookshop.domain.Book;
import org.apache.geode.cache.Region;
import org.apache.geode.cache.query.SelectResults;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.gemfire.GemfireTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@ContextConfiguration("classpath:spring-config.xml")
// TODO-04: Open and review basic Spring Test configuration
public class BasicSpringClientTests {

  @Resource(name="Book")
  private Region<Long, Book> books;

  // TODO-07: Use the @Autowired annotation to autowire in the GemFire template
  @Autowired
  GemfireTemplate bookTemplate;

  // TODO-12: Use the @Autowired annotation to autowire your newly created Repository interface
  @Autowired
  BookRepository bookRepository;

  @Test
  // TODO-05: Run first test to verify basic configuration
  public void simpleClientTest() {
    Book book = books.get(456L);
    assertThat(book.getTitle()).isEqualTo("Clifford the Big Red Dog");
  }

  @Test
  public void testGemfireTemplateQueryMethod() {
    // TODO-08: Implement this test by writing a query to return books having the author 'Daisy Mae West'
    //   Assert you only get one item and that it's title is "A Treatise of Treatises"
    SelectResults<Book> results = bookTemplate.query("author = 'Daisy Mae West'");
    assertThat(results.asList().get(0).getTitle()).isEqualTo("A Treatise of Treatises");
  }

  @Test
  public void testGemfireTemplateFindMethod() {
    // another way of doing the above, using find..
    SelectResults<Book> books = bookTemplate.find("select * from /Book where author = $1",
        "Daisy Mae West");
    assertThat(books.size()).isEqualTo(1);
    Book book = books.asList().get(0);
    assertThat(book.getTitle()).isEqualTo("A Treatise of Treatises");
  }

  @Test
  public void testGemfireTemplateFindUniqueMethod() {
    // another way of doing the above, using find..
    Book book = bookTemplate.findUnique("select * from /Book where author = $1",
        "Daisy Mae West");
    assertThat(book.getTitle()).isEqualTo("A Treatise of Treatises");
  }

  @Test
  public void testGemfireRepositories() {
    // TODO-13: Write a test that calls your findBy method. Use the above query as an example of how to perform the query
    //   and assert the correct results
    Optional<Book> result = bookRepository.findById(456L);
    assertThat(result.isPresent()).isTrue();
    Book book = result.get();
    assertThat(book.getTitle()).isEqualTo("Clifford the Big Red Dog");
  }

  @Test
  public void testGemfireRepositoriesFindMethod() {
    List<Book> books = bookRepository.findByAuthor("Daisy Mae West");
    assertThat(books.size()).isEqualTo(1);
    Book book = books.get(0);
    assertThat(book.getTitle()).isEqualTo("A Treatise of Treatises");
  }
}
