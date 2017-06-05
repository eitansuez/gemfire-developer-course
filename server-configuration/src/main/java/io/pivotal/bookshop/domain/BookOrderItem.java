package io.pivotal.bookshop.domain;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor @Getter @Setter
@EqualsAndHashCode
@ToString(of={"orderLine", "itemNumber", "quantity", "discount"})
public class BookOrderItem implements Serializable {

  private static final long serialVersionUID = 7526471155622776147L;

  private int orderLine;
  private long itemNumber;
  private float quantity;
  private float discount;

  public BookOrderItem(int orderLine, long itemNumber, float quantity, float discount) {
    this.orderLine = orderLine;
    this.itemNumber = itemNumber;
    this.quantity = quantity;
    this.discount = discount;
  }

}
