package io.pivotal.bookshop.domain;


import lombok.*;

import java.util.ArrayList;

/**
 * TODO-01: Note that this class does not implement java.io.Serializable. Also note that one of the fields is an instance
 * of a complex object Address.
 */
@NoArgsConstructor @Getter @Setter
@EqualsAndHashCode(of={"customerNumber"})
@ToString(of={"customerNumber", "firstName", "lastName"})
@AllArgsConstructor
@Builder
public class Customer {
  private long customerNumber;
  private String firstName;
  private String lastName;
  private Address primaryAddress;
  private final ArrayList<Long> myBookOrders = new ArrayList<>();

  // TODO-07: Add a new field called telephoneNumber of type String. Also add setter and getter as well as adding
  //          to the toString() method.

  public void addOrder(long orderKey) {
    myBookOrders.add(orderKey);
  }

}
