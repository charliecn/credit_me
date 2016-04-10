package deal;

public class Deal {
  public final int rate;
  public final boolean complete;
  private Offer offer;
  private Request request;
  
  public Deal(Offer of, Request req, int rt, boolean comp) {
    complete = comp;
    rate = rt;
    offer = of;
    request = req;
  }

  public Request getRequest() {
    return request;
  }

  public Offer getOffer() {
    return offer;
  }
  
}
