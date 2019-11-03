package ca.dao;

import ca.model.Flight;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*")
public interface FlightRepository extends CrudRepository<Flight, String> {
  Flight findByFlight(@Param(value = "flight") String flight);

  // Disable default POST end point
  @SuppressWarnings("unchecked")
  @Override
  @RestResource(exported = false)
  Flight save(Flight f);
}
