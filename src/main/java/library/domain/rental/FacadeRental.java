package library.domain.rental;

import org.springframework.stereotype.Component;
import library.api.dto.RentalDto;
import library.api.form.BorrowForm;
import library.auth.IAuthenticationFacade;
import library.domain.customer.Customer;

@Component
public class FacadeRental {

  private IAuthenticationFacade authorizationFacade;
  private Borrower borrower;

  public FacadeRental(IAuthenticationFacade authorizationFacade, Borrower borrower) {
    super();
    this.authorizationFacade = authorizationFacade;
    this.borrower = borrower;
  }

  public RentalDto borrow(BorrowForm form) {
    Customer customer = authorizationFacade.getCurrentUser();
    form.customerId = customer.getId();
    return borrower.borrow(form);
  }

}
