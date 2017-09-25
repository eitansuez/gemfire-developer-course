package io.pivotal.bookshop.tests;

import io.pivotal.bookshop.domain.Book;
import io.pivotal.bookshop.domain.Customer;
import org.apache.geode.cache.Region;
import org.apache.geode.cache.client.ClientCache;
import org.apache.geode.cache.client.ClientCacheFactory;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

// TODO-06: Run the test suite
public class ClientCacheTests {

  private ClientCache cache;
  private Region<Long, Book> books;
  private Region<Long, Customer> customers;
  private Region<Long, String> localRegion;

  @Before
  public void setUp() throws Exception {
    // TODO-04: Write the code to obtain a reference to a ClientCache instance;
    //  if necessary, use the set() method to configure your client cache factory
    //  before calling create()
    cache = new ClientCacheFactory().create();

    // TODO-05: Make call to the getRegion() method to get the Book and Customer regions
    // from the ClientCache and assign to instance variables
    books = cache.getRegion("Book");
    customers = cache.getRegion("Customer");

    // TODO-08: obtain a reference to the local region, assign it to localRegion above
    localRegion = cache.getRegion("LocalRegion");
  }

  @Test
  public void regionsShouldExist() {
    assertThat(books).isNotNull();
    assertThat(customers).isNotNull();
  }

  @Test
  public void customersShouldExist() {
    assertThat(customers).isNotNull();

    Customer customer = customers.get(5598L);
    assertThat(customer).isNotNull();
    assertThat(customer.getFirstName()).isEqualTo("Kari");
  }

  @Test
  public void booksShouldExist() {
    assertThat(books).isNotNull();

    Book book = books.get(456L);
    assertThat(book).isNotNull();
    assertThat(book.getTitle()).isEqualTo("Clifford the Big Red Dog");
  }

  @Test
  // TODO-09: Re-run the tests and verify that they all pass
  public void localRegionShouldExist() {
    assertThat(localRegion).isNotNull();
  }

}
