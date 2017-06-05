package io.pivotal.bookshop.domain;

import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

@NoArgsConstructor @Getter @Setter
@ToString(of={"orderNumber", "orderDate", "customerNumber", "totalPrice"})
@EqualsAndHashCode(of={"orderNumber"})
public class BookOrder implements Serializable {

  private static final long serialVersionUID = 7526471155622776147L;

  private Long orderNumber;
  private Date orderDate;
  private ArrayList<BookOrderItem> orderItems = new ArrayList<>();
  private Long customerNumber;

  private float totalPrice;

  private Date shipDate;
  private float shippingCost;

  public BookOrder(long orderNumber, Date orderDate, float shippingCost,
                   Date shipDate, ArrayList<BookOrderItem> orderItems, long customerNumber,
                   float totalPrice) {
    this.orderNumber = orderNumber;
    this.orderDate = orderDate;
    this.shippingCost = shippingCost;
    this.shipDate = shipDate;
    this.orderItems = orderItems;
    this.customerNumber = customerNumber;
    this.totalPrice = totalPrice;
  }

  public void addOrderItem(BookOrderItem item) {
    orderItems.add(item);
  }

}
