package global;

import java.util.ArrayList;
import java.util.List;

import deal.Deal;
import deal.Offer;
import deal.Order;

/**
 * a class for matching offers and requests
 * 
 * @author lucieackley
 */
public class Matcher {

  public static List<Deal> match(List<Offer> offer, List<Order> orders) {
    List<Deal> deals = new ArrayList<>();


    return deals;
  }
  
  public static List<Deal> match() {
    return match(Global.getOffer(), Global.getOrders());
  }

  public static Deal matchOrder(Order order) {
  	boolean isDeliver = order.isDeliver();
  	List<Offer> offers = Global.getOffer();
  	if (isDeliver) {
	  	for (Offer of : offers) {
	  		if (deliver != isDeliver) {
	  			continue;
	  		}
	  		
	  	}
  	} else {
	  	for (Offer of : offers) {
	  		if (deliver != isDeliver) {
	  			continue;
	  		}
	  		if (!order.getEatery().equals(of.getEatery())) {
	  			continue;
	  		}
	  		
	  	}
  	}
  	
    Deal deal;
    return null;
  }

  public static Deal matchOffer(Offer offer) {
    Deal deal;


    return null;
  }

}
