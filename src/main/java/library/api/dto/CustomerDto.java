package library.api.dto;

import java.util.Set;

public class CustomerDto {

  public String name;
  public Set<String> roles;

  public CustomerDto(String name, Set<String> roles) {
    super();
    this.name = name;
    this.roles = roles;
  }

}
