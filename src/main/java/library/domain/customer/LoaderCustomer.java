package library.domain.customer;

import java.util.HashSet;
import java.util.Set;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
        this.getAuthority(applicationUser));
  }

  private Set<SimpleGrantedAuthority> getAuthority(Customer customer) {
    Set<SimpleGrantedAuthority> authorities = new HashSet<>();
    customer.getRoles().forEach(role -> {
      authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
    });
    return authorities;
  }
}
