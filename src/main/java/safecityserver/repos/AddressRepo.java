package safecityserver.repos;

import org.springframework.data.repository.CrudRepository;
import safecityserver.entities.Address;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface AddressRepo extends CrudRepository<Address, Integer> {

}
