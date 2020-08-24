package library.domain.rental;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface RepositoryRental extends JpaRepository<Rental, Long> {

}
