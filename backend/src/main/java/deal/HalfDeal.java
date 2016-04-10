package deal;

import geo.Location;

import java.time.Duration;
import java.time.Clock;

import user.User;

public abstract class HalfDeal {
  public final int ID;
  private Location location;
  private Clock time;
  private Duration duration;
  private String userNote;
  private double priceLowerBound;
  private double priceUpperBound;
  private User user;
  
  enum type {
    deliver, meetup;
  }
  
  public HalfDeal(int id, Location loc, Clock clock, Duration dur, String note, double upper, double lower, User usr) {
    ID = id;
    location = loc;
    duration = dur;
    time = clock;
    userNote = note;
    priceUpperBound = upper;
    priceLowerBound = lower;
    user = usr;
  }

  public abstract void cancel();
  
  // getters and setters
  public Location getLocation() {
    return location;
  }

  public void setLocation(Location location) {
    this.location = location;
  }

  public Clock getTime() {
    return time;
  }

  public String getUserNote() {
    return userNote;
  }

  public double getPriceLowerBound() {
    return priceLowerBound;
  }

  public void setPriceLowerBound(double priceLowerBound) {
    this.priceLowerBound = priceLowerBound;
  }

  public double getPriceUpperBound() {
    return priceUpperBound;
  }

  public void setPriceUpperBound(double priceUpperBound) {
    this.priceUpperBound = priceUpperBound;
  }

  public User getUser() {
    return user;
  }
  
}
