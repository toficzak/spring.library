package library.domain.customer.role;

import java.util.Collection;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryRole extends JpaRepository<Role, Long> {

  Set<Role> findAllByNameIn(Collection<String> names);
}
