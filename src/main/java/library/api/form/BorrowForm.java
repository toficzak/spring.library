package library.api.form;

import java.time.LocalDateTime;
import java.util.Collection;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

public class BorrowForm {

  @NotNull
  @DateTimeFormat(iso = ISO.DATE_TIME)
  public LocalDateTime dateFrom;
  @NotNull
  @DateTimeFormat(iso = ISO.DATE_TIME)
  public LocalDateTime dateTo;

  @NotEmpty
  public Collection<Long> bookIds;

  public Long customerId;
}
