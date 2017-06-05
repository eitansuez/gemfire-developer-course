package io.pivotal.bookshop.domain;

import lombok.*;

import java.io.Serializable;

// TODO-02a: Open this key class and observe the components of the key.
// TODO-02b: Notice the implementation of hashCode() and equals(). The customerId attribute is only used for partitioning while
//           the orderNumber is used for equality and hashCode.
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"orderNumber"})
@ToString
public class OrderKey implements Serializable {
  private static final long serialVersionUID = 1L;

  private long customerNumber;
  private long orderNumber;

  public OrderKey(long customerNumber, long orderNumber) {
    this.orderNumber = orderNumber;
    this.customerNumber = customerNumber;
  }

}
