package io.pivotal.bookshop.domain;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor @Getter @Setter
@EqualsAndHashCode(of = {"itemNumber"})
@ToString(of = {"itemNumber", "title", "author"})
@AllArgsConstructor
@Builder(toBuilder = true)
public class Book implements Serializable {
  private static final long serialVersionUID = 7526471155622776147L;

  private long itemNumber;
  private String author, title, description;
  private float retailCost;
  private int yearPublished;

}
