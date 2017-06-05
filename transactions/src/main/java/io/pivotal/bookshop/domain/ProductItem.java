package io.pivotal.bookshop.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class ProductItem implements Serializable {

  private static final long serialVersionUID = 7526471155622776147L;

  private String itemNumber;
  private String description;
  private float retailCost;

  public ProductItem(String itemNumber, String description, float retailCost) {
    this.itemNumber = itemNumber;
    this.description = description;
    this.retailCost = retailCost;
  }

}
