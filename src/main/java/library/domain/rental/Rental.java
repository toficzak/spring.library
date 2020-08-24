package library.domain.rental;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import library.api.dto.BookDto;
import library.api.dto.CustomerDto;
import library.api.dto.RentalDto;
import library.domain.book.Book;
import library.domain.customer.Customer;

@Entity
public class Rental {

  @Id
  @GeneratedValue
  private Long id;

  @NotNull
  @DateTimeFormat(iso = ISO.DATE)
  private ZonedDateTime dateFrom;

  @NotNull
  @DateTimeFormat(iso = ISO.DATE)
  private ZonedDateTime dateTo;

  @NotNull
  private Status status;

  @NotNull
  @ManyToOne
  private Customer customer;

  @NotNull
  @OneToMany(mappedBy = "rental")
  private List<Book> books;

  private enum Status {
    BORROWED,
    RETURNED,
    HOLDED
  }

  public Rental() {}

  public Rental(ZonedDateTime dateFrom, ZonedDateTime dateTo, Customer customer, List<Book> books) {
    super();
    this.dateFrom = dateFrom;
    this.dateTo = dateTo;
    this.customer = customer;
    this.books = books;
    this.status = Status.BORROWED;
  }

  public RentalDto viewModel() {
    List<BookDto> booksViewModel = this.books.stream()
        .map(Book::viewModel)
        .collect(Collectors.toList());

    CustomerDto customerViewModel = this.customer.viewModel();

    return new RentalDto(this.dateFrom, this.dateTo, booksViewModel, customerViewModel);
  }

}
