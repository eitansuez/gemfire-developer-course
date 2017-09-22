package io.pivotal.bookshop.domain;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor @Getter @Setter
@ToString(of={"addressLine1", "city", "state", "postalCode", "country", "phoneNumber"})
@AllArgsConstructor
@Builder
public class Address implements Serializable {

  private static final long serialVersionUID = 7526471155622776147L;

  private String addressLine1, addressLine2, addressLine3;
  private String city, state, postalCode;
  private String country;
  private String phoneNumber;
  private String addressTag;
}
