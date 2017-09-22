package io.pivotal.bookshop.domain;

import lombok.*;

import java.util.ArrayList;
import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"orderNumber"})
@AllArgsConstructor
@Builder
public class BookOrder {
  private static final long serialVersionUID = 7526471155622776147L;

  private long orderNumber;
  private long customerNumber;
  private Date orderDate, shipDate;
  private final ArrayList<BookOrderItem> orderItems = new ArrayList<>();
  private float totalPrice, shippingCost;

  public void addOrderItem(BookOrderItem item) {
    orderItems.add(item);
  }

}
