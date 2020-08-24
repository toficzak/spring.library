package library.domain.customer;

import org.springframework.stereotype.Component;
import library.api.dto.CustomerDto;
import library.api.form.RegistrationForm;

@Component
public class Registrator {

  private RepositoryCustomer customerRepo;

  public Registrator(RepositoryCustomer customerRepo) {
    super();
    this.customerRepo = customerRepo;
  }

  public CustomerDto register(RegistrationForm form) {
    Customer customer = new Customer(form.firstName, form.lastName, form.email, form.password);
    return customerRepo.save(customer).viewModel();
  }
}
