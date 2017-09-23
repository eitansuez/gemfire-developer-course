package io.pivotal.bookshop.domain;

import lombok.*;
import org.apache.geode.DataSerializable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

@NoArgsConstructor @Getter @Setter
@ToString(of = {"addressLine1", "city", "state", "postalCode", "country"})
@AllArgsConstructor
@Builder
public class Address implements DataSerializable {
  private static final long serialVersionUID = 7526471155622776147L;

  private String addressLine1, addressLine2, addressLine3;
  private String city, state, postalCode;
  private String country;
  private String phoneNumber;
  private String addressTag;

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
