package library.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import library.api.dto.RentalDto;
import library.api.form.BorrowForm;
import library.domain.rental.FacadeRental;

@RestController
@RequestMapping("rentals")
public class RestRental {

  private FacadeRental rentalFacade;

  public RestRental(FacadeRental rentalFacade) {
    super();
    this.rentalFacade = rentalFacade;
  }

  @PostMapping
  public RentalDto rent(@RequestBody BorrowForm form) {
    return rentalFacade.borrow(form);
  }

}
