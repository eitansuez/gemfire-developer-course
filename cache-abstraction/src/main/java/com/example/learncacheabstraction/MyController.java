package com.example.learncacheabstraction;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

  private BookService bookService;

  public MyController(BookService bookService) {
    this.bookService = bookService;
  }

  @RequestMapping("/books/{id}")
  public Book fetchBook(@PathVariable("id") Long id) {
    return bookService.fetchBook(id);
  }
}
