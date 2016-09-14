package deal;

import java.util.Date;
import java.util.List;

import global.Global;
import locationfood.Eatery;
import locationfood.Food;
import locationfood.Location;
import user.User;

public class Order extends HalfDeal {
  Runnable counting = () -> {
  	try {
			Thread.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
  	Global.getOrders().remove(this);
  };
  protected List<Food> orders;
  protected double actualPrice;
  protected Location location;
  protected Eatery eatery;
  
  public Order(Location loc, Eatery ety, double upper, double price, int dur, User usr, List<Food> food, Date date) {
    super(upper, dur, usr, date);
    location = loc;
    eatery = ety;
    orders = food;
    actualPrice = price;
    if (location == null) {
    	isDeliver = false;
    } else {
    	isDeliver = true;
    }
  }

  public String getFoodString() {
  	StringBuilder sb = new StringBuilder();
  	for (Food f : orders) {
  		sb.append(", " + f.name);
  	}
  	return sb.toString().substring(2);
  }
  
  public List<Food> getOrders() {
    return orders;
  }
  
  public double getActualPrice() {
  	return actualPrice;
  }
  
  public Location getLocation() {
    return location;
  }
  
  public Eatery getEatery() {
  	return eatery;
  }
  
  @Override
  protected void start() {
  	new Thread(counting).start();
  }
  
  @Override
  public void cancel() {
    Global.getOrders().remove(this);
  }
  
  @Override
  public String toString() {
    return "Order:\nbuyer: " + user.getName() + "\nactual price: " + actualPrice + 
    		"\npriceBound: " + priceBound + "\ntime: " + time.toString() + "\neatery: " + eatery + "\n";
  }
}
