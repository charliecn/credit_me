package deal;

import global.Global;
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
  int creditNum;
	
  public Offer(Location loc, Eatery ety, double upper, double price, int dur, User usr, int num) {
    super(loc, ety, upper, price, dur, usr);
    creditNum = num;
    Global.getOffer().add(this);
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
