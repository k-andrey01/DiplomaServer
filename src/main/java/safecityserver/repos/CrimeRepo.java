package safecityserver.repos;

import org.springframework.data.repository.CrudRepository;
import safecityserver.entities.Crime;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface CrimeRepo extends CrudRepository<Crime, Integer> {

}