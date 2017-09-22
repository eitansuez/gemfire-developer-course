package io.pivotal.bookshop.domain;

import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;

@NoArgsConstructor
@Getter
@Setter
@ToString(of={"customerNumber", "firstName", "lastName", "phoneNumber"})
@AllArgsConstructor
@Builder
public class Customer implements Serializable {
  private static final long serialVersionUID = 7526471155622776147L;

  private String customerNumber;
  private String firstName;
  private String lastName;
  private String phoneNumber;
  private Address address;
  private final ArrayList<Order> myOrders = new ArrayList<>();

  public void addOrder(Order order) {
    myOrders.add(order);
  }

}
