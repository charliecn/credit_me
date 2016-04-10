package global;

import java.util.TreeSet;

import database.Database;
import deal.Offer;
import deal.Request;

public class Global {
  private static TreeSet<Request> requests = new TreeSet<>();
  private static TreeSet<Offer> offers = new TreeSet<>();
  private static Database db = new Database();
    
  public static TreeSet<Offer> getOffer() {
    return offers;
  }

  public static TreeSet<Request> getRequests() {
    return requests;
  }

  public static Database getDb() {
    return db;
  }
  public static void setDb(Database db) {
    
  }
  
}
