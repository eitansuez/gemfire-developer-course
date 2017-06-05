package io.pivotal.bookshop.buslogic;

import lombok.extern.log4j.Log4j2;
import org.apache.geode.cache.Declarable;
import org.apache.geode.cache.Region;
import org.apache.geode.cache.execute.Function;
import org.apache.geode.cache.execute.FunctionContext;
import org.apache.geode.cache.execute.FunctionException;
import org.apache.geode.cache.execute.RegionFunctionContext;
import org.apache.geode.cache.partition.PartitionRegionHelper;
import org.apache.geode.pdx.PdxInstance;

import java.math.BigDecimal;
import java.util.Properties;

@Log4j2
public class GenericSumFunction implements Function, Declarable {

  @Override
  public void execute(FunctionContext context) {
    if (context instanceof RegionFunctionContext) {
      RegionFunctionContext rfc = (RegionFunctionContext) context;
      String fieldString = (String) rfc.getArguments();
      Region<Object, PdxInstance> localRegion = PartitionRegionHelper.getLocalDataForContext(rfc);
      BigDecimal summable = BigDecimal.ZERO;
      for (PdxInstance item : localRegion.values()) {
        Object field = item.getField(fieldString);
        if (field instanceof Float) {
          summable = summable.add(BigDecimal.valueOf((Float) field));
        } else {
          log.info("Field : " + fieldString + " is NOT a Float. Value= " + field);
        }
      }
      log.info("Returning: " + summable);
      rfc.getResultSender().lastResult(summable);
    } else {
      throw new FunctionException("Function must be called as onRegion()");
    }

  }

  @Override
  public String getId() {
    return getClass().getSimpleName();
  }

  @Override
  public boolean hasResult() { return true; }

  @Override
  public void init(Properties props) {
    // Nothing to do
  }

}
