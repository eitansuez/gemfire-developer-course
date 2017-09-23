package io.pivotal.bookshop.domain;

import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor @Getter @Setter
@ToString
@AllArgsConstructor
@Builder
public class Order implements Serializable {
  private static final long serialVersionUID = 7526471155622776147L;

  private String orderNumber;
  private Date orderDate;
  @Singular
  private List<ProductItem> items;
  private String customerNumber;
  private float totalPrice;

}
