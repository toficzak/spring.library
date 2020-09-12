package library.domain.rental;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import library.api.dto.RentalDto;
import library.api.form.BorrowForm;
import library.api.form.CreateBookForm;
import library.domain.book.Book;
import library.domain.book.RepositoryBook;
import library.domain.customer.Customer;
import library.domain.customer.RepositoryCustomer;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("Borrow feature tests")
class BorrowTest {

  private Borrower borrower;
  private RepositoryRental rentalRepo;
  private RepositoryCustomer customerRepo;
  private RepositoryBook bookRepo;

  @Autowired
  public BorrowTest(Borrower borrower, RepositoryRental rentalRepo, RepositoryCustomer customerRepo,
      RepositoryBook bookRepo) {
    this.borrower = borrower;
    this.rentalRepo = rentalRepo;
    this.customerRepo = customerRepo;
    this.bookRepo = bookRepo;
  }

  @BeforeEach
  public void clear() {
    rentalRepo.deleteAll();
  }

  @Nested
  @DisplayName("borrow()")
  class BorrowFeature {

    @Test
    void singleBorrow() {
      // given
      Customer customer =
          customerRepo.save(new Customer("Test", "Test", "test@email.com", "pass", Set.of()));
      CreateBookForm bookForm = new CreateBookForm();
      bookForm.title = "Test title";
      Book book = bookRepo.save(new Book(bookForm));
      BorrowForm borrowForm = new BorrowForm();
      borrowForm.dateFrom =
          LocalDateTime.parse("2011-12-03T10:15:30", DateTimeFormatter.ISO_LOCAL_DATE_TIME);
      borrowForm.dateTo =
          LocalDateTime.parse("2012-12-03T10:15:30", DateTimeFormatter.ISO_LOCAL_DATE_TIME);

      borrowForm.bookIds = List.of(book.getId());
      borrowForm.customerId = customer.getId();

      // when
      RentalDto rental = borrower.borrow(borrowForm);

      // THEN
      Assertions.assertNotNull(rental);
      Assertions.assertNotNull(rental.books);
      Assertions.assertNotNull(rental.dateFrom);
      Assertions.assertNotNull(rental.dateTo);
      Assertions.assertNotNull(rental.customer);
    }
  }

}
