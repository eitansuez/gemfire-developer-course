package io.pivotal.training.prb;

import org.apache.geode.cache.CacheFactory;
import org.apache.geode.cache.Declarable;
import org.apache.geode.cache.execute.Function;
import org.apache.geode.cache.execute.FunctionContext;
import org.apache.geode.cache.execute.RegionFunctionContext;
import org.apache.geode.distributed.DistributedMember;
import org.apache.geode.internal.cache.BucketRegion;
import org.apache.geode.internal.cache.PartitionedRegion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class PRBFunction implements Function, Declarable {

  private DistributedMember member;
  
  public PRBFunction() {
    this.member = CacheFactory.getAnyInstance().getDistributedSystem().getDistributedMember();
  }

  public void execute(FunctionContext context) {
    RegionFunctionContext rfc = (RegionFunctionContext) context;
    PartitionedRegion pr = (PartitionedRegion) rfc.getDataSet();
    List primaryBucketInfo = new ArrayList();
    List redundantBucketInfo = new ArrayList();
    for (BucketRegion br : pr.getDataStore().getAllLocalBucketRegions()) {
      Map map = new HashMap();
      map.put("BucketId", br.getId());
      map.put("Size", br.size());
      map.put("Bytes", br.getTotalBytes());
      if (pr.getBucketPrimary(br.getId()).equals(this.member)) {
        primaryBucketInfo.add(map);
      } else {
        redundantBucketInfo.add(map);
      }
    }
    context.getResultSender().lastResult(new Object[] {primaryBucketInfo, redundantBucketInfo, pr.getPartitionAttributes().getTotalNumBuckets()});
  }

  public String getId() {
    return getClass().getSimpleName();
  }

  public boolean optimizeForWrite() {
    return true;
  }

  public boolean hasResult() {
    return true;
  }

  public boolean isHA() {
    return true;
  }

  public void init(Properties properties) {
  }
}
