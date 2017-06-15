package io.pivotal.bookshop.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.apache.geode.DataSerializable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.Serializable;

@NoArgsConstructor @Getter @Setter
@ToString(of={"addressLine1", "city", "state", "postalCode", "country"})
public class Address implements DataSerializable {
  private static final long serialVersionUID = 7526471155622776147L;

  private String addressLine1, addressLine2, addressLine3;
  private String city, state, postalCode;
  private String country;
  private String phoneNumber;
  private String addressTag;

  public Address(String postalCode) {
    this.postalCode = postalCode;
  }

  public Address(String addressLine1, String addressLine2, String addressLine3,
                 String city, String state, String postalCode,
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

  @Override
  public void toData(DataOutput out) throws IOException {
    out.writeUTF(addressLine1);
    out.writeUTF(addressLine2);
    out.writeUTF(addressLine3);
    out.writeUTF(city);
    out.writeUTF(state);
    out.writeUTF(postalCode);
    out.writeUTF(country);
    out.writeUTF(phoneNumber);
    out.writeUTF(addressTag);
  }

  @Override
  public void fromData(DataInput in) throws IOException, ClassNotFoundException {
    this.addressLine1 = in.readUTF();
    this.addressLine2 = in.readUTF();
    this.addressLine3 = in.readUTF();
    this.city = in.readUTF();
    this.state = in.readUTF();
    this.postalCode = in.readUTF();
    this.country = in.readUTF();
    this.phoneNumber = in.readUTF();
    this.addressTag = in.readUTF();
  }
}
