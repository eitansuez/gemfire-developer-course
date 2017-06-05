package io.pivotal.bookshop.buslogic;

import io.pivotal.bookshop.domain.Customer;
import lombok.extern.log4j.Log4j2;
import org.apache.geode.cache.Declarable;
import org.apache.geode.cache.Region;
import org.apache.geode.cache.execute.Function;
import org.apache.geode.cache.execute.FunctionContext;
import org.apache.geode.cache.execute.RegionFunctionContext;
import org.apache.geode.cache.partition.PartitionRegionHelper;

import java.util.*;

@Log4j2
public class Function_FindCustByCity implements Function, Declarable {

  public void execute(FunctionContext fc) {

    Customer someCustomer;
    List<Customer> allCustomerList = new ArrayList<>();
    int custNo = 0;

    if (fc instanceof RegionFunctionContext) {

      RegionFunctionContext context = (RegionFunctionContext) fc;

      String city = (String) context.getArguments();

      Region<Long, Customer> customer = PartitionRegionHelper.getLocalDataForContext(context);

      Set keys = customer.keySet();
      int setSize = keys.size();
      Iterator keysIterator = keys.iterator();

      for (int i = 0; i < (setSize); i++) {
        someCustomer = customer.get(keysIterator.next());
        if (someCustomer.getPrimaryAddress().getCity().equals(city)) {
          allCustomerList.add(someCustomer);
          custNo++;
        }
      }

      log.info("Customer no :" + custNo);
      if (custNo > 0) {
        for (int j = 0; j < (custNo - 1); j++) {
          fc.getResultSender().sendResult(
              allCustomerList.get(j));
        }

        fc.getResultSender().lastResult(
            allCustomerList.get(custNo - 1));
      } else {
        log.warn("No matching customers found for " + city + "...");
        fc.getResultSender().lastResult(null);

      }
    } else {
      log.warn("This is not a region function...");
      fc.getResultSender().lastResult(null);
    }
  }

  public String getId() {
    return getClass().getName();
  }

  @Override
  public void init(Properties arg0) {

  }
}
