package safecityserver.repos;

import org.springframework.data.repository.CrudRepository;
import safecityserver.entities.Victim;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface VictimRepo extends CrudRepository<Victim, Integer> {

}
