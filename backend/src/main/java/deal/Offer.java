package deal;

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
	
  public Offer(Location loc, Eatery ety, double upper, int dur, User usr, int num) {
    super(loc, ety, upper, dur, usr);
    creditNum = num;
    Global.getOffer().add(this);
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
