package ca.model;

import javax.persistence.Entity;
import java.sql.Date;
import javax.persistence.Id;

@Entity
public class Flight implements Comparable<Flight> {
  private String flight;
  private Date departure;

  @Id
  public String getFlight() {
    return this.flight;
  }

  public void setFlight(String flight) {
    this.flight = flight;
  }

  public Date getDeparture() {
    return this.departure;
  }

  public void setDeparture(Date departure) {
    this.departure = departure;
  }

  @Override
  public int compareTo(Flight other) {
    return this.departure.compareTo(other.departure);
  }

  @Override
  public String toString() {
    return "[ flight=" + flight + ", departure=" + departure + "]";
  }
}
