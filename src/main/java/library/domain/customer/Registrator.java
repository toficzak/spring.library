package library.domain.customer;

import java.util.Set;
import org.springframework.stereotype.Component;
import library.api.dto.CustomerDto;
import library.api.form.RegistrationForm;
import library.domain.customer.role.RepositoryRole;
import library.domain.customer.role.Role;

@Component
public class Registrator {

  private RepositoryCustomer customerRepo;
  private RepositoryRole roleRepository;


  public Registrator(RepositoryCustomer customerRepo, RepositoryRole roleRepository) {
    super();
    this.customerRepo = customerRepo;
    this.roleRepository = roleRepository;
  }


  public CustomerDto register(RegistrationForm form) {
    Set<Role> roles = roleRepository.findAllByNameIn(Role.CUSTOMER_ROLES);
    Customer customer =
        new Customer(form.firstName, form.lastName, form.email, form.password, roles);
    return customerRepo.save(customer).viewModel();
  }
}
