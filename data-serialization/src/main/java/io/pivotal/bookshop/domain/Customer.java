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
public class Customer {
  private long customerNumber;
  private String firstName;
  private String lastName;
  private Address primaryAddress;
  private ArrayList<Long> myBookOrders = new ArrayList<>();

  // TODO-07: Add a new field called telephoneNumber of type String. Also add setter and getter as well as adding
  //          to the toString() method.


  public Customer(long customerNumber, String firstName, String lastName) {
    this.customerNumber = customerNumber;
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public Customer(long customerNumber, String firstName, String lastName, String postalCode) {
    this(customerNumber, firstName, lastName);
    this.primaryAddress = Address.builder().postalCode(postalCode).build();
  }

  public Customer(long customerNumber, String firstName,
                  String lastName, Address address,
                  ArrayList<Long> orders) {
    this(customerNumber, firstName, lastName);
    this.primaryAddress = address;
    this.myBookOrders = orders;
  }

  public Customer(long customerNumber, String firstName,
                  String lastName, Address address) {
    this.customerNumber = customerNumber;
    this.firstName = firstName;
    this.lastName = lastName;
    this.primaryAddress = address;
  }

  public void addOrder(long orderKey) {
    if (myBookOrders == null) {
      myBookOrders = new ArrayList<>();
    }

    myBookOrders.add(orderKey);
  }

}
