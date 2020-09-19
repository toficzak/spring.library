package library.domain.customer;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import library.api.dto.CustomerDto;
import library.email.SendEmailEvent;

@Component
public class PasswordReseter {
  private RepositoryCustomer customerRepo;
  private ApplicationEventPublisher applicationEventPublisher;

  public PasswordReseter(RepositoryCustomer customerRepo,
      ApplicationEventPublisher applicationEventPublisher) {
    super();
    this.customerRepo = customerRepo;
    this.applicationEventPublisher = applicationEventPublisher;
  }

  public CustomerDto resetPassword(Customer customer) {
    customer.generateResetPasswordHash();
    customerRepo.save(customer);
    applicationEventPublisher.publishEvent(this.prepareResetPasswordEmail(customer));
    return customer.viewModel();
  }

  private SendEmailEvent prepareResetPasswordEmail(Customer customer) {
    String message = "Reset password of user: " + customer.getName() + ".";
    String body = "Reset hash: " + customer.getResetPasswordHash();
    return new SendEmailEvent(this, customer.getEmail(), message, body);
  }

}
