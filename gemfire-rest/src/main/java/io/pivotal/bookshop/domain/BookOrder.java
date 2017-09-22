package io.pivotal.bookshop.domain;

import lombok.*;

import java.util.ArrayList;
import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = {"orderNumber"})
@ToString
@AllArgsConstructor
@Builder
public class BookOrder {
  private static final long serialVersionUID = 7526471155622776147L;

  private long orderNumber;
  private long customerNumber;

  // TODO-08: Add the appropriate JSON Formatting annotation to tell the converter how to interpret
  //          the date from the JSON object being returned. Repeat for the shipDate field below as well.
  private Date orderDate;
  private Date shipDate;

  private final ArrayList<BookOrderItem> orderItems = new ArrayList<>();

  private float totalPrice;
  private float shippingCost;

  public void addOrderItem(BookOrderItem item) {
    orderItems.add(item);
  }

}
