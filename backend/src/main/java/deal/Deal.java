package deal;

public class Deal {
  public final int rate;
  public final boolean complete;
  public final String comment;
  private Offer offer;
  private Request request;

  public Deal(Offer of, Request req, int rt, String cmt, boolean comp) {
    complete = comp;
    rate = rt;
    offer = of;
    request = req;
    comment = cmt;
  }

  public Request getRequest() {
    return request;
  }

  public Offer getOffer() {
    return offer;
  }

}
