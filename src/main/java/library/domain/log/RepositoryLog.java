package library.domain.log;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryLog extends JpaRepository<Log, Long>, RepositoryLogDto {

}
