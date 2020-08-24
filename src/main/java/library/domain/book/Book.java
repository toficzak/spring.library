package library.domain.book;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import library.api.dto.BookDto;
import library.api.form.CreateBookForm;
import library.domain.rental.Rental;

@Entity
public class Book {
  @Id
  @GeneratedValue
  private Long id;
  @NotNull
  private String title;

  @ManyToOne
  private Rental rental;

  public Book() {}

  public Book(@NotNull CreateBookForm form) {
    this.title = form.title;
  }

  public BookDto viewModel() {
    return new BookDto(this.title);
  }
}
