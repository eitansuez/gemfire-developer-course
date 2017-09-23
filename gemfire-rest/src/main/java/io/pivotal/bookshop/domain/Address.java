package io.pivotal.bookshop.domain;

import lombok.*;

@NoArgsConstructor @Getter @Setter
@ToString(of={"addressLine1", "city", "state", "postalCode", "country", "phoneNumber"})
@AllArgsConstructor
@Builder
public class Address {

  private static final long serialVersionUID = 7526471155622776147L;

  private String addressLine1, addressLine2, addressLine3;
  private String city, state, postalCode;
  private String country;
  private String phoneNumber;
  private String addressTag;

}
