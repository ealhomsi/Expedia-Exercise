package ca.service;

import java.sql.Date;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import ca.dao.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ca.requesthandler.InvalidParameterException;
import ca.model.Flight;
import java.util.Calendar;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.HashSet;

@Service
public class FlightService {
  @Autowired
  FlightRepository flightRepository;

  /**
   * Method to create a new Flight
   * 
   * @param flight
   * @param departure
   * @return Flight
   */
  @Transactional
  public Flight createFlight(String flight, Date departure) {
    if (flight == null || flight.isEmpty()) {
      throw new InvalidParameterException("Your flight details are bogus!");
    } else if (departure == null) {
      throw new InvalidParameterException("Your departure date is null!");
    } else if (flightRepository.existsById(flight)) {
      throw new EntityExistsException("Flight Already Exists");
    }

    Flight f = new Flight();
    f.setFlight(flight);
    f.setDeparture(departure);

    return flightRepository.save(f);
  }

  /**
   * Create a flight passing object
   * 
   * @param flight
   * @return Flight
   */
  @Transactional
  public Flight createFlight(Flight flight) {
    return createFlight(flight.getFlight(), flight.getDeparture());
  }

  /**
   * Method to find a flight
   * 
   * @param flight
   * @return Flight
   */
  @Transactional
  public Flight getFlight(String flight) {
    Flight f = flightRepository.findByFlight(flight);
    if (f == null)
      throw new EntityNotFoundException("Could not find a Flight with details  " + flight);
    return f;
  }

  @Transactional
  public Set<Flight> getFlightsInTimeWindow(int hoursDelta) {
    Iterable<Flight> iterable = flightRepository.findAll();
    Set<Flight> res = new HashSet<Flight>();
    for (Flight t : iterable) {
      long diffInMillies = Math.abs(t.getDeparture().getTime() - Calendar.getInstance().getTimeInMillis());
      long diff = TimeUnit.HOURS.convert(diffInMillies, TimeUnit.MILLISECONDS);
      if (diff < hoursDelta) {
        res.add(t);
      }
    }

    return res;
  }

  /**
   * Method to get a list of all flights
   * 
   * @return Set<Flight>
   */
  @Transactional
  public Set<Flight> getAllFlights() {
    Iterable<Flight> iterable = flightRepository.findAll();
    Set<Flight> res = new HashSet<Flight>();
    for (Flight t : iterable) {
      res.add(t);
    }
    return res;
  }
}
