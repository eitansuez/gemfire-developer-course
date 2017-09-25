package io.pivotal.bookshop.domain;


import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor @Getter @Setter
@EqualsAndHashCode(of = {"customerNumber"})
@ToString(of = {"customerNumber", "firstName", "lastName"})
@AllArgsConstructor
@Builder
public class Customer implements Serializable {
  private static final long serialVersionUID = 7526471155622776147L;

  private long customerNumber;
  private String firstName, lastName;
  @Singular
  private List<Address> addresses;
  @Singular
  private List<Long> bookOrders;

  public void addAddress(Address address) {
    List<Address> addresses = new ArrayList<>();
    addresses.addAll(getAddresses());
    addresses.add(address);
    setAddresses(addresses);
  }

}
