package io.pivotal.bookshop.buslogic;

import org.apache.geode.cache.execute.FunctionException;
import org.apache.geode.cache.execute.ResultCollector;
import org.apache.geode.distributed.DistributedMember;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

public class SummingResultCollector implements ResultCollector<Serializable, Serializable> {

  // TODO-03: Determine what type will be used to contain results
  //    HINT: You only need to keep a final sum

  @Override
  public void addResult(DistributedMember memberID,
                        Serializable resultOfSingleExecution) {
    // TODO-04: Implement the addResult method
    //    HINT: Keep in mind what was sent from the function in the prior lab.

  }

  @Override
  public void clearResults() {
    // TODO-05: Implement clearResults method

  }

  @Override
  public void endResults() {
    // No special processing required.

  }

  @Override
  public Serializable getResult() throws FunctionException {
    // TODO-06: Implement getResult method
    return null;
  }

  @Override
  public Serializable getResult(long timeout, TimeUnit unit)
      throws FunctionException, InterruptedException {
    // This method will do whatever the other getResult() method does.
    return this.getResult();
  }

}
