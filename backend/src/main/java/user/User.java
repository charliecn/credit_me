package user;
import java.util.ArrayList;
import java.util.List;

import deal.Deal;
import deal.Request;

/**
 * represents a class for containing information about a user
 * @author lucieackley
 *
 */
public abstract class User {
	
	private List<Deal> pastOrders;
	private String name;
	private String email;
	private String id;
	private int rating;
	private title[] titles;
	private gender gender;
	
	enum title {
	  
	}
	
	enum gender {
	  male, female, undisclosed;
	}
	
	/**
	 *  a user takes in a name, email, id and rating
	 * @param name - the name of the user
	 * @param email - the email of the user
	 * @param id - the id of the user
	 * @param rating - the rating of the user
	 */
	public User(String name, String email, String id, int rating, gender gender){
		this.name = name;
		this.email = email;
		this.id = id;
		this.rating = rating;
		this.pastOrders = new ArrayList<Deal>();
	}
	
	public abstract void deleteUser();
	
	public abstract Request postRequest();
	
	
	public title[] getTitles() {
	  return titles;
	}
	
	/**
	 * returns the user's name
	 * @return name
	 */
	public String getName(){
		return this.name;
	}
	
	/**
	 * returns the user's email 
	 * @return email
	 */
	public String getEmail(){
		return this.email;
	}
	
	/**
	 * returns the user's id
	 * @return id
	 */
	public String getId(){
		return this.id;
	}
	
	/**
	 * returns the users rating
	 * @return rating
	 */
	public int getRating(){
		return this.rating;
	}
	
	/**
	 * adds a deal to the list of deals
	 * @param deal - deal to add
	 */
	public void addDeal(Deal deal){
		pastOrders.add(deal);
		updateTitles();
	}
	
	/**
	 * returns the list of past deals
	 * @return pastOrders
	 */
	public List<Deal> getPastDeals(){
		return pastOrders;
	}
	
	protected abstract void updateTitles();
}
