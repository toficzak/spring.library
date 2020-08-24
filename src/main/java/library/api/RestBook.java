package library.api;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import library.api.form.BookForm;
import library.api.form.CreateBookForm;
import library.domain.book.CreatorBook;
import library.domain.book.ListerBook;

@RestController
@RequestMapping("/books")
public class RestBook {

  private CreatorBook bookCreator;
  private ListerBook bookLister;

  public RestBook(CreatorBook bookCreator, ListerBook bookLister) {
    super();
    this.bookCreator = bookCreator;
    this.bookLister = bookLister;
  }

  @GetMapping("{id}")
  public BookForm get(@PathVariable("id") Long id) {
    return bookLister.get(id);
  }

  @GetMapping
  public List<BookForm> list() {
    return bookLister.list();
  }

  @PostMapping
  public BookForm create(@RequestBody CreateBookForm form) {
    return bookCreator.create(form);
  }

}
