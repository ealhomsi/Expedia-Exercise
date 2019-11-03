package service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import ca.requesthandler.InvalidParameterException;
import util.TestUtil;
import ca.dao.FlightRepository;
import ca.model.Flight;
import ca.service.FlightService;
import java.sql.Date;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class TestFlight {
  public static final String FLIGHT = "Air Canada 8099";
  public static final Date DEPARTURE = Date.valueOf("2019-01-01");

  @InjectMocks
  private FlightService service;

  @Mock
  private FlightRepository flightRepository;

  @Before
  public void mockSetUp() {
    when(flightRepository.save(any(Flight.class))).thenAnswer((InvocationOnMock invocation) -> {
      return TestUtil.createFlight(FLIGHT, DEPARTURE);
    });

    when(flightRepository.findByFlight(anyString())).thenAnswer((InvocationOnMock invocation) -> {
      if (invocation.getArgument(0).equals(FLIGHT)) {
        return TestUtil.createFlight(FLIGHT, DEPARTURE);
      } else {
        return null;
      }
    });

    when(flightRepository.existsById(anyString())).thenReturn(false, true);
  }

  @Test
  public void testCreateFlight() {
    Flight param = new Flight();
    param.setFlight(FLIGHT);
    param.setDeparture(DEPARTURE);

    try {
      service.createFlight(param);
    } catch (InvalidParameterException e) {
      fail();
    }
    Flight f = service.getFlight(FLIGHT);

    // Check attributes
    assertEquals(FLIGHT, f.getFlight());
    assertEquals(DEPARTURE, f.getDeparture());
  }

  @Test
  public void testCreateFlightWithParameters() {
    try {
      service.createFlight(FLIGHT, DEPARTURE);
    } catch (InvalidParameterException e) {
      fail();
    }
    Flight f = service.getFlight(FLIGHT);

    // Check attributes
    assertEquals(FLIGHT, f.getFlight());
    assertEquals(DEPARTURE, f.getDeparture());
  }

  @Test
  public void testCreateNullFlight() {
    try {
      service.createFlight(null, DEPARTURE);
      fail();
    } catch (InvalidParameterException e) {
    }
    assertEquals(0, service.getAllFlights().size());
  }

  @Test
  public void testCreateEmptyFlight() {
    try {
      service.createFlight("", DEPARTURE);
      fail();
    } catch (InvalidParameterException e) {
    }
    assertEquals(0, service.getAllFlights().size());
  }

  @Test
  public void testCreateNullDeparture() {
    try {
      service.createFlight(FLIGHT, null);
      fail();
    } catch (InvalidParameterException e) {
    }
    assertEquals(0, service.getAllFlights().size());
  }
}
