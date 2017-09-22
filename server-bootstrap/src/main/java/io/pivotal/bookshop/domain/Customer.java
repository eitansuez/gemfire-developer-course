package io.pivotal.bookshop.domain;


import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of = {"customerNumber"})
@ToString(of = {"customerNumber", "firstName", "lastName"})
@Builder
public class Customer implements Serializable {
  private static final long serialVersionUID = 7526471155622776147L;

  private long customerNumber;
  private String firstName, lastName;
  private Address primaryAddress;
  @Singular
  private List<Long> bookOrders;

}
