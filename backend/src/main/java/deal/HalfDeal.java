package deal;

import java.util.List;

import locationfood.Food;
import locationfood.Location;
import user.User;

public abstract class HalfDeal {
  protected Location location;
  protected Location eatery;
  protected int duration;
  protected List<Food> orders;
  protected double priceBound;
  protected User user;
  protected double actualPrice;

  enum type {
    deliver, meetup;
  }

  public HalfDeal(Location loc, Location ety, double bound, double price, int dur, List<Food> order, User usr) {
    location = loc;
    eatery = ety;
    actualPrice = price;
    duration = dur;
    orders = order;
    priceBound = bound;
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

  public List<Food> getOrders() {
    return orders;
  }

  public double getPriceBound() {
    return priceBound;
  }

  public User getUser() {
    return user;
  }

}
