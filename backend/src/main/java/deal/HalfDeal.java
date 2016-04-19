package deal;

import geo.Location;
import user.User;

public abstract class HalfDeal {
  public final int ID;
  protected Location location;
  protected int duration;
  protected String userNote;
  protected double priceLowerBound;
  protected double priceUpperBound;
  protected User user;

  enum type {
    deliver, meetup;
  }

  public HalfDeal(int id, Location loc, int dur, String note, double upper, double lower, User usr) {
    ID = id;
    location = loc;
    duration = dur;
    userNote = note;
    priceUpperBound = upper;
    priceLowerBound = lower;
    user = usr;
  }
  
  protected abstract void start();
  
  public abstract void cancel();

  // getters and setters
  public Location getLocation() {
    return location;
  }

  public void setLocation(Location location) {
    this.location = location;
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
