package library.domain.rental;

import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotNull;
import org.springframework.stereotype.Component;
import library.api.dto.RentalDto;
import library.api.form.BorrowForm;
import library.domain.book.Book;
import library.domain.book.RepositoryBook;
import library.domain.customer.Customer;
import library.domain.customer.RepositoryCustomer;

@Component
public class Borrower {

  private RepositoryRental rentalRepository;
  private RepositoryBook bookRepo;
  private RepositoryCustomer customerRepo;

  public Borrower(
      RepositoryRental rentalRepository,
      RepositoryBook bookRepo,
      RepositoryCustomer customerRepo) {
    super();
    this.rentalRepository = rentalRepository;
    this.bookRepo = bookRepo;
    this.customerRepo = customerRepo;
  }

  public RentalDto borrow(@NotNull BorrowForm form) {
    List<Book> books = new ArrayList<>();
    bookRepo.findAllById(form.bookIds).forEach(books::add);

    Customer customer = customerRepo.findById(form.customerId).orElseThrow();

    Rental rental = new Rental(form.dateFrom, form.dateTo, customer, books);
    return rentalRepository.save(rental).viewModel();
  }
}
