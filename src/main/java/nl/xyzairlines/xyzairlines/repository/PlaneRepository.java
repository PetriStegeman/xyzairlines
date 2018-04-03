package nl.xyzairlines.xyzairlines.repository;

import nl.xyzairlines.xyzairlines.model.Plane;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaneRepository extends CrudRepository<Plane, Long> {
}
