package io.pivotal.bookshop.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString(of={"orderLine", "itemNumber", "quantity", "discount"})
public class BookOrderItem {
  private static final long serialVersionUID = 7526471155622776147L;

  private int orderLine;
  private long itemNumber;
  private int quantity;
  private float discount;

  public BookOrderItem(int orderLine, long itemNumber, int quantity, float discount) {
    this.orderLine = orderLine;
    this.itemNumber = itemNumber;
    this.quantity = quantity;
    this.discount = discount;
  }

}
