package library.domain.log;

import java.util.List;
import org.springframework.stereotype.Component;
import library.api.dto.LogDto;

@Component
public class FacadeLog {

  private RepositoryLogDto logRepo;

  public FacadeLog(RepositoryLogDto logRepo) {
    super();
    this.logRepo = logRepo;
  }

  public List<LogDto> listing(Long customerId) {
    return logRepo.findByCustomerId(customerId);
  }
}
