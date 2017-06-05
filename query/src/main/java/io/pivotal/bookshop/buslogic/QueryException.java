package io.pivotal.bookshop.buslogic;

public class QueryException extends RuntimeException {

  public QueryException() {
  }

  public QueryException(Throwable cause) {
    super(cause);
  }

  public QueryException(String message) {
    super(message);
  }

  public QueryException(String message, Throwable cause) {
    super(message, cause);
  }

}
