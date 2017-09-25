package io.pivotal.bookshop.tests;

import io.pivotal.bookshop.domain.BookOrder;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;


public class RestClientTest {

  @Test
  public void testGetBookOrder() {

    String url = "http://localhost:8080/gemfire-api/v1/BookOrder/{id}";
    RestTemplate restTemplate = new RestTemplate();
    BookOrder bookOrder = restTemplate.getForObject(url, BookOrder.class, "17700");

    assertThat(bookOrder.getCustomerNumber()).isEqualTo(5543L);

  }

}
