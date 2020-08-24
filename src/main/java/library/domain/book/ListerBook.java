package library.domain.book;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import library.api.dto.BookDto;

@Component
public class ListerBook {

  private RepositoryBook bookRepository;

  public ListerBook(RepositoryBook bookRepository) {
    super();
    this.bookRepository = bookRepository;
  }

  public BookDto get(Long id) {
    return bookRepository.findById(id)
        .map(Book::viewModel)
        .orElseThrow();
  }

  public List<BookDto> list() {
    List<Book> list = new ArrayList<>();
    bookRepository.findAll().forEach(list::add);
    return list.stream()
        .map(Book::viewModel)
        .collect(Collectors.toList());
  }
}
