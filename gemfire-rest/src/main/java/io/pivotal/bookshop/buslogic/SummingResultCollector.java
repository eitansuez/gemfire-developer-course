package io.pivotal.bookshop.buslogic;

import org.apache.geode.cache.execute.FunctionException;
import org.apache.geode.cache.execute.ResultCollector;
import org.apache.geode.distributed.DistributedMember;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

public class SummingResultCollector implements ResultCollector<Serializable, Serializable> {

	BigDecimal total =  BigDecimal.ZERO;
	
	@Override
	public void addResult(DistributedMember memberID,
			Serializable resultOfSingleExecution) {
		total = total.add((BigDecimal) resultOfSingleExecution);
	}

	@Override
	public void clearResults() {
		total = BigDecimal.ZERO;
		
	}

	@Override
	public void endResults() {
		// No special processing required.
		
	}

	@Override
	public Serializable getResult() throws FunctionException {
		return total.setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	@Override
	public Serializable getResult(long timeout, TimeUnit unit)
			throws FunctionException, InterruptedException {
		return this.getResult();
	}

}
