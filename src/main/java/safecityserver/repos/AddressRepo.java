package safecityserver.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import safecityserver.entities.Address;

@Repository
public interface AddressRepo extends JpaRepository<Address, Integer> {

}