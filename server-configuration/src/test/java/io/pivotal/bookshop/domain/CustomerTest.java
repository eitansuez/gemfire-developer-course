package io.pivotal.bookshop.domain;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CustomerTest {

  private Customer customer, customer2, customer3;

  @Before
  public void setup() {
    customer = new Customer();
    customer.setCustomerNumber(123L);
    customer.setFirstName("Eitan");
    customer.setLastName("Suez");

    customer2 = new Customer();
    customer2.setCustomerNumber(333L);
    customer2.setFirstName("John");
    customer2.setLastName("Doe");

    customer3 = new Customer();
    customer3.setCustomerNumber(123L);
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
