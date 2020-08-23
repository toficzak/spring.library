package library.domain.book;

import org.springframework.stereotype.Component;
import library.api.form.BookForm;
import library.api.form.CreateBookForm;

@Component
public class CreatorBook {

  private RepositoryBook bookRepo;

  public CreatorBook(RepositoryBook bookRepo) {
    super();
    this.bookRepo = bookRepo;
  }

  public BookForm create(CreateBookForm form) {
    Book book = new Book(form);
    return bookRepo.save(book).viewModel();
  }
}
