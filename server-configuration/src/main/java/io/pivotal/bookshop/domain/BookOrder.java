package io.pivotal.bookshop.domain;

import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

@NoArgsConstructor @Getter @Setter
@ToString(of={"orderNumber", "orderDate", "customerNumber", "totalPrice"})
@EqualsAndHashCode(of={"orderNumber"})
@AllArgsConstructor
@Builder
public class BookOrder implements Serializable {

  private static final long serialVersionUID = 7526471155622776147L;

  private Long orderNumber;
  private Date orderDate;
  private final ArrayList<BookOrderItem> orderItems = new ArrayList<>();
  private Long customerNumber;

  private float totalPrice;

  private Date shipDate;
  private float shippingCost;

  public void addOrderItem(BookOrderItem item) {
    orderItems.add(item);
  }

}
