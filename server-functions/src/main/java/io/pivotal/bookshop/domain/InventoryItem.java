package io.pivotal.bookshop.domain;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
@ToString(of = {"itemNumber", "quantityInStock", "location"})
@EqualsAndHashCode(of = {"itemNumber"})
public class InventoryItem implements Serializable {
  private static final long serialVersionUID = 7526471155622776147L;

  private long itemNumber;
  private float costToXYZ, priceToCustomer;
  private int quantityInStock;
  private String vendor, location;

  public InventoryItem(long itemNumber, float costToXYZ,
                       float priceToCustomer, int quantityInStock, String vendor,
                       String location) {
    this.itemNumber = itemNumber;
    this.costToXYZ = costToXYZ;
    this.priceToCustomer = priceToCustomer;
    this.quantityInStock = quantityInStock;
    this.vendor = vendor;
    this.location = location;
  }

}
