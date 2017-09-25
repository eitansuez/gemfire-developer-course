package io.pivotal.bookshop.buslogic;

import org.apache.geode.cache.execute.FunctionException;
import org.apache.geode.cache.execute.ResultCollector;
import org.apache.geode.distributed.DistributedMember;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.TimeUnit;

public class SummingResultCollector implements ResultCollector<BigDecimal, BigDecimal> {

  // TODO-03: Determine what type will be used to contain results
  //    HINT: You only need to keep a final sum
  BigDecimal sum = BigDecimal.ZERO;

  @Override
  public void addResult(DistributedMember memberID,
                        BigDecimal resultOfSingleExecution) {
    // TODO-04: Implement the addResult method
    //    HINT: Keep in mind what was sent from the function in the prior lab.
    sum = sum.add(resultOfSingleExecution);
  }

  @Override
  public void clearResults() {
    // TODO-05: Implement clearResults method
    sum = BigDecimal.ZERO;
  }

  @Override
  public void endResults() {
    sum = sum.setScale(2, RoundingMode.HALF_UP);
  }

  @Override
  public BigDecimal getResult() throws FunctionException {
    // TODO-06: Implement getResult method
    return sum;
  }

  @Override
  public BigDecimal getResult(long timeout, TimeUnit unit)
      throws FunctionException, InterruptedException {
    // This method will do whatever the other getResult() method does.
    return this.getResult();
  }

}
