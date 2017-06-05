package io.pivotal.bookshop.domain;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor @Getter @Setter
@EqualsAndHashCode(of={"itemNumber"})
@ToString(of={"itemNumber", "title", "author"})
public class Book implements Serializable {

  private static final long serialVersionUID = 7526471155622776147L;

  private long itemNumber;
  private float retailCost;
  private int yearPublished;
  private String author, title, description;

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
