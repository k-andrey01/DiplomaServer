package safecityserver.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import safecityserver.entities.Type;

import java.util.List;
import java.util.Optional;

@Repository
public interface TypeRepo extends JpaRepository<Type, Integer> {
    Optional<Type> findByNameType(String nameType);
}
