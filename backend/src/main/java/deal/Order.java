package deal;

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
  
  public Order(Location loc, Eatery ety, double upper, double price, int dur, User usr, List<Food> food) {
    super(loc, ety, upper, price, dur, usr);
    orders = food;
    Global.getOrders().add(this);
  }

  public List<Food> getOrders() {
    return orders;
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
