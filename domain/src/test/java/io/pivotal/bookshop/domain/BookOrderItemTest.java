package io.pivotal.bookshop.domain;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BookOrderItemTest {

  @Test
  public void buildShouldDefaultQuantityAndDiscountValues() {
    BookOrderItem orderItem = BookOrderItem.builder()
        .orderLine(1)
        .itemNumber(123)
        .build();

    assertThat(orderItem.getQuantity()).isEqualTo(1);
    assertThat(orderItem.getDiscount()).isEqualTo(0);
  }
}
