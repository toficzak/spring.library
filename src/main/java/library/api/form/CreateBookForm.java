package library.api.form;

import javax.validation.constraints.NotBlank;

public class CreateBookForm {

  @NotBlank
  public String title;
}
