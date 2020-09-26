package library.domain.log;

import library.domain.book.Book;
import library.domain.customer.Customer;

public enum LogType {
  CREATE_CUSTOMER(Customer.class.getSimpleName()),
  CHANGE_PASSWORD(Customer.class.getSimpleName()),
  RESET_PASSWORD(Customer.class.getSimpleName()),
  RENT_BOOK(Book.class.getSimpleName()),
  RETURN_BOOK(Book.class.getSimpleName());

  private String entityName;

  private LogType(String entityName) {
    this.entityName = entityName;
  }

  public String getEntityName() {
    return this.entityName;
  }
}
