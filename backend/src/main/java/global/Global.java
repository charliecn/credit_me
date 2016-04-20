package global;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import database.Database;
import deal.Offer;
import deal.Order;

public class Global {
  private static List<Order> orders = new CopyOnWriteArrayList<>();
  private static List<Offer> offers = new CopyOnWriteArrayList<>();
  private static Database database = new Database();
  private static String linkHead = "localhost:4000/verify/";
  private static Random random = new Random();
  private static String charStorage = 
  		"1234567890!@#$%^&*qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM";
  private static int linklength = 20;
  
  public static List<Offer> getOffer() {
    return offers;
  }

  public static List<Order> getOrders() {
    return orders;
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
  
  public static String randomLink() {
	    char[] text = new char[linklength];
	    for (int i = 0; i < linklength; i++) {
        text[i] = charStorage.charAt(random.nextInt(charStorage.length()));
	    }
	    String linkTail = new String(text);
	    return linkHead + linkTail;
	}
}
