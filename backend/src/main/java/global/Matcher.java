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
  public static List<Deal> matchOrder(Order order) {
  	double actualPrice = order.getActualPrice();
  	boolean isDeliver = order.isDeliver();
    Deal match = null;
    List<Deal> suggestions = new ArrayList<>();
  	List<Offer> offers = Global.getOffer();
  	for (Offer of : offers) {
  		// check whether deliver method matches
  		if (of.isDeliver() != isDeliver) {
  			continue;
  		}
  		// check whether the location matches
  		if (isDeliver && !of.getLocations().contains(order.getLocation())) {
  			continue;
  		}
  		// check whether the eatery matches
  		if (!of.getEateries().contains(order.getEatery())) {
  			continue;
  		}
  		// adjust lowbound price
  		double creditBound = order.getPriceBound();
  		double lowerBound = generateLowerBound(actualPrice, creditBound);
  		// match by price
  		if (order.getPriceBound() < lowerBound) {
  			// price out of bound: put in suggestion
  			Deal deal = new Deal(of, order, lowerBound);
  			suggestions.add(deal);
  			if (suggestions.size() == 3) {
  				break;
  			}
  		} else {
  			// in bound: match found
  			match = new Deal(of, order, lowerBound);
  			Global.getOrders().remove(order);
  			Global.getOrders().remove(of);
  			break;
  		}
  	}
  	
  	if (match == null) {
  		return suggestions;
  	} else {
  		ArrayList<Deal> toReturn = new ArrayList<>();
  		toReturn.add(match);
  		toReturn.add(match);
  		toReturn.add(match);
  		toReturn.add(match);
  		return toReturn;
  	}
  }

  public static List<Deal> matchOffer(Offer offer) {
		double creditBound = offer.getPriceBound();
  	boolean isDeliver = offer.isDeliver();
    Deal match = null;
    List<Deal> suggestions = new ArrayList<>();
  	List<Order> orders = Global.getOrders();
  	for (Order or : orders) {
  		// check whether deliver method matches
  		if (or.isDeliver() != isDeliver) {
  			continue;
  		}
  		// check whether the location matches
  		if (isDeliver && !offer.getLocations().contains(or.getLocation())) {
  			continue;
  		}
  		// check whether the eatery matches
  		if (!offer.getEateries().contains(or.getEatery())) {
  			continue;
  		}
  		// adjust lowbound price
  		double actualPrice = or.getActualPrice();
  		double lowerBound = generateLowerBound(actualPrice, creditBound);
  		// match by price
  		if (or.getPriceBound() < lowerBound) {
  			// price out of bound: put in suggestion
  			Deal deal = new Deal(offer, or, lowerBound);
  			suggestions.add(deal);
  			if (suggestions.size() == 3) {
  				break;
  			}
  		} else {
  			// in bound: match found
  			match = new Deal(offer, or, lowerBound);
  			Global.getOrders().remove(offer);
  			Global.getOrders().remove(or);
  			break;
  		}
  	}
  	
  	if (match == null) {
  		return suggestions;
  	} else {
  		ArrayList<Deal> toReturn = new ArrayList<>();
  		toReturn.add(match);
  		toReturn.add(match);
  		toReturn.add(match);
  		toReturn.add(match);
  		return toReturn;
  	}
  }
  
  private static double generateLowerBound(double actualPrice, double creditBound) {
  	double points = 0;
  	int state = 0;
		if (actualPrice > 11.3) {
			points = actualPrice - 14.6;
			state = 2;
		} else if (actualPrice > 7.3) {
			points = actualPrice - 7.3;
			state = 1;
		}

		double lowerBound;
		if (state == 0) {
			lowerBound = creditBound;
		} else if (state == 1) {
			lowerBound = Math.min(creditBound + points, creditBound * 2);
		} else {
			lowerBound = creditBound * 2 + points;
		}
	  return lowerBound;
  }
}
