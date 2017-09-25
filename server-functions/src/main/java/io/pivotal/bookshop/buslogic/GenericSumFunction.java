package io.pivotal.bookshop.buslogic;

import lombok.extern.log4j.Log4j2;
import org.apache.geode.cache.CacheFactory;
import org.apache.geode.cache.Declarable;
import org.apache.geode.cache.Region;
import org.apache.geode.cache.execute.Function;
import org.apache.geode.cache.execute.FunctionContext;
import org.apache.geode.cache.execute.RegionFunctionContext;
import org.apache.geode.cache.partition.PartitionRegionHelper;
import org.apache.geode.distributed.DistributedMember;
import org.apache.geode.pdx.PdxInstance;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Properties;

@Log4j2
public class GenericSumFunction implements Function, Declarable {

  @Override
  public void execute(FunctionContext context) {
    DistributedMember member = CacheFactory.getAnyInstance().getDistributedSystem().getDistributedMember();

    // TODO-03: Ensure the FunctionContext is a RegionFunctionContext and if so, cast it up to RegionFunctionContext
    if (!(context instanceof RegionFunctionContext)) {
      throw new RuntimeException("Invalid context type");
    }

    RegionFunctionContext regionFunctionContext = (RegionFunctionContext) context;

    // TODO-04: Get the argument from the FunctionContext representing the field to perform sum on
    String fieldName = (String) regionFunctionContext.getArguments();

    // TODO-05: Use the PartitionRegionHelper to get all the local region data
    Region<Long, PdxInstance> localRegion = PartitionRegionHelper.getLocalDataForContext(regionFunctionContext);
    BigDecimal sum = BigDecimal.ZERO;
    // TODO-06: Iterate over the values in the local region data
    for (PdxInstance instance : localRegion.values()) {
      Number number = (Number) instance.getField(fieldName);
      sum = sum.add(BigDecimal.valueOf(number.doubleValue()));
    }

    // TODO-07: Get the requested field, assert it is a Numeric type, cast it and add it to
    //          the summable variable defined above

    // TODO-08: Return the final sum
    BigDecimal result = sum.setScale(2, RoundingMode.HALF_UP);

    String message = String.format("Function %s residing on member %s is returning the value %s", getId(), member.getName(), result);
    log.info(message);

    regionFunctionContext.getResultSender().lastResult(result);
  }

  @Override
  public boolean hasResult() {
    return true;
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
