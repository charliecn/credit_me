package deal;

import locationfood.Eatery;
import locationfood.Location;
import user.User;

public abstract class HalfDeal {
  protected Location location;
  protected Eatery eatery;
  protected int duration;
  protected double priceBound;
  protected User user;
  protected boolean isDeliver;

  enum type {
    deliver, meetup;
  }

  public HalfDeal(Location loc, Eatery ety, double bound, int dur, User usr) {
    location = loc;
    eatery = ety;
    duration = dur;
    priceBound = bound;
    user = usr;
    if (location == null) {
    	isDeliver = false;
    } else {
    	isDeliver = true;
    }
  }
  
  protected abstract void start();
  
  public abstract void cancel();

  // getters and setters
  public Location getLocation() {
    return location;
  }

  public boolean isDeliver() {
  	return isDeliver;
  }
  
  public Eatery getEatery() {
  	return eatery;
}

  public double getPriceBound() {
    return priceBound;
  }
  
  public User getUser() {
    return user;
  }

}
