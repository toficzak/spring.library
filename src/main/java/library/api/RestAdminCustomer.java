package library.api;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import library.api.dto.CustomerDto;
import library.domain.customer.ListerCustomer;

@RestController
@RequestMapping("admin/customers")
public class RestAdminCustomer {

  private ListerCustomer customerLister;

  public RestAdminCustomer(ListerCustomer customerLister) {
    super();
    this.customerLister = customerLister;
  }

  @GetMapping
  public List<CustomerDto> listing() {
    return customerLister.getAll();
  }

}
