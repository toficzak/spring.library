package library.domain.customer;

import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import library.api.dto.CustomerDto;
import library.api.form.ChangePasswordForm;
import library.api.form.ResetPasswordForm;
import library.api.form.SetupResetPasswordForm;
import library.auth.AuthenticationFacade;

@Component
public class FacadeCustomer {

  private RepositoryCustomer customerRepo;
  private PasswordReseter passwordReseter;
  private PasswordChanger passwordChanger;
  private AuthenticationFacade auth;

  public FacadeCustomer(RepositoryCustomer customerRepo, PasswordReseter passwordReseter,
      PasswordChanger passwordChanger, AuthenticationFacade auth) {
    super();
    this.customerRepo = customerRepo;
    this.passwordReseter = passwordReseter;
    this.passwordChanger = passwordChanger;
    this.auth = auth;
  }

  public CustomerDto setupResetPassword(SetupResetPasswordForm form) {
    Customer customer = customerRepo.findByEmail(form.email).orElseThrow();
    passwordReseter.resetPassword(customer);
    return customer.viewModel();
  }

  public CustomerDto resetPassword(ResetPasswordForm form) {
    Customer customer = customerRepo.findByResetPasswordHash(form.resetPasswordHash).orElseThrow();
    passwordChanger.changePassword(customer, form.password);
    return customer.viewModel();
  }

  public CustomerDto changePassword(ChangePasswordForm form) {
    Customer customer = auth.getCurrentUser();
    passwordChanger.changePassword(customer, form.password);
    return customer.viewModel();
  }

  public void activate(String activationHash) {
    Optional<Customer> optCustomer = customerRepo.findByActivationHash(activationHash);
    if (optCustomer.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    } else {
      Customer customer = optCustomer.get();
      customer.activate();
      customerRepo.save(customer);
    }
  }
}
