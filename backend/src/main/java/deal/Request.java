package deal;

import geo.Location;
import global.Global;

import java.time.Clock;
import java.time.Duration;

import user.User;

public class Request extends HalfDeal{

  public Request(int id, Location loc, Clock clock, Duration dur,
      String note, double upper, double lower, User usr) {
    super(id, loc, clock, dur, note, upper, lower, usr);
    Global.getRequests().add(this);
  }

  @Override
  public void cancel() {
    Global.getRequests().remove(this);
  }

  @Override
  public void expire() {    
  }

  @Override
  public void extend(Duration dur) {
  }

}
