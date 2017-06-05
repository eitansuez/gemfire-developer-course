package io.pivotal.bookshop.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class Order implements Serializable {
  private static final long serialVersionUID = 7526471155622776147L;

  private String orderNumber;
  private Date orderDate;
  private ArrayList<ProductItem> orderItems = new ArrayList<>();
  private String customerNumber;
  private float totalPrice;

  public Order(String orderNumber, Date orderDate, ArrayList<ProductItem> orderItems, String customerNumber, float totalPrice) {
    this.orderNumber = orderNumber;
    this.orderDate = orderDate;
    this.orderItems = orderItems;
    this.customerNumber = customerNumber;
    this.totalPrice = totalPrice;
  }

  public void addOrderItem(ProductItem item) {
    orderItems.add(item);
  }

}
