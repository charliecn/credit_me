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
  		/*
  		// check whether the location matches
  		if (isDeliver && !of.getLocations().contains(order.getLocation())) {
  			continue;
  		}
  		*/
  		// check whether the eatery matches
  		if (!of.getEateries().contains(order.getEatery())) {
  			continue;
  		}
  		// adjust lowbound price
  		double creditBound = order.getPriceBound();
  		int creditNum = of.getCreditNum();
  		double lowerBound = generateLowerBound(creditNum, actualPrice, creditBound);
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
		int creditNum = offer.getCreditNum();
  	boolean isDeliver = offer.isDeliver();
    Deal match = null;
    List<Deal> suggestions = new ArrayList<>();
  	List<Order> orders = Global.getOrders();
  	for (Order or : orders) {
  		// check whether deliver method matches
  		if (or.isDeliver() != isDeliver) {
  			continue;
  		}
  		/*
  		// check whether the location matches
  		if (isDeliver && !offer.getLocations().contains(or.getLocation())) {
  			continue;
  		}
  		*/
  		// check whether the eatery matches
  		if (!offer.getEateries().contains(or.getEatery())) {
  			continue;
  		}
  		// adjust lowbound price
  		double actualPrice = or.getActualPrice();
  		double lowerBound = generateLowerBound(creditNum, actualPrice, creditBound);
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
  
  private static double generateLowerBound(int creditNum, double actualPrice, double creditBound) {
  	double points = 0;
    double lowerBound;

		if (actualPrice > 11.3 && creditNum == 2) {
			points = Math.min(actualPrice - 14.6, 0);
      lowerBound = creditBound * 2 + points;
		} else if (actualPrice > 11.3 && creditNum == 1) {
      lowerBound = 100;
		} else if (actualPrice > 7.3 && creditNum == 2) {
			points = actualPrice - 7.3;
      lowerBound = Math.min(creditBound + points, creditBound * 2);
		} else if (actualPrice > 7.3 && creditNum == 1) {
			points = actualPrice - 7.3;
      lowerBound = creditBound + points;
		} else {
      lowerBound = creditBound;
		}
	  return lowerBound;
  }
}
