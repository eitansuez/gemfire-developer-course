package io.pivotal.bookshop.domain;


import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;

@NoArgsConstructor
@Getter
@Setter
@ToString(of = {"customerNumber", "firstName", "lastName"})
@EqualsAndHashCode(of = {"customerNumber"})
@AllArgsConstructor
@Builder
public class Customer implements Serializable {

  private static final long serialVersionUID = 7526471155622776147L;

  private long customerNumber;
  private String firstName, lastName;
  private Address primaryAddress;
  private final ArrayList<Long> myBookOrders = new ArrayList<>();

  public void addOrder(long orderKey) {
    myBookOrders.add(orderKey);
  }

}
