package deal;

import geo.Location;
import global.Global;

import java.time.Clock;
import java.time.Duration;

import user.User;

public class Offer extends HalfDeal{

  public Offer(int id, Location loc, Clock clock, Duration dur,
      String note, double upper, double lower, User usr) {
    super(id, loc, clock, dur, note, upper, lower, usr);
    Global.getOffer().add(this);
  }

  @Override
  public void cancel() {
    Global.getOffer().remove(this);
  }
  
}
