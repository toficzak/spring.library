package library.api.dto;

import java.time.ZonedDateTime;
import java.util.Collection;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

public class RentalDto {
  @NotNull
  @DateTimeFormat(iso = ISO.DATE)
  public ZonedDateTime dateFrom;
  @NotNull
  @DateTimeFormat(iso = ISO.DATE)
  public ZonedDateTime dateTo;

  @NotEmpty
  public Collection<BookDto> books;
  @NotNull
  public CustomerDto customer;

  public RentalDto(
      @NotNull ZonedDateTime dateFrom,
      @NotNull ZonedDateTime dateTo,
      @NotEmpty Collection<BookDto> books,
      @NotNull CustomerDto customer) {
    super();
    this.dateFrom = dateFrom;
    this.dateTo = dateTo;
    this.books = books;
    this.customer = customer;
  }
}
