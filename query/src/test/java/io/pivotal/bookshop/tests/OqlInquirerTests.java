package io.pivotal.bookshop.tests;

import io.pivotal.bookshop.buslogic.OQLInquirer;
import io.pivotal.bookshop.domain.Customer;
import org.apache.geode.cache.client.ClientCache;
import org.apache.geode.cache.client.ClientCacheFactory;
import org.apache.geode.cache.query.SelectResults;
import org.apache.geode.cache.query.Struct;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;


public class OqlInquirerTests {
  private ClientCache cache;
  private OQLInquirer queryObject;

  @Before
  public void setUp() throws Exception {
    cache = new ClientCacheFactory().create();
    queryObject = new OQLInquirer(cache);
  }

  @After
  public void teardown() {
    cache.close();
  }

  // TODO-03: Run the tests and this (and only this) test should pass. The other two will initially fail.
  @Test
  public void testBasicQuery() {
    SelectResults<Customer> results = queryObject.doCustomerQuery();
    assertThat(results.size()).isEqualTo(3);

    Stream<String> firstNames = results.asList().stream().map(Customer::getFirstName);
    assertThat(firstNames.anyMatch(name -> name.equals("Lula"))).isTrue();
  }

  // TODO-05: Run the tests again after implementing doStructQuery() and now this and the testBasicQuery() test should pass.
  @Test
  public void testStructQuery() {
    SelectResults<Struct> results = queryObject.doStructQuery();
    assertThat(results.size()).isEqualTo(3);
    Stream<String> firstNames = results.asList().stream().map(item -> (String) item.get("firstName"));
    assertThat(firstNames.anyMatch(name -> name.equals("Lula"))).isTrue();
  }

  // TODO-07: Run the tests again after implementing doJoin() and now all three tests should pass.
  @Test
  public void testJoinQuery() {
    SelectResults<Customer> results = queryObject.doJoin();
    assertThat(results.size()).isEqualTo(1);
    assertThat(results.asList().get(0).getLastName()).isEqualTo("Wax");
  }
}
