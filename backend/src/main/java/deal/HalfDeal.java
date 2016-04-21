package deal;

import user.User;

public abstract class HalfDeal {
  protected int duration;
  protected double priceBound;
  protected User user;
  protected boolean isDeliver;

  enum type {
    deliver, meetup;
  }

  public HalfDeal(double bound, int dur, User usr) {
    duration = dur;
    priceBound = bound;
    user = usr;
  }
  
  protected abstract void start();
  
  public abstract void cancel();

  // getters and setters

  public boolean isDeliver() {
  	return isDeliver;
  }

  public double getPriceBound() {
    return priceBound;
  }
  
  public User getUser() {
    return user;
  }

}
