package deal;

import java.util.List;

import global.Global;
import locationfood.Eatery;
import locationfood.Location;
import user.User;

public class Offer extends HalfDeal{
  Runnable counting = () -> {
  	try {
			Thread.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
  	Global.getOffer().remove(this);
  };
  private int creditNum;
  protected List<Location> location;
  protected List<Eatery> eatery;
	
  public Offer(List<Location> loc, List<Eatery> ety, double upper, int dur, User usr, int num) {
    super(upper, dur, usr);
    location = loc;
    eatery = ety;
    creditNum = num;
    Global.getOffer().add(this);
    if (location.isEmpty()) {
    	isDeliver = false;
    } else {
    	isDeliver = true;
    }
  }
  
  public List<Location> getLocations() {
  	return location;
  }
  
  public List<Eatery> getEateries() {
  	return eatery;
  }
  
  public int getCreditNum() {
  	return creditNum;
  }
  
  @Override
  protected void start() {
  	new Thread(counting).start();
  }
  
  @Override
  public void cancel() {
    Global.getOffer().remove(this);
  }
}
