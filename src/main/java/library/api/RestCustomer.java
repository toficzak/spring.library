package library.api;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import library.api.dto.CustomerDto;
import library.api.form.RegistrationForm;
import library.auth.IAuthenticationFacade;
import library.domain.customer.ListerCustomer;
import library.domain.customer.Registrator;

@RestController
@RequestMapping("/customers")
public class RestCustomer {

  private Registrator registrator;
  private ListerCustomer customerLister;
  private IAuthenticationFacade authenticationFacade;

  public RestCustomer(Registrator registrator, ListerCustomer customerLister,
      IAuthenticationFacade authenticationFacade) {
    super();
    this.registrator = registrator;
    this.customerLister = customerLister;
    this.authenticationFacade = authenticationFacade;
  }

  @GetMapping("me")
  public CustomerDto me() {
    Authentication auth = authenticationFacade.getAuthentication();
    return customerLister.get((String) auth.getPrincipal());
  }

  @PostMapping("register")
  public CustomerDto register(@RequestBody RegistrationForm form) {
    return registrator.register(form);
  }

}
