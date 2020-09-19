package library.api;

import javax.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import library.api.dto.CustomerDto;
import library.api.form.ChangePasswordForm;
import library.auth.IAuthenticationFacade;
import library.domain.customer.FacadeCustomer;
import library.domain.customer.ListerCustomer;

@RestController
@RequestMapping("/customers/me")
public class RestCustomerMe {

  private ListerCustomer customerLister;
  private IAuthenticationFacade authenticationFacade;
  private FacadeCustomer customerFacade;

  public RestCustomerMe(ListerCustomer customerLister,
      IAuthenticationFacade authenticationFacade, FacadeCustomer customerFacade) {
    super();
    this.customerLister = customerLister;
    this.authenticationFacade = authenticationFacade;
    this.customerFacade = customerFacade;
  }

  @GetMapping
  public CustomerDto me() {
    Authentication auth = authenticationFacade.getAuthentication();
    return customerLister.get(((User) auth.getPrincipal()).getUsername());
  }

  @PutMapping("/password")
  public CustomerDto changePassword(@Valid @RequestBody ChangePasswordForm form) {
    return customerFacade.changePassword(form);
  }

}
