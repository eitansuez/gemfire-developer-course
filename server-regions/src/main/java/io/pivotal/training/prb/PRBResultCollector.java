package io.pivotal.training.prb;

import org.apache.geode.cache.execute.FunctionException;
import org.apache.geode.cache.execute.ResultCollector;
import org.apache.geode.distributed.DistributedMember;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import java.util.concurrent.TimeUnit;

public class PRBResultCollector implements ResultCollector<Object[],String> {

  final Map<DistributedMember,Object[]> fullResult = new HashMap<>();

  public void addResult(DistributedMember memberID, Object[] resultOfSingleExecution) {
    this.fullResult.put(memberID, resultOfSingleExecution);
  }

  public String getResult() throws FunctionException {
    return getResultString();
  }

  public String getResult(long timeout, TimeUnit unit) throws FunctionException, InterruptedException {
    return getResultString();
  }

  private String getResultString() {
    StringBuilder builder = new StringBuilder();
 		int primaryBucketCount=0, redundantBucketCount=0, configuredBucketCount=0;
    for (Map.Entry<DistributedMember,Object[]> entry : this.fullResult.entrySet()) {
      builder.append("\nMember: ").append(entry.getKey());
      List primaryBucketInfo = (List) entry.getValue()[0];
      List redundantBucketInfo = (List) entry.getValue()[1];
      appendBucketInfo(builder, primaryBucketInfo, "Primary");
      appendBucketInfo(builder, redundantBucketInfo, "Redundant");
      builder.append("\n");
      primaryBucketCount += primaryBucketInfo.size();
      redundantBucketCount += redundantBucketInfo.size();
      configuredBucketCount = (Integer) entry.getValue()[2];
    }
		builder.append("\nPrimary Bucket Count=").append(primaryBucketCount);
		builder.append("\nRedundant Bucket Count=").append(redundantBucketCount);
		builder.append("\nConfigured Bucket Count=").append(configuredBucketCount);
    return builder.toString();
  }

  private void appendBucketInfo(StringBuilder builder, List bucketInfo, String bucketType) {
    int row = 0;
    builder.append("\n\t").append(bucketType).append(" buckets:");
    for (Iterator i = sort(bucketInfo).iterator(); i.hasNext();) {
      Map map = (Map) i.next();
      builder
        .append("\n\t\t")
        .append("Row=")
        .append(++row)
        .append(", BucketId=")
        .append(map.get("BucketId"))
        .append(", Bytes=")
        .append(map.get("Bytes"))
        .append(", Size=")
        .append(map.get("Size"));
    }
  }

  private List sort(List bucketInfo) {
    Map sortedBuckets = new TreeMap();
    for (int i=0; i<bucketInfo.size(); i++) {
      Map map = (Map) bucketInfo.get(i);
      sortedBuckets.put(map.get("BucketId"), map);
    }
    return new ArrayList(sortedBuckets.values());
  }

  public void clearResults() {
    fullResult.clear();
  }

  public void endResults() {
  }
}
