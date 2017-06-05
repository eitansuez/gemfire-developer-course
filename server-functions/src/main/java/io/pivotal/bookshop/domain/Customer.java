package io.pivotal.bookshop.domain;


import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"customerNumber"})
@ToString(of = {"customerNumber", "firstName", "lastName"})
public class Customer implements Serializable {

  private static final long serialVersionUID = 7526471155622776147L;

  private long customerNumber;
  private String firstName, lastName;
  private ArrayList<Address> addresses = new ArrayList<>();
  private ArrayList<Long> myBookOrders = new ArrayList<>();

  public Customer(long customerNumber, String firstName, String lastName, ArrayList<Address> addresses) {
    this.customerNumber = customerNumber;
    this.firstName = firstName;
    this.lastName = lastName;
    this.addresses = addresses;
  }

  public Customer(long customerNumber, String firstName, String lastName,
                  ArrayList<Address> addresses, ArrayList<Long> orders) {
    this(customerNumber, firstName, lastName, addresses);
    this.myBookOrders = orders;
  }

  public void addAddress(Address addr) {
    addresses.add(addr);
  }

  public void addOrder(long orderKey) {
    myBookOrders.add(orderKey);
  }

}
