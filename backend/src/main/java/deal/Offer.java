package deal;

import geo.Location;
import global.Global;
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
	
  public Offer(int id, Location loc, int dur,
      String note, double upper, double lower, User usr) {
    super(id, loc, dur, note, upper, lower, usr);
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
