package user;
import java.util.ArrayList;
import java.util.List;

import database.Query;
import deal.Deal;
import deal.Request;
import global.Global;

/**
 * represents a class for containing information about a user
 * @author lucieackley
 *
 */
public abstract class User {
	
	private List<Deal> pastOrders;
	private String name;
	private String email;
	private double rating;
	private gender gender;
	private String contact;
	private boolean subscribe;
	private String password;
	
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
	public User(String name, String email, String password, boolean subscribe){
		this.name = name;
		this.email = email;
		this.password = password;
		this.pastOrders = new ArrayList<Deal>();
	  this.subscribe = subscribe;
	}
	
	public boolean deleteUser(String email, String password) {
		//return Global.getDb().deleteUser(email, password));
		
		return Query.deleteUser(this , Global.getDb().getConnection());
	}
	
	public abstract Request postRequest();
	
	public String getContact() {
	  return contact;
	}
	
	/**
	 * returns the user's name
	 * @return name
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * returns the user's email 
	 * @return email
	 */
	public String getEmail(){
		return email;
	}
	
	/**
	 * returns the users rating
	 * @return rating
	 */
	public double getRating(){
		return rating;
	}
	
	/**
	 * adds a deal to the list of deals
	 * @param deal - deal to add
	 */
	public void addDeal(Deal deal){
		
	}
	
	/**
	 * returns the list of past deals
	 * @return pastOrders
	 */
	public List<Deal> getPastDeals(){
		return pastOrders;
	}

	/**
	 * gets the user's password
	 * @return
	 */
	public String getPassword() {
		return password;
	}
	
	public String getGenderString(){
		if(gender.equals(gender.male)){
			return "male";
		} else if(gender.equals(gender.female)){
			return "female";
		} else {
			return "undisclosed";
		}
	}
	
}
