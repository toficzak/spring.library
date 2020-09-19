package library.api.dto;

import java.util.Set;

public class CustomerDto {

  public String name;
  public Set<String> roles;
  public boolean active;

  public CustomerDto(String name, Set<String> roles, boolean active) {
    super();
    this.name = name;
    this.roles = roles;
    this.active = active;
  }

}
