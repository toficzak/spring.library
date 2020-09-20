package library.api;

import java.util.List;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import library.api.dto.BookDto;
import library.api.form.CreateBookForm;
import library.domain.book.FacadeBook;

@RestController
@RequestMapping("/books")
public class RestBook {

  private FacadeBook bookFacade;

  public RestBook(FacadeBook bookFacade) {
    super();
    this.bookFacade = bookFacade;
  }

  @GetMapping("{id}")
  public BookDto get(@PathVariable("id") Long id) {
    return bookFacade.get(id);
  }

  @GetMapping
  public List<BookDto> list() {
    return bookFacade.list();
  }

  @PostMapping
  public BookDto create(@Valid @RequestBody CreateBookForm form) {
    return bookFacade.create(form);
  }

}
