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
  private Address primaryAddress;
  private ArrayList<Long> myBookOrders = new ArrayList<>();

  public Customer(long customerNumber, String firstName, String lastName) {
    this.customerNumber = customerNumber;
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public Customer(long customerNumber, String firstName, String lastName, String postalCode) {
    this(customerNumber, firstName, lastName);
    this.primaryAddress = Address.builder().postalCode(postalCode).build();
  }

  public Customer(long customerNumber, String firstName, String lastName, Address address, ArrayList<Long> orders) {
    this(customerNumber, firstName, lastName);
    this.primaryAddress = address;
    this.myBookOrders = orders;
  }

  public Customer(long customerNumber, String firstName, String lastName, Address address) {
    this(customerNumber, firstName, lastName);
    this.primaryAddress = address;
  }

  public void addOrder(long orderKey) {
    myBookOrders.add(orderKey);
  }

}
