package library.domain.book;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

@Entity
public class Author {

  @Id
  @GeneratedValue
  private Long id;

  @NotBlank
  private String firstName;
  @NotBlank
  private String lastName;

  @OneToMany
  private List<Book> books = new ArrayList<>();

  public Author(Long id, String firstName, String lastName) {
    super();
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
  }

}

