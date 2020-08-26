package library.domain.book;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import library.api.dto.BookDto;
import library.api.form.CreateBookForm;

@Entity
public class Book {
  @Id
  @GeneratedValue
  private Long id;
  @NotNull
  private String title;

  public Book() {}

  public Book(@NotNull CreateBookForm form) {
    this.title = form.title;
  }

  public BookDto viewModel() {
    return new BookDto(this.title);
  }

  public Long getId() {
    return this.id;
  }
}
