package io.pivotal.bookshop.domain;


import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"customerNumber"})
@ToString(of = {"customerNumber", "firstName", "lastName"})
@AllArgsConstructor
@Builder
public class Customer implements Serializable {

  private static final long serialVersionUID = 7526471155622776147L;

  private long customerNumber;
  private String firstName, lastName;
  private final ArrayList<Address> addresses = new ArrayList<>();
  private final ArrayList<Long> myBookOrders = new ArrayList<>();

  public void addAddress(Address addr) {
    addresses.add(addr);
  }

  public void addOrder(long orderKey) {
    myBookOrders.add(orderKey);
  }

}
