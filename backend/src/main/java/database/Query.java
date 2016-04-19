package database;

import user.User;
import user.BrownUser;
import user.GuestUser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
		
		if(isBrownEmail(email)){
			return new BrownUser(name, email, password, subscribe);
		} else {
			return new GuestUser(name, email, password, subscribe);
		}
	}

	/**
	 * Determines whether string is a brown email address.
	 * @param email - email address to check
	 * @return true if email is brown email address
	 */
	private static boolean isBrownEmail(String email) {
		int atInd = email.indexOf("@");
		if(atInd == -1){
			return false;
		}
		return email.substring(atInd).equals("brown.edu");
	}
	
	public static boolean deleteUser(User user, Connection conn){
		PreparedStatement prep;
		try {
			prep = conn.prepareStatement("DELETE FROM user WHERE email = ?");
			prep.setString(1, user.getEmail());
		} catch (SQLException e) {
			return false;
		}
		return true;
	}
	
	public static boolean putUser(User user, Connection conn){
		PreparedStatement prep;
		try {
			prep = conn.prepareStatement(
			"INSERT INTO user (email, name, password, contact, rating, gender, title, subscribe)" +
			" VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
			prep.setString(1, user.getEmail());
			prep.setString(2, user.getName());
			prep.setString(3, user.getPassword());
			prep.setString(4, user.getContact());
			prep.setDouble(5, user.getRating());
			prep.setString(6, user.getGenderString());
			//TODO: WHAT IS A TITLE STRING
			String titleString = "?";
			prep.setString(7, titleString);
			//TODO: WHAT IS SUBSCRIBE
			String subscribe = "?";
			prep.setString(8, subscribe);
		} catch (SQLException e) {
			return false;
		}
		return true;
	}
	
	public static boolean changePassword(String email, 
			String oldPwd, String newPwd, Connection conn){
		
	}
}
