package io.pivotal.bookshop.domain;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor @Getter @Setter
@ToString
@AllArgsConstructor
@Builder
public class BookOrderItem implements Serializable {
  private static final long serialVersionUID = 7526471155622776147L;

  private int orderLine;
  private long itemNumber;
  private int quantity;
  private float discount;

}
