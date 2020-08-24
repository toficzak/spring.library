package library.domain.customer;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import library.api.form.CustomerForm;
import library.api.form.RegistrationForm;

@RestController
@RequestMapping("/customers")
public class RestCustomer {

  private Registrator registrator;

  public RestCustomer(Registrator registrator) {
    super();
    this.registrator = registrator;
  }

  @PostMapping("register")
  public CustomerForm register(@RequestBody RegistrationForm form) {
    return registrator.register(form);
  }

}
