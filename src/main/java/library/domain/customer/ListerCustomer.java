package library.domain.customer;

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

}
