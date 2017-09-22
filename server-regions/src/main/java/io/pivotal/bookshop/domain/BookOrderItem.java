package io.pivotal.bookshop.domain;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor @Getter @Setter
@ToString(of={"orderLine", "itemNumber", "quantity", "discount"})
@AllArgsConstructor
@Builder
public class BookOrderItem implements Serializable {

  private static final long serialVersionUID = 7526471155622776147L;

  private long itemNumber;
  private int orderLine;
  @Builder.Default private int quantity = 1;
  @Builder.Default private float discount = 0f;

}
