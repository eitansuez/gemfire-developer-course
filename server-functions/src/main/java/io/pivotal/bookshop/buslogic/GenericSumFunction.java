package io.pivotal.bookshop.buslogic;

import lombok.extern.log4j.Log4j2;
import org.apache.geode.cache.CacheFactory;
import org.apache.geode.cache.Declarable;
import org.apache.geode.cache.execute.Function;
import org.apache.geode.cache.execute.FunctionContext;
import org.apache.geode.distributed.DistributedMember;

import java.math.BigDecimal;
import java.util.Properties;

@Log4j2
public class GenericSumFunction implements Function, Declarable {

  @Override
  public void execute(FunctionContext context) {
    DistributedMember member = CacheFactory.getAnyInstance().getDistributedSystem().getDistributedMember();

    // TODO-01: Ensure the FunctionContext is a RegionFunctionContext and if so, cast it up to RegionFunctionContext

    // TODO-02: Get the argument from the FunctionContext representing the field to perform sum on

    // TODO-03: Use the PartitionRegionHelper to get all the local region data

    BigDecimal sum = BigDecimal.ZERO;
    // TODO-04: Iterate over the values in the local region data

    // TODO-05: Get the requested field, assert it is a Numeric type, cast it and add it to
    //          the summable variable defined above

    // TODO-06: Return the final sum

  }

  @Override
  public String getId() {
    return getClass().getSimpleName();
  }

  @Override
  public void init(Properties props) {
    // Nothing to initialize
  }

}
