package io.pivotal.bookshop.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor @Getter @Setter
@EqualsAndHashCode(of = {"orderNumber"})
@ToString(of={"orderNumber", "orderDate", "customerNumber", "totalPrice"})
@AllArgsConstructor
@Builder
public class BookOrder {
  private static final long serialVersionUID = 7526471155622776147L;

  private long orderNumber;
  private long customerNumber;

  // TODO-08: Add the appropriate JSON Formatting annotation to tell the converter how to interpret
  //          the date from the JSON object being returned. Repeat for the shipDate field below as well.
  @JsonFormat(pattern="MM/dd/yyyy")
  private Date orderDate;
  @JsonFormat(pattern="MM/dd/yyyy")
  private Date shipDate;
  private final List<BookOrderItem> orderItems = new ArrayList<>();
  private float shippingCost, totalPrice;

}
