package deal;

import geo.Location;
import global.Global;
import user.User;

public class Request extends HalfDeal {
  Runnable counting = () -> {
  	try {
			Thread.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
  	Global.getRequests().remove(this);
  };
  
  public Request(int id, Location loc, int dur,
      String note, double upper, double lower, User usr) {
    super(id, loc, dur, note, upper, lower, usr);
    Global.getRequests().add(this);
  }
  
  @Override
  protected void start() {
  	new Thread(counting).start();
  }
  
  @Override
  public void cancel() {
    Global.getRequests().remove(this);
  }
}
