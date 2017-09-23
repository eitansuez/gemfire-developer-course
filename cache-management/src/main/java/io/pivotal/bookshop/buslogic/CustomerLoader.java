package io.pivotal.bookshop.buslogic;

import io.pivotal.bookshop.domain.Address;
import io.pivotal.bookshop.domain.Customer;
import lombok.extern.log4j.Log4j2;
import org.apache.geode.cache.Region;

@Log4j2
class CustomerLoader {

  static void populateCustomerRegion(Region<Long, Customer> customerRegion) {
    Customer cust1 = Customer.builder().customerNumber(5540)
        .firstName("Lula").lastName("Wax")
        .address(Address.builder()
            .addressLine1("123 Main St")
            .city("Topeka").state("KS").postalCode("50505")
            .country("US").phoneNumber("423 555-3322").addressTag("HOME").build())
        .bookOrder(17699L)
        .build();
    customerRegion.put(5540L, cust1);

    Customer cust2 = Customer.builder().customerNumber(5541)
        .firstName("Tom").lastName("Mcginns")
        .address(Address.builder()
            .addressLine1("123 Main St")
            .city("San Francisco").state("CA").postalCode("50505")
            .country("US").phoneNumber("423 555-3322").addressTag("HOME").build())
        .bookOrder(17699L)
        .build();
    customerRegion.put(5541L, cust2);

    Customer cust3 = Customer.builder().customerNumber(5542)
        .firstName("Peter").lastName("Fernandez")
        .address(Address.builder()
            .addressLine1("123 Main St")
            .city("San Francisco").state("CA").postalCode("50505")
            .country("US").phoneNumber("423 555-3322").addressTag("HOME").build())
        .bookOrder(17699L)
        .build();
    customerRegion.put(5542L, cust3);

    Customer cust4 = Customer.builder().customerNumber(5543)
        .firstName("Jenny").lastName("Tsai")
        .address(Address.builder()
            .addressLine1("123 Main St")
            .city("Topeka").state("CA").postalCode("50505")
            .country("US").phoneNumber("423 555-3322").addressTag("HOME").build())
        .bookOrder(17699L)
        .build();
    customerRegion.put(5543L, cust4);

    log.info("************ Loaded customers data ****************");
  }
}
