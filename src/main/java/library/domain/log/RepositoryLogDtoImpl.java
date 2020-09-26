package library.domain.log;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import library.api.dto.LogDto;

@Component
@Primary
public class RepositoryLogDtoImpl implements RepositoryLogDto {

  @PersistenceContext
  private EntityManager em;

  @Override
  public List<LogDto> findByCustomerId(Long customerId) {
    return em.createQuery(
        "select new "
            + "library.api.dto.LogDto(l.type, l.entityId, l.entityName) "
            + "from Log  l "
            + "where l.customerId = :customerId",
        LogDto.class)
        .setParameter("customerId", customerId)
        .getResultList();
  }

}
