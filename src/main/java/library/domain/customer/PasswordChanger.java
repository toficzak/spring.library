package library.domain.customer;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import library.email.SendEmailEvent;

@Component
public class PasswordChanger {

  private RepositoryCustomer customerRepo;
  private ApplicationEventPublisher applicationEventPublisher;

  public PasswordChanger(RepositoryCustomer customerRepo,
      ApplicationEventPublisher applicationEventPublisher) {
    super();
    this.customerRepo = customerRepo;
    this.applicationEventPublisher = applicationEventPublisher;
  }

  public void changePassword(Customer customer, String password) {
    customer.changePassword(password);
    customerRepo.save(customer);
    applicationEventPublisher.publishEvent(this.prepareResetedPasswordEmail(customer));
  }

  private SendEmailEvent prepareResetedPasswordEmail(Customer customer) {
    String message = "Changed password of user: " + customer.getName() + ".";
    String body = "You have changed password.";
    return new SendEmailEvent(this, customer.getEmail(), message, body);
  }
}
