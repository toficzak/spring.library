package library.domain.book;

import java.util.List;
import org.springframework.stereotype.Component;
import library.api.dto.BookDto;
import library.api.form.CreateBookForm;

@Component
public class FacadeBook {

  private CreatorBook bookCreator;
  private ListerBook bookLister;
  private RepositoryAuthor authorRepo;

  public FacadeBook(CreatorBook bookCreator, ListerBook bookLister, RepositoryAuthor authorRepo) {
    super();
    this.bookCreator = bookCreator;
    this.bookLister = bookLister;
    this.authorRepo = authorRepo;
  }

  public BookDto get(Long id) {
    return bookLister.get(id);
  }

  public List<BookDto> list() {
    return bookLister.list();
  }

  public BookDto create(CreateBookForm form) {
    Author author = authorRepo.findById(form.authorId).orElseThrow();
    form.author = author;
    return bookCreator.create(form);
  }
}
