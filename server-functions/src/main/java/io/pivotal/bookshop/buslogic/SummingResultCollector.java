package io.pivotal.bookshop.buslogic;

import org.apache.geode.cache.execute.FunctionException;
import org.apache.geode.cache.execute.ResultCollector;
import org.apache.geode.distributed.DistributedMember;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

public class SummingResultCollector implements ResultCollector<BigDecimal, BigDecimal> {

  private BigDecimal total = BigDecimal.ZERO;

  @Override
  public void addResult(DistributedMember memberID,
                        BigDecimal resultOfSingleExecution) {
    total = total.add(resultOfSingleExecution);
  }

  @Override
  public void clearResults() {
    total = BigDecimal.ZERO;
  }

  @Override
  public void endResults() {
    total = total.setScale(2, BigDecimal.ROUND_HALF_UP);
  }

  @Override
  public BigDecimal getResult() throws FunctionException {
    return total;
  }

  @Override
  public BigDecimal getResult(long timeout, TimeUnit unit)
      throws FunctionException, InterruptedException {
    return this.getResult();
  }

}
