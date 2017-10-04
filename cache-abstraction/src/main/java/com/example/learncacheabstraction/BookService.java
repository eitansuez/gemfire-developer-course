package com.example.learncacheabstraction;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class BookService {

  @Cacheable("test_region")
  public Book fetchBook(Long id) {
    return new Book("My "+id+"th book", "Eitan");
  }
}
