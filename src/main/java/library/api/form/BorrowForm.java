package library.api.form;

import java.time.ZonedDateTime;
import java.util.Collection;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

public class BorrowForm {

  @NotNull
  @DateTimeFormat(iso = ISO.DATE)
  public ZonedDateTime dateFrom;
  @NotNull
  @DateTimeFormat(iso = ISO.DATE)
  public ZonedDateTime dateTo;

  @NotEmpty
  public Collection<Long> bookIds;
  @NotNull
  public Long customerId;

}
