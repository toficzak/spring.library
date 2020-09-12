package library.domain.customer;

import java.util.Set;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import library.api.dto.CustomerDto;
import library.api.form.RegistrationForm;
import library.domain.customer.role.RepositoryRole;
import library.domain.customer.role.Role;
import library.email.SendEmailEvent;

@Component
public class Registrator {

  private RepositoryCustomer customerRepo;
  private RepositoryRole roleRepository;
  private ApplicationEventPublisher applicationEventPublisher;

  public Registrator(RepositoryCustomer customerRepo, RepositoryRole roleRepository,
      ApplicationEventPublisher applicationEventPublisher) {
    super();
    this.customerRepo = customerRepo;
    this.roleRepository = roleRepository;
    this.applicationEventPublisher = applicationEventPublisher;
  }

  public CustomerDto register(RegistrationForm form) {
    Set<Role> roles = roleRepository.findAllByNameIn(Role.CUSTOMER_ROLES);
    Customer customer = customerRepo
        .save(new Customer(form.firstName, form.lastName, form.email, form.password, roles));
    applicationEventPublisher.publishEvent(this.prepareRegistrationEmail(customer));
    return customer.viewModel();
  }

  private SendEmailEvent prepareRegistrationEmail(Customer customer) {
    String message = "Registration of " + customer.getName() + ".";
    return new SendEmailEvent(this, customer.getEmail(), message, message);
  }
}
