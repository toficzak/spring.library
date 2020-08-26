package library.auth;

import org.springframework.security.core.Authentication;
import library.domain.customer.Customer;

public interface IAuthenticationFacade {

  Authentication getAuthentication();

  void isAuthenticated();

  Customer getCurrentUser();
}
