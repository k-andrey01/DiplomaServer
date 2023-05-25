package safecityserver.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import safecityserver.entities.Crime;

@Repository
public interface CrimeRepo extends JpaRepository<Crime, Integer> {

}