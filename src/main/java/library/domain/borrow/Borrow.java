// package library.domain.borrow;
//
// import java.time.ZonedDateTime;
// import java.util.List;
// import javax.persistence.Entity;
// import javax.persistence.GeneratedValue;
// import javax.persistence.Id;
// import javax.persistence.ManyToOne;
// import javax.persistence.OneToMany;
// import library.domain.book.Book;
// import library.domain.customer.Customer;
//
// @Entity
// public class Borrow {
// @Id
// @GeneratedValue
// private Long id;
// @ManyToOne
// private Customer customer;
//
// @OneToMany
// private List<Book> books;
//
// private ZonedDateTime dateFrom;
// private ZonedDateTime dateTo;
//
// private Status status;
//
// private enum Status {
// BORROWED,
// RETURNED,
// HOLDED
// }
// }
