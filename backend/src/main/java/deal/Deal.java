package deal;

public class Deal {
  private int rate = -1;
  private boolean complete = false;
  private String comment = null;
  private Offer offer;
  private Order order;
  private double price;

  public Deal(Offer of, Order req, double pri) {
    offer = of;
    order = req;
    price = pri;
  }
  
  public Deal(Offer of, Order req, int rt, String cmt, boolean comp, double pri) {
    complete = comp;
    rate = rt;
    offer = of;
    order = req;
    comment = cmt;
    price = pri;
  }

  public Order getOrder() {
    return order;
  }

  public Offer getOffer() {
    return offer;
  }
}
