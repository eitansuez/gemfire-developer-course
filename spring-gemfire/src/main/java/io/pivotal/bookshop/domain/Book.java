package io.pivotal.bookshop.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.gemfire.mapping.annotation.Region;

import java.io.Serializable;

//TODO-09: Annotate the Book class to declare the region it's associated with
//TODO-10: Create a repository interface in the io.pivotal.bookshop.buslogic package that implements CrudRepository
//       Declare a 'findBy' method that will find all Book items that match the specified author.

@Region("Book")
@NoArgsConstructor @Getter @Setter
@EqualsAndHashCode(of = {"itemNumber"})
@ToString(of = {"itemNumber", "title", "author"})
@AllArgsConstructor
@Builder(toBuilder = true)
public class Book implements Serializable {
  private static final long serialVersionUID = 7526471155622776147L;

  @Id private long itemNumber;
  private String author, title, description;
  private float retailCost;
  private int yearPublished;

}
