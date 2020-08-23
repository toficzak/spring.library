package library.domain.book;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import library.api.form.BookForm;

@Component
public class ListerBook {

  private RepositoryBook bookRepository;

  public ListerBook(RepositoryBook bookRepository) {
    super();
    this.bookRepository = bookRepository;
  }

  public List<BookForm> list() {
    List<Book> list = new ArrayList<>();
    bookRepository.findAll().forEach(list::add);
    return list.stream()
        .map(Book::viewModel)
        .collect(Collectors.toList());
  }
}
