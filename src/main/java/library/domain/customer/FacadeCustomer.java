package library.domain.customer;

import org.springframework.stereotype.Component;
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
}
