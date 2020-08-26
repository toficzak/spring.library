package library.auth;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import library.domain.customer.Customer;
import library.domain.customer.RepositoryCustomer;

@Component
public class AuthenticationFacade implements IAuthenticationFacade {

  private RepositoryCustomer customerRepository;

  public AuthenticationFacade(RepositoryCustomer customerRepository) {
    super();
    this.customerRepository = customerRepository;
  }

  @Override
  public Authentication getAuthentication() {
    return SecurityContextHolder.getContext().getAuthentication();
  }

  @Override
  public void isAuthenticated() {
    Authentication auth = this.getAuthentication();
    if (!auth.isAuthenticated()) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
    }
  }

  @Override
  public Customer getCurrentUser() {
    Authentication auth = this.getAuthentication();
    if (!auth.isAuthenticated()) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
    }
    return customerRepository.findByEmail((String) auth.getPrincipal())
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED));
  }
}
