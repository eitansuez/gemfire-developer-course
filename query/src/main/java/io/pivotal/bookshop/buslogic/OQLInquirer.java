package io.pivotal.bookshop.buslogic;


import io.pivotal.bookshop.domain.Customer;
import org.apache.geode.cache.client.ClientCache;
import org.apache.geode.cache.query.SelectResults;
import org.apache.geode.cache.query.Struct;

public class OQLInquirer {

  private ClientCache clientCache;

  public OQLInquirer(ClientCache cache) {
    this.clientCache = cache;
  }

  public SelectResults<Customer> doCustomerQuery() {
    // TODO-02: Implement query by 1) creating the query string and 2) return the results of calling doQuery

    throw new RuntimeException("Yet to be implemented");

  }

  public SelectResults<Struct> doStructQuery() {
    // TODO-04: implement the struct query to return Struct results

    throw new RuntimeException("Yet to be implemented");

  }

  public SelectResults<Customer> doJoin() {
    // TODO-06: Implement a join query to select customers having orders totaling more than $45.00.
    //          The key to this is properly constructing the query string

    throw new RuntimeException("Yet to be implemented");

  }


  // TODO-01: Implement the doQuery method to use the supplied query string to execute, returning the SelectResults
  //          Catch any exceptions and re-throw as a QueryException.
  public SelectResults<?> doQuery(String queryString) {

    throw new RuntimeException("Yet to be implemented");

  }


}