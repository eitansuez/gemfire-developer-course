package io.pivotal.bookshop.domain;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor @Getter @Setter
@EqualsAndHashCode
@ToString(of={"orderLine", "itemNumber", "quantity", "discount"})
@AllArgsConstructor
@Builder
public class BookOrderItem implements Serializable {

  private static final long serialVersionUID = 7526471155622776147L;

  private int orderLine;
  private long itemNumber;
  @Builder.Default private float quantity = 1;
  @Builder.Default private float discount = 0f;

}
