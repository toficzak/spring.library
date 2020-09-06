package library.domain.customer.role;

import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Role {

  public static final Set<String> ADMIN_ROLES = Set.of(RoleName.ADMIN, RoleName.CUSTOMER).stream()
      .map(Enum::name)
      .collect(Collectors.toSet());
  public static final Set<String> CUSTOMER_ROLES = Set.of(RoleName.CUSTOMER).stream()
      .map(Enum::name)
      .collect(Collectors.toSet());

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Column
  private String name;

  @Column
  private String description;

  public Role() {}

  public Role(String name, String description) {
    super();
    this.name = name;
    this.description = description;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

}
