package io.pivotal.bookshop.domain;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor @Getter @Setter
@EqualsAndHashCode
@ToString(of = {"itemNumber", "quantityInStock", "location"})
@AllArgsConstructor
@Builder
public class InventoryItem implements Serializable {

  private static final long serialVersionUID = 7526471155622776147L;

  private long itemNumber;
  private int quantityInStock;
  private float costToXYZ, priceToCustomer;
  private String vendor, location;

}
