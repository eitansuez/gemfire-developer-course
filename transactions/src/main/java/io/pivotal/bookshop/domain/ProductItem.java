package io.pivotal.bookshop.domain;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor @Getter @Setter
@ToString
@AllArgsConstructor
@Builder
public class ProductItem implements Serializable {

  private static final long serialVersionUID = 7526471155622776147L;

  private String itemNumber;
  private String description;
  private float retailCost;

}
