package io.pivotal.bookshop.domain;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CustomerTest {

  private Customer customer, customer2, customer3;

  @Before
  public void setup() {
    customer = Customer.builder().customerNumber(123)
        .firstName("Eitan").lastName("Suez").build();

    customer2 = Customer.builder().customerNumber(333)
        .firstName("John").lastName("Doe").build();

    customer3 = Customer.builder().customerNumber(123).build();
  }

  @Test
  public void twoCustomersWithDifferentNumbersShouldNotBeEqual() {
    assertThat(customer).isNotEqualTo(customer2);
  }

  @Test
  public void twoCustomersWithSameNumberShouldBeEqual() {
    assertThat(customer).isEqualTo(customer3);
  }

  @Test
  public void customerToStringShouldIncludeCustomerNumberAndNameFields() {
    String toString = customer.toString();
    assertThat(toString).contains("Eitan");
    assertThat(toString).contains("Suez");
    assertThat(toString).contains("123");
    System.out.println(toString);
  }
}
