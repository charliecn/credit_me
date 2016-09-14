package deal;

import java.util.Date;
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
	
  public Offer(List<Location> loc, List<Eatery> ety, double upper, int dur, User usr, int num, Date date) {
    super(upper, dur, usr, date);
    location = loc;
    eatery = ety;
    creditNum = num;
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
  
  @Override
  public String toString() {
    return "Offer:\nbuyer: " + user.getName() + "\ncredit number: " + creditNum + 
    		"\npriceBound: " + priceBound+ "\ntime: " + time.toString()  + "\neateries: " + eatery + "\n";
  }
}
