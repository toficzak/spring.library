package library.api;

import javax.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import library.api.dto.CustomerDto;
import library.api.form.RegistrationForm;
import library.api.form.ResetPasswordForm;
import library.api.form.SetupResetPasswordForm;
import library.domain.customer.FacadeCustomer;
import library.domain.customer.Registrator;

@RestController
@RequestMapping("/customers")
public class RestCustomer {

  private Registrator registrator;
  private FacadeCustomer customerFacade;

  public RestCustomer(Registrator registrator, FacadeCustomer customerFacade) {
    super();
    this.registrator = registrator;
    this.customerFacade = customerFacade;
  }

  @PostMapping("register")
  public CustomerDto register(@Valid @RequestBody RegistrationForm form) {
    return registrator.register(form);
  }

  @PostMapping("/setupResetPassword")
  public void setupResetPassword(@Valid @RequestBody SetupResetPasswordForm form) {
    customerFacade.setupResetPassword(form);
  }

  @PostMapping("/resetPassword")
  public void resetPassword(@Valid @RequestBody ResetPasswordForm form) {
    customerFacade.resetPassword(form);
  }
}
