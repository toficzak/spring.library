package library.domain.customer;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import org.springframework.security.crypto.bcrypt.BCrypt;
import library.api.dto.CustomerDto;
import library.domain.customer.role.Role;
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

  @NotNull
  @Past
  private LocalDateTime lastPasswordUpdate = LocalDateTime.now();

  private String resetPasswordHash;

  private String activationHash;
  private boolean active;
  private LocalDateTime activation;

  @OneToMany(mappedBy = "customer")
  private List<Rental> rentals;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "USER_ROLES", joinColumns = {
      @JoinColumn(name = "USER_ID")},
      inverseJoinColumns = {
          @JoinColumn(name = "ROLE_ID")})
  private Set<Role> roles;

  public Customer() {}

  public Customer(
      @NotBlank String firstName,
      @NotBlank String lastName,
      @Email @NotBlank String email,
      @NotBlank String password,
      @NotEmpty Set<Role> roles) {
    super();
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.password = this.hashPassword(password);
    this.roles = roles;
    this.activationHash = UUID.randomUUID().toString();
  }

  public CustomerDto viewModel() {
    Set<String> rolesNames = new HashSet<>();
    this.roles.stream()
        .map(Role::getName)
        .forEach(rolesNames::add);
    return new CustomerDto(this.getName(), rolesNames, this.active);
  }

  public String getName() {
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

  public Set<Role> getRoles() {
    return Collections.unmodifiableSet(this.roles);
  }

  public void generateResetPasswordHash() {
    this.resetPasswordHash = UUID.randomUUID().toString();
  }

  public void changePassword(String password) {
    this.password = this.hashPassword(password);
    this.resetPasswordHash = null;
    this.lastPasswordUpdate = LocalDateTime.now();
  }

  public String getResetPasswordHash() {
    return resetPasswordHash;
  }

  public void activate() {
    this.active = true;
    this.activationHash = null;
    this.activation = LocalDateTime.now();
  }

  private String hashPassword(String password) {
    return BCrypt.hashpw(password, BCrypt.gensalt(15));
  }

  public String getActivationHash() {
    return this.activationHash;
  }

}
