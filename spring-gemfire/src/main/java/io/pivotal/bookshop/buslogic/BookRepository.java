package io.pivotal.bookshop.buslogic;

import io.pivotal.bookshop.domain.Book;
import org.springframework.data.gemfire.repository.GemfireRepository;

import java.util.List;

public interface BookRepository extends GemfireRepository<Book, Long> {

  List<Book> findByAuthor(String author);
}
