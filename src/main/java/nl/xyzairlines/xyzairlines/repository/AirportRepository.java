package nl.xyzairlines.xyzairlines.repository;

import nl.xyzairlines.xyzairlines.model.Airport;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirportRepository extends CrudRepository<Airport, String> {
}
