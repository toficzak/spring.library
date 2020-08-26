package library.auth;

import org.springframework.security.core.Authentication;

public interface IAuthenticationFacade {
  Authentication getAuthentication();
}
