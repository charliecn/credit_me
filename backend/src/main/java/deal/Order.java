package deal;

import java.util.List;

import global.Global;
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
  
  public Order(Location loc, Location ety, double upper, double price, int dur, List<Food> food, User usr) {
    super(loc, ety, upper, price, dur, food, usr);    
    Global.getOrders().add(this);
  }
  
  @Override
  protected void start() {
  	new Thread(counting).start();
  }
  
  @Override
  public void cancel() {
    Global.getOrders().remove(this);
  }
}
