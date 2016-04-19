package global;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

import database.Database;
import deal.Offer;
import deal.Request;
import user.User;

public class Global {
  private static Queue<Request> requests = new PriorityQueue<>();
  private static Queue<Offer> offers = new PriorityQueue<>();
  private static Map<String, User> activeUsers = new HashMap<>();
  private static Database database = new Database();
  
  public static Queue<Offer> getOffer() {
    return offers;
  }

  public static Queue<Request> getRequests() {
    return requests;
  }
  
  public static Map<String, User> getUsers() {
    return activeUsers;
  }

  public static Database getDb() {
    return database;
  }
  public static void setDb(String db) {
    database.setUrl(db);
  }
  
  public static String md5(String password) {
  	MessageDigest md5;
		try {
			md5 = MessageDigest.getInstance("md5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
  	md5.update(password.getBytes());
    byte[] pwd = md5.digest();
  	StringBuffer sb = new StringBuffer();
  	for (int i = 0; i < pwd.length; i++) {
  		byte b = pwd[i];
  		sb.append(String.format("%02x", b & 0xff));
  	}
  	return sb.toString();
  }
}
