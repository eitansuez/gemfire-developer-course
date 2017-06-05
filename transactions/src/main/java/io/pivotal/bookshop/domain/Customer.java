package io.pivotal.bookshop.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;

@NoArgsConstructor
@Getter
@Setter
@ToString(of={"customerNumber", "firstName", "lastName", "phoneNumber"})
public class Customer implements Serializable {
  private static final long serialVersionUID = 7526471155622776147L;

  private String customerNumber;
  private String firstName;
  private String lastName;
  private String phoneNumber;
  private Address address;
  private ArrayList<Order> myOrders = new ArrayList<>();

  public Customer(String customerNumber, String firstName, String lastName, String phone) {
    this.customerNumber = customerNumber;
    this.firstName = firstName;
    this.lastName = lastName;
    this.phoneNumber = phone;
  }

  public void addAddress(Address addr) {
    if (address == null) {
      address = addr;
    }
  }

  public void addOrder(Order order) {
    myOrders.add(order);
  }

}
