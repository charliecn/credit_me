package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import deal.Deal;
import locationfood.Eatery;
import locationfood.Food;
import locationfood.Location;
import user.User;

public class Query {

	/**
	 * Gets a user from the user database.
	 * @param email - contact of the user
	 * @param password - password of the user
	 * @param conn - connection to the database
	 * @return user with given email and password if it exists, null otherwise
	 * @throws SQLException if unable to connect properly
	 */
	public static User getUser(String email, String password, Connection conn) 
			throws SQLException{
		

		PreparedStatement prep = 
				conn.prepareStatement("SELECT name, subscribe FROM user WHERE email = ? AND password = ?");
		
		prep.setString(1, email);
		prep.setString(2, password);
		
		ResultSet rs = prep.executeQuery();
		
		//should only be one user with email
		if(rs.next() == false){
			return null;
		}

		String name = rs.getString("name");
		Boolean subscribe = rs.getBoolean("subscribe");
		
		return new User(name, email, password, subscribe);
	}
	
	/**
	 * Get a user just given email address.
	 * @param email - user's email
	 * @param conn - connection to database
	 * @return User if there is a user with this email, null otherwise
	 * @throws SQLException - if unable to properly connect with db
	 */
	public static User getUser(String email, Connection conn) throws SQLException{
		PreparedStatement prep = 
				conn.prepareStatement("SELECT * FROM user WHERE email = ?");
		
		prep.setString(1, email);
		
		ResultSet rs = prep.executeQuery();
		
		//should only be one user with email
		if(rs.next() == false){
			return null;
		}

		String name = rs.getString("name");
		Boolean subscribe = rs.getBoolean("subscribe");
		String password = rs.getString("password");
		String contact = rs.getString("contact");
		int rating = rs.getInt("rating");
		int ratingNum = rs.getInt("rating_num");
		String gender = rs.getString("gender");
		
		prep = 
				conn.prepareStatement("SELECT * FROM order WHERE seller = ? OR buyer = ?");
		prep.setString(1, email);
		prep.setString(2, email);
		rs = prep.executeQuery();
		
	  List<Deal> pastDeals = new ArrayList<>();
		// TODO add deals
		return new User(name, email, password, rating, ratingNum, gender, contact, pastDeals, subscribe);
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
			prep = conn.prepareStatement("DELETE FROM user WHERE email = ? AND password = ?");
			prep.setString(1, email);
			prep.setString(2, password);
	    prep.addBatch();
	    prep.executeBatch();
	    prep.close();
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
			prep = conn.prepareStatement(
			"INSERT INTO user VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
			prep.setString(1, user.getEmail());
			prep.setString(2, user.getName());
			prep.setString(3, user.getPassword());
			prep.setString(4, user.getContact());
			prep.setInt(5, user.getTotalRating());
			prep.setInt(6, user.getRatingNum());
			//TODO: WHAT IS A TITLE STRING
			String titleString = "placeholder";
			prep.setString(7, "");
			prep.setString(8, "");
			prep.setBoolean(9, user.getSubscribe());
	    prep.addBatch();
	    prep.executeBatch();
	    prep.close();
		} catch (SQLException e) {
			return false;
		}
		return true;
	}
	
	public static boolean changePassword(String email, 
			String oldPwd, String newPwd, Connection conn){
		PreparedStatement prep;
		try {
			prep = conn.prepareStatement(
			"UPDATE user SET password = ? WHERE email = ? AND password = ?");
			prep.setString(1, newPwd);
			prep.setString(2, email);
			prep.setString(3, oldPwd);
	    prep.addBatch();
	    prep.executeBatch();
	    prep.close();
		} catch (SQLException e) {
			return false;
		}
		return true;
	}
	
	public static boolean changePassword(String email, String newPwd, Connection conn){
		PreparedStatement prep;
		try {
			prep = conn.prepareStatement(
			"UPDATE user SET password = ? WHERE email = ?");
			prep.setString(1, newPwd);
			prep.setString(2, email);
	    prep.addBatch();
	    prep.executeBatch();
	    prep.close();
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
		PreparedStatement prep = 
				conn.prepareStatement("SELECT id, section, start, end, description FROM eatery WHERE name = ?");
		
		prep.setString(1, eateryName);
		
		ResultSet rs = prep.executeQuery();
		
		if(rs.next() == false){
			return null;
		}
		String id = rs.getString("id");
		String section = rs.getString("section");		
		int start = rs.getInt("start");
		int end = rs.getInt("end");
		String description = rs.getString("description");
			
		return new Eatery(id, eateryName, section, start, end, description);
		
	}
	
	public static Location getLocation(String name, Connection conn) throws SQLException{
		PreparedStatement prep = 
				conn.prepareStatement("SELECT id, section FROM location WHERE name = ?");
		
		prep.setString(1, name);
		
		ResultSet rs = prep.executeQuery();
		
		if(rs.next() == false){
			return null;
		}
		String id = rs.getString("id");
		String section = rs.getString("section");		
		
			
		return new Location(id, name, section);
		
	}
	
	/**
	 * Gets the food with the given foodId.
	 * @param foodId - id of food
	 * @param conn - connection to database
	 * @return the Food with given id if it exists, null otherwise
	 * @throws SQLException if unable to properly connect
	 */
	public static Food getFood(String foodId, Connection conn) throws SQLException{
		PreparedStatement prep = 
				conn.prepareStatement("SELECT name, price FROM food WHERE foodId = ?");
		
		prep.setString(1, foodId);
		
		ResultSet rs = prep.executeQuery();
		if(rs.next() == false){
			return null;
		}
		
		String name = rs.getString("name");
		Double price = rs.getDouble("price");
		return new Food(price, name);
		
	}
}
