package deal;

import java.util.Date;

import user.User;

public abstract class HalfDeal {
  protected int duration;
  protected double priceBound;
  protected User user;
  protected boolean isDeliver;
  protected Date time;

  enum type {
    deliver, meetup;
  }

  public HalfDeal(double bound, int dur, User usr, Date date) {
    duration = dur;
    priceBound = bound;
    user = usr;
    time = date;
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
  
  public String getTimeString() {
    if (time != null) {
    	return time.toString();
    } else {
    	return "null";
    }
  }
  
  public Date getTime() {
  	return time;
  }
  
  public int getDuration() {
  	return duration;
  }

}
