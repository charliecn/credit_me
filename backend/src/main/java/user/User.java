package user;
import java.util.ArrayList;
import java.util.List;

import database.Query;
import deal.Deal;
import global.Global;

/**
 * represents a class for containing information about a user
 * @author lucieackley
 *
 */
public class User {
	private boolean isBrownUser = false;
	private List<Deal> pastDeals;
	private String name = "";
	private String email = "";
	private int totalRating = 0;
	private int ratingNum = 0;
	private String gender = "";
	private String contact = "0000000000";
	private boolean subscribe = false;
	private String password = "";
	
	enum title {
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
		this.pastDeals = new ArrayList<Deal>();
		this.subscribe = subscribe;
		pastDeals = new ArrayList<Deal>();
		if (email.endsWith("@brown.edu")) {
			isBrownUser = true;
		}
	}
	
	public User(String name, String email, String password, int totalRating, int ratingNum, String gender, String contact, List<Deal> pastDeals, boolean subscribe){
		this.name = name;
		this.email = email;
		this.password = password;
		this.pastDeals = new ArrayList<Deal>();
		this.subscribe = subscribe;
		this.totalRating = totalRating;
		this.ratingNum = ratingNum;
		this.contact = contact;
		this.gender = gender;
		this.pastDeals = pastDeals;
		pastDeals = new ArrayList<Deal>();
		if (email.endsWith("@brown.edu")) {
			isBrownUser = true;
		}
	}
	
	//public static boolean deleteUser(String email, String password) {
	//	return Query.deleteUser(email, password, Global.getDb().getConnection());
	//}
	
	public void setSubsribe(boolean s) {
		subscribe = s;
	}
	
	public boolean getSubscribe() {
		return subscribe;
	}
	
	public void setContact(String con) {
		contact = con;
	}
	
	public String getContact() {
	  return contact;
	}
	
	public void setName(String newName) {
		name = newName;
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
		return (double) totalRating/ (double) ratingNum;
	}
	
	public void addRating(int r) {
		totalRating += r;
		ratingNum++;
	}
	
	public int getTotalRating() {
		return totalRating;
	}
	
	public int getRatingNum() {
		return ratingNum;
	}
	
	/**
	 * adds a deal to the list of deals
	 * @param deal - deal to add
	 */
	public void addDeals(List<Deal> deal){
		pastDeals = deal;
	}
	
	/**
	 * returns the list of past deals
	 * @return pastOrders
	 */
	public List<Deal> getPastDeals(){
		return null;
	}

	/**
	 * gets the user's password
	 * @return
	 */
	public String getPassword() {
		return password;
	}

	public void setPassword(String pwd) {
		password = pwd;
	}
	
	@Override
	public String toString() {
	  return "user: email: " + email + " name: " + name + " password: " 
	+ password + " subscribe: " + subscribe + " contact: " + contact;
	}
}
