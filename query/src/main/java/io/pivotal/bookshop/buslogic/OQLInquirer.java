package io.pivotal.bookshop.buslogic;


import io.pivotal.bookshop.domain.Customer;
import org.apache.geode.cache.client.ClientCache;
import org.apache.geode.cache.query.*;

public class OQLInquirer {

  private ClientCache clientCache;

  public OQLInquirer(ClientCache cache) {
    this.clientCache = cache;
  }

  public SelectResults<Customer> doCustomerQuery() {
    // TODO-02: Implement query by 1) creating the query string and 2) return the results of calling doQuery

    return (SelectResults<Customer>) doQuery("select * from /Customer");

  }

  public SelectResults<Struct> doStructQuery() {
    // TODO-04: implement the struct query to return Struct results

    return (SelectResults<Struct>) doQuery("select firstName, lastName from /Customer");

  }

  public SelectResults<Customer> doJoin() {
    // TODO-06: Implement a join query to select customers having orders totaling more than $45.00.
    //          The key to this is properly constructing the query string

    String queryString = "select c from /Customer c, /BookOrder o " +
        " where c.customerNumber = o.customerNumber and o.totalPrice > 45";

    // alternative solution using a nested query:
    // "select * from /Customer where customerNumber in " +
    //   "( select o.customerNumber from /BookOrder o where o.totalPrice > 45 )";

    return (SelectResults<Customer>) doQuery(queryString);
  }


  // TODO-01: Implement the doQuery method to use the supplied query string to execute, returning the SelectResults
  //          Catch any exceptions and re-throw as a QueryException.
  public SelectResults<?> doQuery(String queryString) {

    try {
      return (SelectResults<?>) clientCache.getQueryService().newQuery(queryString).execute();

    } catch (FunctionDomainException | TypeMismatchException | QueryInvocationTargetException | NameResolutionException e) {
      e.printStackTrace();
      throw new QueryException(e);
    }


  }


}