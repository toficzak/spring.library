package library.api.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import library.domain.book.Author;

public class CreateBookForm {

  @NotBlank
  public String title;

  @NotNull
  public Long authorId;
  public Author author;
}
