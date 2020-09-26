package library.domain.log;

import java.util.List;
import library.api.dto.LogDto;

interface RepositoryLogDto {

  List<LogDto> findByCustomerId(Long customerId);

}
