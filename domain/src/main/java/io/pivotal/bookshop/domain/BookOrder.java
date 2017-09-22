package io.pivotal.bookshop.domain;

import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@NoArgsConstructor @Getter @Setter
@EqualsAndHashCode(of={"orderNumber"})
@ToString(of={"orderNumber", "orderDate", "customerNumber", "totalPrice"})
@AllArgsConstructor
@Builder
public class BookOrder implements Serializable {

  private static final long serialVersionUID = 7526471155622776147L;

  private long orderNumber;
  private long customerNumber;
  private Date orderDate, shipDate;
  @Singular private List<BookOrderItem> orderItems;
  private float shippingCost, totalPrice;

}
