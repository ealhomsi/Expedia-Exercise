package util;

import java.sql.Date;
import ca.model.Flight;

public class TestUtil {
  public static Flight createFlight(String flight, Date departure) {
    Flight f = new Flight();
    f.setFlight(flight);
    f.setDeparture(departure);
    return f;
  }
}
