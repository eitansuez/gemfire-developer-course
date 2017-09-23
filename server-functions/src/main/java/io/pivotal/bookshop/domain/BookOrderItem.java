package io.pivotal.bookshop.domain;

import lombok.*;

@NoArgsConstructor @Getter @Setter
@ToString
@AllArgsConstructor
@Builder
public class BookOrderItem {
  private static final long serialVersionUID = 7526471155622776147L;

  private int orderLine;
  private long itemNumber;
  @Builder.Default private int quantity = 1;
  @Builder.Default private float discount = 0f;

}
