package deal;

import java.util.List;

import locationfood.Eatery;

public class HalfDealObject {
	private String eatery;
	private String time;
	private int duration;
	private String location = null;
	private boolean center;
	private boolean north;
	private boolean south;
	private String userName;
	private double actualPrice;
	private double priceBound;
	private int creditNum;
	private boolean deliver;
	
	public HalfDealObject(String e, String t, int dur) {
		eatery = e;
		time = t;
		duration = dur;
	}
	
	public HalfDealObject(Offer offer) {
		List<Eatery> eateries = offer.getEateries();
		StringBuilder sb = new StringBuilder();
		for (Eatery e : eateries) {
			sb.append(", " + e.getEateryName());
		}
		eatery = sb.toString().substring(2);
		time = offer.getTimeString();
		duration = offer.getDuration() / 600000;
		deliver = offer.isDeliver();
	  //if (offer.getLocations().contains(new Location(1, null, null)));
	}
	
	public HalfDealObject(Order order) {
		
	}
}
