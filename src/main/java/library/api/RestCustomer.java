package library.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import library.api.dto.CustomerDto;
import library.api.form.RegistrationForm;
import library.domain.customer.Registrator;

@RestController
@RequestMapping("/customers")
public class RestCustomer {

  private Registrator registrator;

  public RestCustomer(Registrator registrator) {
    super();
    this.registrator = registrator;
  }

  @PostMapping("register")
  public CustomerDto register(@RequestBody RegistrationForm form) {
    return registrator.register(form);
  }

}
