package library.domain.customer;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import org.springframework.security.crypto.bcrypt.BCrypt;
import library.api.dto.CustomerDto;
import library.domain.rental.Rental;

@Entity
public class Customer {

  @Id
  @GeneratedValue
  private Long id;

  @NotBlank
  private String firstName;

  @NotBlank
  private String lastName;

  @Email
  @NotBlank
  private String email;

  @NotBlank
  private String password;

  @OneToMany(mappedBy = "customer")
  private List<Rental> rentals;

  public Customer() {}

  public Customer(
      @NotBlank String firstName,
      @NotBlank String lastName,
      @Email @NotBlank String email,
      @NotBlank String password) {
    super();
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.password = BCrypt.hashpw(password, BCrypt.gensalt(15));
  }

  public CustomerDto viewModel() {
    return new CustomerDto(this.getName());
  }

  private String getName() {
    return this.firstName + " " + this.lastName;
  }

  public Long getId() {
    return this.id;
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }


}
