package io.pivotal.bookshop.buslogic;

import io.pivotal.bookshop.domain.Book;
import lombok.extern.log4j.Log4j2;
import org.apache.geode.cache.CacheWriterException;
import org.apache.geode.cache.Declarable;
import org.apache.geode.cache.EntryEvent;
import org.apache.geode.cache.Region;
import org.apache.geode.cache.query.Query;
import org.apache.geode.cache.query.QueryException;
import org.apache.geode.cache.query.QueryService;
import org.apache.geode.cache.query.SelectResults;
import org.apache.geode.cache.util.CacheWriterAdapter;

import java.util.Properties;

/**
 * This implementation provides a specific function for all new Book entries. It validates that there are
 * no other entries having the same itemNumber. This in theory would ensure someone doesn't mistakenly try to create
 * two books with the same item number.
 * <p>
 * This implementation has factored out the validation part from the handling of invalid entries. It's the job of
 * the CacheWriter method(s) to extract the value and region to validate and to handle an invalid case. It's the
 * job of validateNewValue to handle the logic of determining if the object in question is valid.
 */
@Log4j2
public class ValidatingCacheWriter extends CacheWriterAdapter<String, Book> implements Declarable {

  @Override
  public void beforeCreate(EntryEvent<String, Book> event) throws CacheWriterException {
    // Implement the functionality to obtain the correct value and validate it, issuing
    // a CacheWriterException if new entry is invalid.
    Book book = event.getNewValue();
    Region<String, Book> bookRegion = event.getRegion();
    try {
      if (!validateNewValue(book, bookRegion)) {
        long itemNumber = event.getNewValue().getItemNumber();
        throw new CacheWriterException("Book with item number " +
            itemNumber + " already exists!");
      }
    } catch (QueryException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * New value is valid as long as the itemNumber doesn't exist
   *
   * @param book  New book value to validate
   * @param books Book region reference used to create a query
   * @return true if new value is valid (i.e no other entry has the same itemNumber)
   * @throws QueryException If this query fails for some reason
   */
  private boolean validateNewValue(Book book, Region books) throws QueryException {
    log.info("Validating Item: " + book);
    Object[] queryParams = new Object[1];
    queryParams[0] = book.getItemNumber();
    String queryString = "Select * from /Book where itemNumber = $1";
    QueryService queryService = books.getRegionService().getQueryService();
    Query query = queryService.newQuery(queryString);
    SelectResults results = (SelectResults) query.execute(queryParams);

    return results.size() == 0;
  }

  @Override
  public void init(Properties props) {
    // Not passing parameters

  }

}
