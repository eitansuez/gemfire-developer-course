package io.pivotal.bookshop.domain;

import lombok.*;
import org.apache.geode.DataSerializable;
import org.apache.geode.DataSerializer;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor @Getter @Setter
@EqualsAndHashCode(of={"customerNumber"})
@ToString(of={"customerNumber", "firstName", "lastName"})
@AllArgsConstructor
@Builder
public class Customer implements DataSerializable {
  private static final long serialVersionUID = 7526471155622776147L;

  private long customerNumber;
  private String firstName, lastName;
  @Singular
  private List<Address> addresses;

  @Override
  public void toData(DataOutput out) throws IOException {
    out.writeLong(customerNumber);
    out.writeUTF(firstName);
    out.writeUTF(lastName);
    ArrayList<Address> castAddresses = new ArrayList<>();
    castAddresses.addAll(addresses);
    DataSerializer.writeArrayList(castAddresses, out);
  }

  @Override
  public void fromData(DataInput in) throws IOException, ClassNotFoundException {
    this.customerNumber = in.readLong();
    this.firstName = in.readUTF();
    this.lastName = in.readUTF();
    this.addresses = DataSerializer.readArrayList(in);
  }
}
