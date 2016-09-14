package deal;

public class Deal {
	private int buyerRating = -1;
  private int sellerRating = -1;
  private boolean complete = false;
  private String buyerComment = "No comment yet.";
  private String sellerComment = "No comment yet.";
  private Offer offer;
  private Order order;
  private double price;
  private String time;

  public Deal(Offer of, Order req, double pri, String date) {
    offer = of;
    order = req;
    price = pri;
    time = date;
  }
  
  public Deal(Offer of, Order req, int brt, int srt, String buyercmt, String sellercmt, boolean comp, double pri, String date) {
    complete = comp;
    buyerRating = brt;
    sellerRating = srt;
    offer = of;
    order = req;
    buyerComment = buyercmt;
    sellerComment = sellercmt;
    price = pri;
    time = date;
  }

  public String getTime() {
  	return time;
  }
  
  public double getPrice() {
  	return price;
  }
  
  public Order getOrder() {
    return order;
  }

  public Offer getOffer() {
    return offer;
  }
  
  public int getBuyerRating() {
    return buyerRating;
  }
  
  public int getSellerRating() {
    return sellerRating;
  }
  
  public String getBuyerComment() {
  	return buyerComment;
  }
  
  public String getSellerComment() {
  	return sellerComment;
  }
  
  public boolean isComplete() {
  	return complete;
  }
  
  @Override
  public String toString() {
    return "Deal:\nOffer: " + offer + "\n Order: " + order + "\n price: " + price + "\n";
  }
}
