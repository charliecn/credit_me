package deal;

public class Deal {
  public final int rate;
  public final boolean complete;
  public final String comment;
  private Offer offer;
  private Order request;

  public Deal(Offer of, Order req, int rt, String cmt, boolean comp) {
    complete = comp;
    rate = rt;
    offer = of;
    request = req;
    comment = cmt;
  }

  public Order getRequest() {
    return request;
  }

  public Offer getOffer() {
    return offer;
  }
}
