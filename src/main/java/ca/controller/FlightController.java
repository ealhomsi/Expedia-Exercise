package ca.controller;

import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Set;
import ca.service.FlightService;
import ca.model.Flight;

@CrossOrigin(origins = "*")
@RestController
public class FlightController {

  @Autowired
  private FlightService service;

  /**
   * Say hello
   */
  @GetMapping("/hello")
  public String sayHello() {
    return "hello";
  }


  /**
   * Method to create a Flight via RESTful service call
   * 
   * @param flight
   * @return ResponseEntity<Object>
   */
  @PostMapping("/flight")
  public ResponseEntity<Object> createFlight(@RequestBody Flight flight) {
    Flight savedFlight = service.createFlight(flight);
    // create URI of where the enitity can be found
    URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().path("s/{id}")
        .buildAndExpand(savedFlight.getFlight()).toUri();
    // return 201 Status with location in header and body
    return ResponseEntity.created(location).body(location);
  }

  /**
   * List All Flights
   * @return
   */
  @GetMapping("/flights")
  public Set<Flight> listFlights() {
    return service.getAllFlights();
  }

  /**
   * List All Flights within time window
   * @return
   */
  @GetMapping("/flights_within")
  public Set<Flight> listFlightsInTimeWindow(@RequestParam(name = "distance") int distance) {
    return service.getFlightsInTimeWindow(distance);
  }

}
