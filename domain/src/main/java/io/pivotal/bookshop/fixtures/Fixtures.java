package io.pivotal.bookshop.fixtures;

import io.pivotal.bookshop.domain.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Fixtures {

  public static List<BookOrder> someOrders() {

    BookOrder order1 = BookOrder.builder()
        .orderNumber(17699L)
        .orderDate(new Date())
        .totalPrice(40.98f)
        .shipDate(new Date())
        .shippingCost(5.99f)
        .customerNumber(5598L) // Kari Powell
        .orderItem(BookOrderItem.builder().orderLine(1)
            .itemNumber(123) // A Treatise of Treatises
            .build())
        .build();

    BookOrder order2 = BookOrder.builder()
        .orderNumber(17700L)
        .orderDate(new Date())
        .totalPrice(52.97f)
        .shipDate(new Date())
        .shippingCost(5.99f)
        .customerNumber(5543L) // Lula Wax
        .orderItem(BookOrderItem.builder().orderLine(1)
            .itemNumber(123) // A Treatise of Treatises
            .build())
        .orderItem(BookOrderItem.builder().orderLine(2)
            .itemNumber(456) // Clifford the Big Red Dog
            .build())
        .build();

    return Arrays.asList(order1, order2);
  }

  public static List<InventoryItem> someInventoryItems() {
    InventoryItem item1 = InventoryItem.builder().itemNumber(123)
        .costToXYZ(12.5f).priceToCustomer(34.99f)
        .quantityInStock(12)
        .vendor("BooksRUs").location("Seattle").build();

    InventoryItem item2 = InventoryItem.builder().itemNumber(456)
        .costToXYZ(12.50f).priceToCustomer(11.99f)
        .quantityInStock(1)
        .vendor("BooksRUs").location("Seattle").build();

    return Arrays.asList(item1, item2);
  }

  public static List<Customer> someCustomers() {

    Customer cust1 = Customer.builder()
        .customerNumber(5598)
        .firstName("Kari")
        .lastName("Powell")
        .address(Address.builder()
            .addressLine1("123 Main St")
            .city("Topeka")
            .state("KS")
            .postalCode("50505")
            .country("US")
            .phoneNumber("(423) 555-3322")
            .addressTag("HOME")
            .build())
        .bookOrder(18009L).bookOrder(18049L).bookOrder(17699L)
        .build();

    Customer cust2 = Customer.builder()
        .customerNumber(5543)
        .firstName("Lula")
        .lastName("Wax")
        .address(Address.builder()
            .addressLine1("123 Main St")
            .city("Topeka")
            .state("KS")
            .postalCode("50505")
            .country("US")
            .phoneNumber("(423) 555-3322")
            .addressTag("HOME")
            .build())
        .bookOrder(17700L)
        .build();

    Customer cust3 = Customer.builder()
        .customerNumber(6024L)
        .firstName("Trenton")
        .lastName("Garcia")
        .address(
            Address.builder()
                .addressLine1("123 Main St")
                .city("San Francisco")
                .state("CA")
                .postalCode("50505")
                .country("US")
                .phoneNumber("(423) 555-3322")
                .addressTag("HOME")
                .build()
        )
        .build();

    return Arrays.asList(cust1, cust2, cust3);

  }

  public static List<Book> someBooks() {
    Book book = Book.builder()
        .itemNumber(123L)
        .author("Daisy Mae West")
        .title("A Treatise of Treatises")
        .description("Run on sentences and drivel on all things mundane")
        .yearPublished(2011)
        .retailCost(34.99f)
        .build();

    Book book2 = Book.builder()
        .itemNumber(456L)
        .author("Clarence Meeks")
        .title("Clifford the Big Red Dog")
        .description("A book about a dog")
        .yearPublished(1971)
        .retailCost(11.99f)
        .build();

    Book book3 = Book.builder()
        .itemNumber(789L)
        .author("Jim Heavisides")
        .title("Operating Systems: An Introduction")
        .description("Theoretical information about the structure of Operating Systems")
        .yearPublished(2011)
        .retailCost(59.99f)
        .build();

    return Arrays.asList(book, book2, book3);
  }
}
