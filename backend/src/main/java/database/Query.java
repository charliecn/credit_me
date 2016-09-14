package database;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import deal.Deal;
import deal.Offer;
import deal.Order;
import locationfood.Eatery;
import locationfood.Food;
import locationfood.Location;
import user.User;

public class Query {

	public static Object lock = new Object();
	
	/**
	 * Gets a user from the user database.
	 * @param email - contact of the user
	 * @param password - password of the user
	 * @param conn - connection to the database
	 * @return user with given email and password if it exists, null otherwise
	 * @throws SQLException if unable to connect properly
	 */
  public static User getUser(String email, String pwd, Connection conn) throws SQLException{
    ResultSet rs;
    synchronized(lock) {
	    PreparedStatement prep = 
	        conn.prepareStatement("SELECT * FROM user WHERE email = ? AND password = ?;");
	    prep.setString(1, email);
	    prep.setString(2, pwd);
    	rs = prep.executeQuery();
    }
    //should only be one user with email
    if(rs.next() == false){
      return null;
    }
    System.out.println("in getuser " + email);
    System.out.println("in getuser " + rs.getString("name"));
    
    String name = rs.getString("name");
    Boolean subscribe = rs.getBoolean("subscribe");
    String password = rs.getString("password");
    String contact = rs.getString("contact");
    int rating = rs.getInt("rating");
    int ratingNum = rs.getInt("ratingNum");
    String gender = rs.getString("gender");
    String lastComment = rs.getString("lastComment");
    rs.close();
    return new User(name, email, password, rating, ratingNum, gender, contact, subscribe, lastComment);
  }
	
	/**
	 * Get a user just given email address.
	 * @param email - user's email
	 * @param conn - connection to database
	 * @return User if there is a user with this email, null otherwise
	 * @throws SQLException - if unable to properly connect with db
	 */
	public static User getUser(String email, Connection conn) throws SQLException{
		ResultSet rs;
    synchronized(lock) {
    	PreparedStatement prep = 
				conn.prepareStatement("SELECT * FROM user WHERE email = ?;");
			prep.setString(1, email);
    	rs = prep.executeQuery();
    }
		//should only be one user with email
		if(rs.next() == false){
			return null;
		}

		String name = rs.getString("name");
		Boolean subscribe = rs.getBoolean("subscribe");
		String password = rs.getString("password");
		String contact = rs.getString("contact");
		int rating = rs.getInt("rating");
		int ratingNum = rs.getInt("ratingNum");
		String gender = rs.getString("gender");
		String lastComment = rs.getString("lastComment");
		rs.close();
		return new User(name, email, password, rating, ratingNum, gender, contact, subscribe, lastComment);
	}
	
	public static List<Deal> getHistory(String email, Connection conn) throws SQLException{
		ResultSet rs;
    synchronized(lock) {
    	PreparedStatement prep = 
				conn.prepareStatement("SELECT * FROM \"deal\" WHERE buyer = ? OR seller = ?;");
			prep.setString(1, email);
			prep.setString(2, email);
    	rs = prep.executeQuery();
    }
	  List<Deal> pastDeals = new ArrayList<>();
		//should only be one user with email
		while(rs.next()){
			String buyerEmail = rs.getString("buyer");
			String sellerEmail = rs.getString("seller");
			int eatery = rs.getInt("eatery");
			String location = rs.getString("location");
			double price = rs.getDouble("price");
			String date = rs.getString("date");
			int buyerRating = rs.getInt("buyerRating");
			int sellerRating = rs.getInt("sellerRating");
			String buyerComment = rs.getString("buyerComment");
			String sellerComment = rs.getString("sellerComment");
			boolean complete = rs.getBoolean("complete");
			
			List<Location> locs = new ArrayList<>();
			Location l = getLocation(location, conn);
			locs.add(l);
			List<Eatery> etys = new ArrayList<>();
			Eatery e = getEateryByID(eatery, conn);
			etys.add(e);
			User seller = getUser(sellerEmail, conn);
			User buyer = getUser(buyerEmail, conn);
			Offer offer = new Offer(locs, etys, price, -1, seller, -1, null);
			Order order = new Order(l, e, -1, -1, -1, buyer, null, null);
			pastDeals.add(new Deal(offer, order, buyerRating, sellerRating, buyerComment, sellerComment, complete, price, date));
		}
		rs.close();
		return pastDeals;
	}
	
	/**
	 * Delete a user from the user table.
	 * @param email - user's email
	 * @param password - user's password
	 * @param conn - connection to the database
	 * @return true if able to find and delete user, false otherwise
	 */
	public static boolean deleteUser(String email, String password, Connection conn){
		PreparedStatement prep;
		try {
	    synchronized(lock) {
				prep = conn.prepareStatement("DELETE FROM user WHERE email = ? AND password = ?");
				prep.setString(1, email);
				prep.setString(2, password);
		    prep.addBatch();
		    prep.executeBatch();
		    prep.close();
	    }
		} catch (SQLException e) {
			return false;
		}
		return true;
	}
	
	/**
   * Adds a user to the user table
   * @param user - user to add
   * @param conn - connection to database
   * @return true if able to successfully add user, false otherwise
   */
  public static boolean putUser(User user, Connection conn){
    PreparedStatement prep;
    try {
      synchronized(lock) {
	      prep = conn.prepareStatement(
	      "INSERT INTO user (email, name, password, contact, rating, ratingNum, gender, title, subscribe, lastComment)" +
	      " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
	      prep.setString(1, user.getEmail());
	      prep.setString(2, user.getName());
	      prep.setString(3, user.getPassword());
	      prep.setString(4, user.getContact());
	      prep.setInt(5, user.getTotalRating());
	      prep.setInt(6, user.getRatingNum());
	      prep.setString(7, user.getGender());
	      //TODO: WHAT IS A TITLE STRING
	      String titleString = "placeholder";
	      prep.setString(8, titleString);
	      prep.setBoolean(9, user.getSubscribe());
	      prep.setString(10, user.getLatsComment());
	      prep.addBatch();
	      prep.executeBatch();
	      prep.close();
      }
    } catch (SQLException e) {
      System.out.println(e.getMessage());
      return false;
    }
    return true;
  }
  
  public static boolean putDeal(Deal deal, Connection conn){
    PreparedStatement prep;
    try {
      synchronized(lock) {
	      prep = conn.prepareStatement(
	      "INSERT INTO \"deal\" (id, buyer, seller, eatery, location, method, price, date, buyerRating, sellerRating, buyerComment, sellerComment, complete)" +
	      " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
	      System.out.println("in put deal" + deal);
	      prep.setString(1, new Date().toString());
	      prep.setString(2, deal.getOrder().getUser().getEmail());
	      prep.setString(3, deal.getOffer().getUser().getEmail());
	      prep.setInt(4, deal.getOrder().getEatery().getId());
	      String method;
	      if (deal.getOffer().isDeliver()) {
	      	method = "deliver";
	      } else {
	      	method = "meetup";
	      }
	      if (method.equals("meetup")) {
	      	prep.setString(5, "-1");
	      } else {
	      	prep.setString(5, deal.getOrder().getLocation().getId());
	      }
	      prep.setString(6, method);
	      prep.setDouble(7, deal.getPrice());
	      prep.setString(8, deal.getTime());
	      prep.setInt(9, deal.getBuyerRating());
	      prep.setInt(10, deal.getSellerRating());
	      prep.setString(11, deal.getBuyerComment());
	      prep.setString(12, deal.getSellerComment());
	      prep.setBoolean(13, deal.isComplete());
	      prep.addBatch();
	      prep.executeBatch();
	      prep.close();
      }
    } catch (SQLException e) {
      System.out.println(e.getMessage());
      return false;
    }
    return true;
  }
  
	
	public static boolean changePassword(String email, 
			String oldPwd, String newPwd, Connection conn){
		System.out.println("old" + oldPwd);
		System.out.println("new" + newPwd);
		PreparedStatement prep;
		try {
	    synchronized(lock) {
				prep = conn.prepareStatement(
				"UPDATE user SET password = ? WHERE email = ? AND password = ?");
				prep.setString(1, newPwd);
				prep.setString(2, email);
				prep.setString(3, oldPwd);
		    prep.addBatch();
		    prep.executeBatch();
		    prep.close();
	    }
		} catch (SQLException e) {
			return false;
		}
		return true;
	}

	public static boolean updateUser(String email, String username, String contact, String subscribe, Connection conn){
		PreparedStatement prep;
		try {
	    synchronized(lock) {
				prep = conn.prepareStatement(
					"UPDATE user SET name = ?, contact = ?, subscribe = ? WHERE email = ?");
				prep.setString(1, username);
				prep.setString(2, contact);
				prep.setString(3, subscribe);
				prep.setString(4, email);
		    prep.addBatch();
		    prep.executeBatch();
		    prep.close();
	    }
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}
	
	public static boolean addComment(String user, String id, int rating, String email, String newComment, int complete, Connection conn){
		PreparedStatement prep;
		try {
	    synchronized(lock) {
	    	if (user.equals("buyer")) {
					prep = conn.prepareStatement(
							"UPDATE deal SET buyerComment = ?, buyerRating = ?, complete = ? WHERE id = ?");
					prep.setString(1, newComment);
					prep.setInt(2, rating);
					prep.setInt(3, complete);
					prep.setString(4, id);
			    prep.addBatch();
			    prep.executeBatch();
			    prep.close();
	    	} else if (user.equals("seller")) {
					prep = conn.prepareStatement(
							"UPDATE deal SET sellerComment = ?, sellerRating = ?, complete = ? WHERE id = ?");
					prep.setString(1, newComment);
					prep.setInt(2, rating);
					prep.setInt(3, complete);
					prep.setString(4, id);
			    prep.addBatch();
			    prep.executeBatch();
			    prep.close();
	    	}

		    if (rating != 0){
			    prep = conn.prepareStatement(
					"UPDATE user SET lastComment = ?, ratingNum = ratingNum + 1, rating = rating + ? WHERE email = ?");
					prep.setString(1, newComment);
					prep.setInt(2, rating);
					prep.setString(3, email);
			    prep.addBatch();
			    prep.executeBatch();
			    prep.close();
		    }
	    }
		} catch (SQLException e) {
			return false;
		}
		return true;
	}
	
	// change
	public static boolean hasComment(String email, Connection conn){
		PreparedStatement prep;
		boolean hasComment = false;
		try {
	    synchronized(lock) {
				prep = conn.prepareStatement(
				"SELECT * FROM deal WHERE buyer = ? OR seller = ?");
				prep.setString(1, email);
				prep.setString(2, email);
				ResultSet rs = prep.executeQuery();
				while (rs.next()) {
					if (rs.getString("buyer").equals(email)) {
						if (rs.getString("sellerComment").equals("No comment yet.")) {
							hasComment = true;
							break;
						}
					} else if (rs.getString("seller").equals(email)) {
						if (rs.getString("buyerComment").equals("No comment yet.")) {
							hasComment = true;
							break;
						}
					}
				}
	    }
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
		return hasComment;
	}
	
	public static boolean changePassword(String email, String newPwd, Connection conn){
		PreparedStatement prep;
		try {
	    synchronized(lock) {
				prep = conn.prepareStatement(
				"UPDATE user SET password = ? WHERE email = ?");
				prep.setString(1, newPwd);
				prep.setString(2, email);
		    prep.addBatch();
		    prep.executeBatch();
		    prep.close();
	    }
		} catch (SQLException e) {
			return false;
		}
		return true;
	}
	
	/**
	 * Get the location with the given name if it exists.
	 * @param eateryName - name of the eatery
	 * @param conn - connection to database
	 * @return the location with given name if it exists, null otherwise
	 * @throws SQLException - if unable to properly connect to db
	 */
	public static Eatery getEatery(String eateryName, Connection conn) throws SQLException{
    ResultSet rs;
    synchronized(lock) {
			PreparedStatement prep = 
					conn.prepareStatement("SELECT id, section, start, end, description FROM eatery WHERE name = ?");
			prep.setString(1, eateryName);
			
			rs = prep.executeQuery();
    }
		if(rs.next() == false){
			return null;
		}
		int id = rs.getInt("id");
		String section = rs.getString("section");		
		int start = rs.getInt("start");
		int end = rs.getInt("end");
		String description = rs.getString("description");
		rs.close();
		return new Eatery(id, eateryName, section, start, end, description);
	}
	
	public static Eatery getEateryByID(int etyID, Connection conn) throws SQLException{
    ResultSet rs;
    synchronized(lock) {
			PreparedStatement prep = 
					conn.prepareStatement("SELECT name, section, start, end, description FROM eatery WHERE id = ?");
			prep.setInt(1, etyID);
			
			rs = prep.executeQuery();
    }
		if(rs.next() == false){
			return null;
		}
		String name = rs.getString("name");
		String section = rs.getString("section");		
		int start = rs.getInt("start");
		int end = rs.getInt("end");
		String description = rs.getString("description");
		rs.close();
		return new Eatery(etyID, name, section, start, end, description);
	}
	
	public static Location getLocation(String name, Connection conn) throws SQLException{
    ResultSet rs;
    synchronized(lock) {
			PreparedStatement prep = 
					conn.prepareStatement("SELECT id, section FROM location WHERE name = ?");
			prep.setString(1, name);
			rs = prep.executeQuery();
    }
		if(rs.next() == false){
			return null;
		}
		String id = rs.getString("id");
		String section = rs.getString("section");
		rs.close();
		return new Location(id, name, section);
	}
	
	/**
	 * Gets the food with the given foodId.
	 * @param foodId - id of food
	 * @param conn - connection to database
	 * @return the Food with given id if it exists, null otherwise
	 * @throws SQLException if unable to properly connect
	 */
	public static Food getFood(String foodName, Connection conn) throws SQLException{
    ResultSet rs;
    synchronized(lock) {
			PreparedStatement prep = 
					conn.prepareStatement("SELECT price FROM food WHERE name = ?");
			prep.setString(1, foodName);
			rs = prep.executeQuery();
    }
		if(rs.next() == false){
			return null;
		}
		
		Double price = rs.getDouble("price");
		rs.close();
		return new Food(price, foodName);
	}
		
	public static List<Location> getNorthLocation(Connection conn) throws SQLException{
    ResultSet rs;
    synchronized(lock) {
			PreparedStatement prep = 
					conn.prepareStatement("SELECT * FROM location WHERE section = 'north';");
			rs = prep.executeQuery();
    }
		List<Location> toReturn = new ArrayList<>();
		while(rs.next()){
			String id = rs.getString("id");
			String name = rs.getString("name");
			toReturn.add(new Location(id, name, "north"));
		}
		rs.close();
		return toReturn;
	}
	
	public static List<Location> getSouthLocation(Connection conn) throws SQLException{
    ResultSet rs;
    synchronized(lock) {
			PreparedStatement prep = 
					conn.prepareStatement("SELECT * FROM location WHERE section = 'south';");
			rs = prep.executeQuery();
    }
		List<Location> toReturn = new ArrayList<>();
		while(rs.next()){
			String id = rs.getString("id");
			String name = rs.getString("name");
			toReturn.add(new Location(id, name, "south"));
		}
		rs.close();
		return toReturn;
	}
	
	public static List<Location> getCenterLocation(Connection conn) throws SQLException{
    ResultSet rs;
    synchronized(lock) {
			PreparedStatement prep = 
					conn.prepareStatement("SELECT * FROM location WHERE section = 'center';");
			rs = prep.executeQuery();
    }
		List<Location> toReturn = new ArrayList<>();
		while(rs.next()){
			String id = rs.getString("id");
			String name = rs.getString("name");
			toReturn.add(new Location(id, name, "center"));
		}
		rs.close();
		return toReturn;
	}
	
	public static void putLocation() throws FileNotFoundException, SQLException {
		Scanner scanner = new Scanner(new File("location_corpa"));
		int i = 0;
		PreparedStatement prep = Database.getConnection().prepareStatement(
	      "INSERT INTO \"location\" (id, name)" +
	      " VALUES (?, ?);");
		while (scanner.hasNext()) {
			String locName = scanner.nextLine();
			i++;
      prep.setString(1, String.valueOf(i));
      prep.setString(2, locName);
      prep.addBatch();
		}
    prep.executeBatch();
    prep.close();
	}
	
	public static void putFood() throws FileNotFoundException, SQLException {
		Scanner scanner = new Scanner(new File("food_corpa.js"));
		PreparedStatement prep = Database.getConnection().prepareStatement(
	      "INSERT INTO food_eatery (food_name, eatery_name, start, end, food_price)" +
	      " VALUES (?, ?, ?, ?, ?);");
		while (scanner.hasNext()) {
			String locName = scanner.nextLine();
			if (locName.matches("\".*\":\\d\\.\\d\\d,")) {
				String[] food = locName.split(":");
	      prep.setString(1, food[0].substring(1, food[0].length() - 1));
	      prep.setString(2, "Andrews Commons");
	      prep.setDouble(3, 0);
	      prep.setDouble(4, 24);
	      prep.setDouble(5, Double.parseDouble(food[1].substring(0, food[1].length() - 1)));
	      prep.addBatch();
			} else {
				System.out.println("naaaaaa");
			}
			if (scanner.hasNext()) {
				locName = scanner.nextLine();
			} else {
				break;
			}
			if (locName.matches("\".*\":\\d\\.\\d\\d,")) {
				String[] food = locName.split(":");
	      prep.setString(1, food[0].substring(1, food[0].length() - 1));
	      prep.setString(2, "Blueroom");
	      prep.setDouble(3, 0);
	      prep.setDouble(4, 24);
	      prep.setDouble(5, Double.parseDouble(food[1].substring(0, food[1].length() - 1)));
	      prep.addBatch();
			} else {
				System.out.println("naaaaaa");
			}
			if (scanner.hasNext()) {
				locName = scanner.nextLine();
			} else {
				break;
			}
			if (locName.matches("\".*\":\\d\\.\\d\\d,")) {
				String[] food = locName.split(":");
	      prep.setString(1, food[0].substring(1, food[0].length() - 1));
	      prep.setString(2, "Ivy Room");
	      prep.setDouble(3, 0);
	      prep.setDouble(4, 24);
	      prep.setDouble(5, Double.parseDouble(food[1].substring(0, food[1].length() - 1)));
	      prep.addBatch();
			} else {
				System.out.println("naaaaaa");
			}
			if (scanner.hasNext()) {
				locName = scanner.nextLine();
			} else {
				break;
			}
			if (locName.matches("\".*\":\\d\\.\\d\\d,")) {
				String[] food = locName.split(":");
	      prep.setString(1, food[0].substring(1, food[0].length() - 1));
	      prep.setString(2, "Josiah's");
	      prep.setDouble(3, 0);
	      prep.setDouble(4, 24);
	      prep.setDouble(5, Double.parseDouble(food[1].substring(0, food[1].length() - 1)));
	      prep.addBatch();
			} else {
				System.out.println("naaaaaa");
			}
		}
    prep.executeBatch();
    prep.close();
	}
	
	public static void putFoods() throws FileNotFoundException, SQLException {
		Scanner scanner = new Scanner(new File("rattymenu_purged"));
		PreparedStatement prep = Database.getConnection().prepareStatement(
	      "INSERT INTO food (name, price) VALUES (?, ?);");
		while (scanner.hasNext()) {
			String foodName = scanner.nextLine();
			System.out.println(foodName);
      prep.setString(1, foodName);
      prep.setDouble(2, 0);
      prep.addBatch();
		}
    prep.executeBatch();
    prep.close();
	}
	
	public static List<ArrayList<Object>> getEateryFood(String eatery, Date date, Connection conn) throws SQLException{
    ResultSet rs;
    synchronized(lock) {
			PreparedStatement prep = 
					conn.prepareStatement("SELECT * FROM food_eatery WHERE eatery_name = ? AND start <= ? AND end >= ?;");
			prep.setString(1, eatery);
			prep.setInt(2, date.getHours());
			prep.setInt(3, date.getHours());
			rs = prep.executeQuery();
    }
		List<ArrayList<Object>> toReturn = new ArrayList<>();
		toReturn.add(new ArrayList<Object>());
		toReturn.add(new ArrayList<Object>());
		
		while(rs.next()){
			toReturn.get(0).add(rs.getString("food_name"));
			toReturn.get(1).add(rs.getString("food_price"));
		}
		rs.close();
		
		return toReturn;
	}
}

