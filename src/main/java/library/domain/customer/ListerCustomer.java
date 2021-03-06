package library.domain.customer;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import org.springframework.stereotype.Component;
import library.api.dto.CustomerDto;

@Component
public class ListerCustomer {

  private RepositoryCustomer customerRepository;

  public ListerCustomer(RepositoryCustomer customerRepository) {
    super();
    this.customerRepository = customerRepository;
  }

  public CustomerDto get(String email) {
    return customerRepository.findByEmail(email)
        .map(Customer::viewModel)
        .orElseThrow();
  }

  @RolesAllowed("ADMIN")
  public List<CustomerDto> getAll() {
    List<CustomerDto> customers = new ArrayList<>();
    customerRepository.findAll().forEach(customer -> customers.add(customer.viewModel()));
    return customers;
  }

}
