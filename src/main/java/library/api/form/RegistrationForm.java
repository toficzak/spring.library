package library.api.form;

import javax.validation.constraints.NotBlank;

public class RegistrationForm {

  @NotBlank
  public String email;
  @NotBlank
  public String firstName;
  @NotBlank
  public String lastName;
  @NotBlank
  public String password;
}
