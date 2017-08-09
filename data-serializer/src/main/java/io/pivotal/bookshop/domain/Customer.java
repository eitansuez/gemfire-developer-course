package io.pivotal.bookshop.domain;

import lombok.*;
import org.apache.geode.DataSerializable;
import org.apache.geode.DataSerializer;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

@NoArgsConstructor @Getter @Setter
@EqualsAndHashCode(of={"customerNumber"})
@ToString(of={"customerNumber", "firstName", "lastName"})
public class Customer implements DataSerializable {
  private static final long serialVersionUID = 7526471155622776147L;

  private long customerNumber;
  private String firstName, lastName;
  private Address primaryAddress;

  public Customer(long customerNumber, String firstName, String lastName) {
    this.customerNumber = customerNumber;
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public Customer(long customerNumber, String firstName, String lastName, String postalCode) {
    this(customerNumber, firstName, lastName);
    this.primaryAddress = Address.builder().postalCode(postalCode).build();
  }

  public Customer(long customerNumber, String firstName, String lastName, Address address) {
    this.customerNumber = customerNumber;
    this.firstName = firstName;
    this.lastName = lastName;
    this.primaryAddress = address;
  }

  @Override
  public void toData(DataOutput out) throws IOException {
    out.writeLong(customerNumber);
    out.writeUTF(firstName);
    out.writeUTF(lastName);
    DataSerializer.writeObject(primaryAddress, out);
  }

  @Override
  public void fromData(DataInput in) throws IOException, ClassNotFoundException {
    this.customerNumber = in.readLong();
    this.firstName = in.readUTF();
    this.lastName = in.readUTF();
    this.primaryAddress = DataSerializer.readObject(in);
  }
}
