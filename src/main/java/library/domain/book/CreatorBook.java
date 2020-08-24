package library.domain.book;

import org.springframework.stereotype.Component;
import library.api.dto.BookDto;
import library.api.form.CreateBookForm;

@Component
public class CreatorBook {

  private RepositoryBook bookRepo;

  public CreatorBook(RepositoryBook bookRepo) {
    super();
    this.bookRepo = bookRepo;
  }

  public BookDto create(CreateBookForm form) {
    Book book = new Book(form);
    return bookRepo.save(book).viewModel();
  }
}
