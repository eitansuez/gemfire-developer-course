package io.pivotal.bookshop.domain;

import lombok.*;

import java.io.Serializable;

//TODO-09: Annotate the Book class to declare the region it's associated with
//TODO-10: Create a repository interface in the io.pivotal.bookshop.buslogic package that implements CrudRepository
//       Declare a 'findBy' method that will find all Book items that match the specified author.

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of={"itemNumber"})
@ToString(of={"itemNumber", "title", "author"})
public class Book implements Serializable {

  private static final long serialVersionUID = 7526471155622776147L;

  private long itemNumber;
  private String author, title, description;
  private float retailCost;
  private int yearPublished;

  public Book(long itemNumber, String description, float retailCost,
              int yearPublished, String author, String title) {
    this.itemNumber = itemNumber;
    this.description = description;
    this.retailCost = retailCost;
    this.yearPublished = yearPublished;
    this.author = author;
    this.title = title;
  }

}
