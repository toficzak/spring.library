package library.api.dto;

import java.time.LocalDateTime;
import java.util.Collection;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

public class RentalDto {
  @NotNull
  @DateTimeFormat(iso = ISO.DATE)
  public LocalDateTime dateFrom;
  @NotNull
  @DateTimeFormat(iso = ISO.DATE)
  public LocalDateTime dateTo;

  @NotEmpty
  public Collection<BookDto> books;
  @NotNull
  public CustomerDto customer;

  public RentalDto(
      @NotNull LocalDateTime dateFrom,
      @NotNull LocalDateTime dateTo,
      @NotEmpty Collection<BookDto> books,
      @NotNull CustomerDto customer) {
    super();
    this.dateFrom = dateFrom;
    this.dateTo = dateTo;
    this.books = books;
    this.customer = customer;
  }
}
