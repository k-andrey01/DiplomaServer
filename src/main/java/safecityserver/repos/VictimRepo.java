package safecityserver.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import safecityserver.entities.Victim;

@Repository
public interface VictimRepo extends JpaRepository<Victim, Integer> {

}
