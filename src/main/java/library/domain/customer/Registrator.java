package library.domain.customer;

import org.springframework.stereotype.Component;
import library.api.form.RegistrationForm;
import library.api.form.CustomerForm;

@Component
public class Registrator {

  private RepositoryCustomer customerRepo;

  public Registrator(RepositoryCustomer customerRepo) {
    super();
    this.customerRepo = customerRepo;
  }

  public CustomerForm register(RegistrationForm form) {
    Customer customer = new Customer(form.firstName, form.lastName, form.email, form.password);
    return customerRepo.save(customer).viewModel();
  }
}
