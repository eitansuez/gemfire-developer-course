package io.pivotal.bookshop.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@NoArgsConstructor @Getter @Setter
@ToString(of={"addressLine1", "city", "state", "postalCode", "country", "phoneNumber"})
public class Address implements Serializable {
  private static final long serialVersionUID = 7526471155622776147L;

  private String addressLine1, addressLine2, addressLine3;
  private String city, state, postalCode;
  private String country;
  private String phoneNumber;
  private String addressTag;

  public Address(String postalCode) {
    this.postalCode = postalCode;
  }

  public Address(String addressLine1, String addressLine2,
                 String addressLine3, String city, String state, String postalCode,
                 String country, String phoneNumber, String addressTag) {
    this.addressLine1 = addressLine1;
    this.addressLine2 = addressLine2;
    this.addressLine3 = addressLine3;
    this.city = city;
    this.state = state;
    this.postalCode = postalCode;
    this.country = country;
    this.phoneNumber = phoneNumber;
    this.addressTag = addressTag;
  }

}
