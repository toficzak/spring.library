package library.domain.customer;

import java.util.Collections;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class LoaderCustomer implements UserDetailsService {

  private RepositoryCustomer customerRepository;

  public LoaderCustomer(RepositoryCustomer customerRepository) {
    super();
    this.customerRepository = customerRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) {
    Customer applicationUser = customerRepository.findByEmail(username)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, username));

    return new User(applicationUser.getEmail(), applicationUser.getPassword(),
        Collections.emptyList());
  }
}
