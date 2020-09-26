package library.api;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import library.api.dto.LogDto;
import library.domain.log.FacadeLog;

@RestController
@RequestMapping("admin/logs")
public class RestAdminLog {

  private FacadeLog logFacade;

  public RestAdminLog(FacadeLog logFacade) {
    super();
    this.logFacade = logFacade;
  }

  @GetMapping(value = "/{customerId}")
  public List<LogDto> listingForCustomer(@PathVariable("customerId") Long customerId) {
    return logFacade.listing(customerId);
  }

}
