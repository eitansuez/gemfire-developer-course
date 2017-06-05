package io.pivotal.bookshop.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

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
    assertNotEquals(customer, customer2);
  }

  @Test
  public void twoCustomersWithSameNumberShouldBeEqual() {
    assertEquals(customer, customer3);
  }

  @Test
  public void customerToStringShouldIncludeCustomerNumberAndNameFields() {
    String toString = customer.toString();
    assertTrue(toString.contains("Eitan"));
    assertTrue(toString.contains("Suez"));
    assertTrue(toString.contains("123"));
    System.out.println(toString);
  }
}
