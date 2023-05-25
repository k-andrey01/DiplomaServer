package safecityserver.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import safecityserver.entities.Userr;

@Repository
public interface UserRepo extends JpaRepository<Userr, Integer> {

}