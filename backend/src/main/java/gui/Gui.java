package gui;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;

import database.Database;
import database.Query;
import deal.Deal;
import deal.DealObject;
import deal.HalfDealObject;
import deal.Offer;
import deal.Order;
import emailer.EmailSender;
import freemarker.template.Configuration;
import global.Global;
import global.Matcher;
import locationfood.Eatery;
import locationfood.Food;
import locationfood.Location;
import spark.ModelAndView;
import spark.QueryParamsMap;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;
import spark.TemplateViewRoute;
import spark.template.freemarker.FreeMarkerEngine;
import user.User;

public class Gui {
  /**
   * GSON to create json object.
   */
  private static final Gson GSON = new Gson();
  /**
   * port number.
   */
	int PORTNUMBER = 4000;
	/**
	 * matcher.
	 */
	Matcher matcher;
	
	private Connection conn;
	/**
	 * constructor.
	 */
  public Gui(String db) {
  	Global.setDb(db);
  	conn = Database.getConnection();
  }

  /**
   * Starts the spark server. Initializes different handlers to manage
   * REST and generate web page templates with autocorrect.
   */
  public void run() {    
    (new Thread() {
      public void run() {
        while (true) {
					Global.refreshRattyVdubFood();
					try {
						Thread.sleep(600000);
					} catch (InterruptedException e) {
						e.printStackTrace();
						return;
					}
        }
      }
    }).start();
    runSparkServer();
  }

  /**
   * FreeMarkerEngine builder.
   * @return New Freemarker engine.
   */
  private static FreeMarkerEngine createEngine() {
    Configuration config = new Configuration();
    File templates = new File(
        "src/main/resources/spark/template/freemarker");
    try {
      config.setDirectoryForTemplateLoading(templates);
    } catch (IOException ioe) {
      System.out.printf(
          "ERROR: Unable use %s for template loading.\n", templates);
      System.exit(1);
    }
    return new FreeMarkerEngine(config);
  }

  /** Initial spark server setup. */
  private void runSparkServer() {
    Spark.setPort(PORTNUMBER);
    Spark.externalStaticFileLocation("src/main/resources/static");
    FreeMarkerEngine freeMarker = createEngine();
    // Setup Spark Routes
    Spark.get("/home", new LandingPage(), freeMarker);
    Spark.post("/userlogin", new UserLoginHandler());
    Spark.post("/signupemail", new SignUpEmailHandler());
    Spark.get("/verify/:random", new VerifyHandler(), freeMarker);
    Spark.post("/userforgetpwd", new UserForgetPwdHandler());
    Spark.post("/passwordemail", new PasswordEmailHandler());
    Spark.get("/forgetpwd/:random", new PasswordVerifyHandler(), freeMarker);
    Spark.post("/userchangepwd", new UserChangePwdHandler());
    Spark.post("/changeinfo", new ChangeInfoHandler());
    Spark.post("/addcomment", new AddCommentHandler());
    Spark.post("/placeorder", new PlaceOrderHandler());
    Spark.post("/placeoffer", new PlaceOfferHandler());
    Spark.post("/getdeals", new GetDealsHandler());
    Spark.post("/deleteuser", new DeleteUserHandler());
    Spark.post("/deletedeal", new DeleteDealHandler());
    Spark.post("/getmenu", new GetMenuHandler());
    Spark.post("/putdeal", new PutDealHandler());
    Spark.post("/market", new MarketHandler());
    /*Spark.get("/*", (q, a) -> {
      throw new NoSuchElementException();
    });
    Spark.exception(NoSuchElementException.class, (e, request, response) -> {
      response.status(404);
      response.body();
    });*/
  }

  /** The main view of the GUI. */
  private class LandingPage implements TemplateViewRoute {
    @Override
    public ModelAndView handle(Request req, Response res) {
      Map<String, Object> variables;
      variables = ImmutableMap.of();
      return new ModelAndView(variables, "home.ftl");
    }
  }
  
  private static class ErrorPageHandler implements TemplateViewRoute {
    @Override
    public ModelAndView handle(Request req, Response res) {
      Map<String, Object> variables = ImmutableMap.of();
      return new ModelAndView(variables, "404.ftl");
    }
  }
  
  private class UserLoginHandler implements Route {
    @Override
    public Object handle(final Request req, final Response res) {
      QueryParamsMap qm = req.queryMap();
      String email = qm.value("email");
      //loginEmail = email;
      String password = qm.value("pwd");
      //get user
      User user = null;
			try {
				user = Query.getUser(email, Global.md5(password), conn);
			} catch (SQLException e) {
      	e.printStackTrace();
      	Map<String, Object> variables = new ImmutableMap.Builder()
	          .put("error", e.getMessage()).build();
      	return variables;
			}			
      if (user == null) {
	      Map<String, Object> variables = new ImmutableMap.Builder()
	          .put("error", "incorrect email address or password!").build();
	      return GSON.toJson(variables);
      } else {
        //loginEmail = email;
	      Map<String, Object> variables = new ImmutableMap.Builder()
	      		.put("comment", Query.hasComment(email, conn))
	          .put("user", user).build();
	      return GSON.toJson(variables);
      }
    }
  }
  
  private class DeleteUserHandler implements Route {
    @Override
    public Object handle(final Request req, final Response res) {
      QueryParamsMap qm = req.queryMap();
      String email = qm.value("email");
      String pwd = qm.value("pwd");
      boolean success = Query.deleteUser(email, pwd, conn);
      Map<String, Object> variables = new ImmutableMap.Builder()
          .put("done", success).build();
      return GSON.toJson(variables);
    }
  }
  
  private class SignUpEmailHandler implements Route {
    @Override
    public Object handle(final Request req, final Response res) {
      QueryParamsMap qm = req.queryMap();
      String email = qm.value("email");
      String name = qm.value("username");
      String pwd = qm.value("pwd");
      String subscribe = qm.value("subscribe");
      String gend = qm.value("gender");
      boolean subs = Boolean.parseBoolean(subscribe);
      try {
				if (Query.getUser(email, conn) != null) {
					Map<String, Object> variables = new ImmutableMap.Builder()
		      		.put("done", false).build();
		      return GSON.toJson(variables);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	User user = new User(name, email, Global.md5(pwd), subs, gend);
    	
      String subject = "credit_me sign up verification";
      String link = Global.randomLink("verify/");
      String body = "Welcome! click the following link to verify your email:\n" + link;
    	Global.getRegisteringUsers().put(link, user);
    	boolean success = EmailSender.sendEmail(email, subject, body);
      Map<String, Object> variables = new ImmutableMap.Builder()
      		.put("done", success).build();
      return GSON.toJson(variables);
    }
  }

  private class VerifyHandler implements TemplateViewRoute {
    @Override
    public ModelAndView handle(final Request req, final Response res) {
      String link = req.params(":random");
      String key = Global.linkHead + "verify/" + link;
      if (Global.getRegisteringUsers().containsKey(key)) {
      	User user = Global.getRegisteringUsers().remove(key);
      	boolean success = Query.putUser(user, conn);  	
      	Map<String, Object> variables = new ImmutableMap.Builder()
        		.put("valid", "Congrats! You have successfully signed up. Now you can log in and start exchange credit!").build();
        return new ModelAndView(variables, "verify.ftl");
      }
      Map<String, Object> variables = new ImmutableMap.Builder()
      		.put("valid", "Error! Invalid URL.\n\nYou can try to sign up again and request a new link!").build();
      return new ModelAndView(variables, "verify.ftl");
    }
  }

  private class PasswordEmailHandler implements Route {
    @Override
    public Object handle(final Request req, final Response res) {
      QueryParamsMap qm = req.queryMap();
      String email = qm.value("email");
      try {
				if (Query.getUser(email, conn) == null) {
					Map<String, Object> variables = new ImmutableMap.Builder()
				      .put("done", false).build();
				  return GSON.toJson(variables);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				Map<String, Object> variables = new ImmutableMap.Builder()
	          .put("done", false).build();
	      return GSON.toJson(variables);
			}
      String subject = "credit_me forget password";
      String link = Global.randomLink("forgetpwd/");
      String body = "Visit the following link to reset your password:\n" + link;
      Global.getForgetPwds().put(link, email);
    	boolean success = EmailSender.sendEmail(email, subject, body);
      Map<String, Object> variables = new ImmutableMap.Builder()
          .put("done", success).build();
      return GSON.toJson(variables);
    }
  }
  
  private class PasswordVerifyHandler implements TemplateViewRoute {
    @Override
    public ModelAndView handle(final Request req, final Response res) {
      //String person = req.params(":id");
      String link = req.params(":random");
      String email = Global.getForgetPwds().remove(Global.linkHead + "forgetpwd/" + link);
    	
      Map<String, Object> variables = new ImmutableMap.Builder()
      		.put("email", email).build();
      return new ModelAndView(variables, "forgetpwd.ftl");
    }
  }

  private class UserForgetPwdHandler implements Route {
    @Override
    public Object handle(final Request req, final Response res) {
      QueryParamsMap qm = req.queryMap();
      String email = qm.value("email");
      String password = qm.value("pwd");
      boolean success = Query.changePassword(email, Global.md5(password), conn);
      Map<String, Object> variables = new ImmutableMap.Builder()
          .put("done", success).build();
      return GSON.toJson(variables);
    }
  }

  private class UserChangePwdHandler implements Route {
    @Override
    public Object handle(final Request req, final Response res) {
      QueryParamsMap qm = req.queryMap();
      String email = qm.value("email");
      String prevPwd = qm.value("prevPwd");
      String newPwd = qm.value("newPwd");
      System.out.println("prev" + prevPwd);
      System.out.println("email" + email);
      System.out.println("newpwd" + newPwd);
      User user;
			try {
				user = Query.getUser(email, Global.md5(prevPwd), conn);
				System.out.println(user);
			} catch (SQLException e) {
      	e.printStackTrace();
      	Map<String, Object> variables = new ImmutableMap.Builder()
	          .put("error", e.getMessage()).build();
      	return variables;
			}
      if (user == null) {
	      Map<String, Object> variables = ImmutableMap.<String, Object>builder()
	          .put("error", "incorrect password!").build();
	      return GSON.toJson(variables);
      } else {
      	Query.changePassword(email, Global.md5(prevPwd), Global.md5(newPwd), conn);
      	System.out.println(newPwd);
	      Map<String, Object> variables = ImmutableMap.<String, Object>builder().build();
	      return GSON.toJson(variables);
      }
    }
  }

  private class ChangeInfoHandler implements Route {
    @Override
    public Object handle(final Request req, final Response res) {
      QueryParamsMap qm = req.queryMap();
      String name = qm.value("username");
      String contact = qm.value("contact");
      String email = qm.value("email");
      String subscribe = qm.value("subscribe");
      
    	User user;
      boolean success= Query.updateUser(email, name, contact, subscribe, conn);
      Map<String, Object> variables = ImmutableMap.<String, Object>builder()
          .put("done", success).build();
      return GSON.toJson(variables);
    }
  }
  
  private class AddCommentHandler implements Route {
    @Override
    public Object handle(final Request req, final Response res) {
      QueryParamsMap qm = req.queryMap();
      String user = qm.value("user");
      String time = qm.value("time");
      int rating = Integer.parseInt(qm.value("rating"));
      int complete = Integer.parseInt(qm.value("complete"));
      String newComment = qm.value("comment");
      String email = qm.value("email");
      
      boolean success= Query.addComment(user, time, rating, email, newComment, complete, conn);
      Map<String, Object> variables = ImmutableMap.<String, Object>builder()
          .put("done", success).build();
      return GSON.toJson(variables);
    }
  }
  
  private class MarketHandler implements Route {
    @Override
    public Object handle(final Request req, final Response res) {
    	System.out.println("++++++" + Global.getOffer());
    	System.out.println("++++++" + Global.getOrders());
    	
      List<Order> orders = Global.getOrders();
      int len = orders.size();
      orders = orders.subList(0, Math.min(len, 5));
      List<Offer> offers = Global.getOffer();
      len = offers.size();
      offers = offers.subList(0, Math.min(len, 5));
    	List<String> buyerNames = new ArrayList<>();
    	List<Double> buyerPrices = new ArrayList<>();
    	List<String> buyerLocations  = new ArrayList<>();
    	List<String> buyerGenders = new ArrayList<>();
    	List<Double> buyerBounds = new ArrayList<>();
    	List<Boolean> buyerDelivers = new ArrayList<>();
    	List<String> buyerEateries = new ArrayList<>();
    	List<Integer> buyerDurations = new ArrayList<>();
    	List<String> sellerNames = new ArrayList<>();
    	List<ArrayList<String>> sellerEateries = new ArrayList<>();
    	List<Boolean> sellerDelivers = new ArrayList<>();
    	List<ArrayList<String>> sellerLocations = new ArrayList<>();
    	List<Double> sellerBounds = new ArrayList<>();
    	List<Integer> creditBounds = new ArrayList<>();
    	List<Integer> sellerDurations = new ArrayList<>();
    	List<String> sellerGenders = new ArrayList<>();
    	
    	for (Order or : orders) {
    		buyerNames.add(or.getUser().getName());
    		buyerPrices.add(or.getActualPrice());    		
    		buyerEateries.add(or.getEatery().getEateryName());
    		if (!or.isDeliver()) {
    			buyerLocations.add("");
    		} else {
    			buyerLocations.add(or.getLocation().getName());
    		}
    		buyerGenders.add(or.getUser().getGender());
    		buyerBounds.add(new Double(or.getPriceBound()));
    		buyerDelivers.add(new Boolean(or.isDeliver()));
    		long now = new Date().getTime();
    		long time = or.getTime().getTime();
    		buyerDurations.add((int) ((or.getDuration() - now + time)/600000));
    	}
    	
    	for (Offer of : offers) {
    		sellerNames.add(of.getUser().getName());
    		sellerDelivers.add(of.isDeliver());
    		sellerBounds.add(of.getPriceBound());
    		sellerGenders.add(of.getUser().getGender());
    		creditBounds.add(of.getCreditNum());
    		List<Eatery> eatery = of.getEateries();
    		ArrayList<String> eateryString = new ArrayList<>();
    		for (Eatery e : eatery) {
    			eateryString.add(e.getEateryName());
    		}
    		sellerEateries.add(eateryString);
    		ArrayList<String> sections = new ArrayList<>();
    		if (of.getLocations().size() == 36) {
    			sections.add("north");
    		} else if (of.getLocations().size() == 65) {
    			sections.add("center");
    		} else if (of.getLocations().size() == 52) {
    			sections.add("south");
    		} else if (of.getLocations().size() == 101) {
    			sections.add("north");
    			sections.add("center");
    		} else if (of.getLocations().size() == 88) {
    			sections.add("north");
    			sections.add("south");
    		} else if (of.getLocations().size() == 117) {
    			sections.add("center");
    			sections.add("south");
    		} else if (of.getLocations().size() == 153) {
    			sections.add("north");
    			sections.add("center");
    			sections.add("south");
    		}
    		sellerLocations.add(sections);
    		long now = new Date().getTime();
    		long time = of.getTime().getTime();
    		sellerDurations.add((int) ((of.getDuration() - now + time)/600000));
    	}
      
      Map<String, Object> variables = new ImmutableMap.Builder()
          .put("buyerNames", buyerNames)
          .put("buyerPrices", buyerPrices)
          .put("buyerLocations", buyerLocations)
          .put("buyerGenders", buyerGenders)
          .put("buyerBounds", buyerBounds)
          .put("buyerDelivers", buyerDelivers)
          .put("buyerEateries", buyerEateries)
          .put("buyerDurations", buyerDurations)
          .put("sellerNames", sellerNames)
          .put("sellerEateries", sellerEateries)
          .put("sellerDurations", sellerDurations)
          .put("sellerDelivers", sellerDelivers)
          .put("sellerLocations", sellerLocations)
          .put("sellerBounds", sellerBounds)
          .put("sellerGenders", sellerGenders)
          .put("creditBounds", creditBounds).build();
      return GSON.toJson(variables);
    }
  }
  
  private class PutDealHandler implements Route {
    @Override
    public Object handle(final Request req, final Response res) {
      QueryParamsMap qm = req.queryMap();
      String offerId = qm.value("offerID");
      String orderId = qm.value("orderID");
      String sellerEmail = "";
      String buyerEmail = "";
      System.out.println("sellerId " + offerId);
      System.out.println("buyerId " + orderId);
      System.out.println("sellerCache " + Global.getOffer());
      System.out.println("buyerCache " + Global.getOrders());
      double price = Double.parseDouble(qm.value("price"));
      boolean success = false;
      for (Offer of : Global.getOffer()) {
      	if (of.getTimeString().equals(offerId)) {
      		for (Order or : Global.getOrders()) {
          	if (or.getTimeString().equals(orderId)) {
          		sellerEmail = of.getUser().getEmail();
          		buyerEmail = or.getUser().getEmail();
          		Deal result = new Deal(of, or, price, new Date().toString());              
              String sellerName = result.getOffer().getUser().getName();
              String buyerName = result.getOrder().getUser().getName();
              String sellerContact = result.getOffer().getUser().getContact();
              String buyerContact = result.getOrder().getUser().getContact();
              double dealPrice = result.getPrice();
              BigDecimal bd = new BigDecimal(dealPrice, new MathContext(3));
              
              System.out.println("in putdeals: " + sellerEmail + " " + buyerEmail);
              
              String foodString = result.getOrder().getFoodString();
            	//send email
              String subject = "[creditMe] Your offer has been matched!";
              String body = "Your order has been matched! Order information:\n\n" + 
              		"Seller Name: " + sellerName + ";\n" +
              		"Seller Phone Number: " + sellerContact + ";\n" + 
              		"Buyer Name: " + buyerName + ";\n" +
              		"Buyer Phone Number: " + buyerContact + ";\n" + 
              		"Deal Price: " + bd + ";\n" + 
              		"Order items: " + foodString + ".\n\n" + 
              		"CreditMe";
              success = Query.putDeal(result, conn);
              success = EmailSender.sendEmail(result.getOffer().getUser().getEmail(), subject, body);
              success = EmailSender.sendEmail(result.getOrder().getUser().getEmail(), subject, body);
              Global.getOffer().remove(of);
              Global.getOrders().remove(or);
            	break;
          	}
          }
      		break;
      	}
      }
      
      Map<String, Object> variables = ImmutableMap.<String, Object>builder()
      		.put("sellerEmail", sellerEmail)
      		.put("buyerEmail", buyerEmail)
          .put("done", success).build();
      return GSON.toJson(variables);
    }
  }
  
  private class GetMenuHandler implements Route {
    @Override
    public Object handle(final Request req, final Response res) {
      QueryParamsMap qm = req.queryMap();
      String eatery = qm.value("eatery");
      List<Object> menu;
      List<Object> price = new ArrayList<>();
      if (eatery.equals("Ratty")) {
      	menu = Global.getRatty();
      	Set<Object> hashMenu = new HashSet<>();
      	hashMenu.addAll(menu);
      	menu.clear();
      	menu.addAll(hashMenu);
      	for (Object f : menu) {
      		price.add(new Integer(0));
      	}
      } else if (eatery.equals("V-Dub")) {
      	menu = Global.getVDub();
      	Set<Object> hashMenu = new HashSet<>();
      	hashMenu.addAll(menu);
      	menu.clear();
      	menu.addAll(hashMenu);
      	for (Object f : menu) {
      		price.add(new Integer(0));
      	}
      } else {
      	List<ArrayList<Object>> food;
				try {
					food = Query.getEateryFood(eatery, new Date(), conn);
					System.out.println("food" + food);
		      menu = food.get(0);
		      price = food.get(1);
				} catch (SQLException e) {
					menu = new ArrayList<>();
				}
      }
      Map<String, Object> variables = ImmutableMap.<String, Object>builder()
          .put("menu", menu)
          .put("price", price).build();
      return GSON.toJson(variables);
    }
  }
  
  private class DeleteDealHandler implements Route {
    @Override
    public Object handle(final Request req, final Response res) {
      QueryParamsMap qm = req.queryMap();
      String type = qm.value("type");
      String time = qm.value("time");
      boolean success = false;      
      System.out.println("ordercache: " + Global.getOrders());
      System.out.println("offercache: " + Global.getOffer());
      System.out.println("time: " + time);
      System.out.println("type: " + type);
      
      if (type.equals("offer")) {
      	List<Offer> offers = Global.getOffer();
      	for (Offer of : offers) {
      		if (of.getTimeString().equals(time)) {
      			offers.remove(of);
      			success = true;
      			break;
      		}
      	}
      } else if (type.equals("order")) {
      	List<Order> orders = Global.getOrders();
      	for (Order od : orders) {
      		if (od.getTimeString().equals(time)) {
      			orders.remove(od);
      			success = true;
      			break;
      		}
      	}
      }
      Map<String, Object> variables = new ImmutableMap.Builder()
          .put("done", success).build();
      return GSON.toJson(variables);
    }
  }

  private class GetDealsHandler implements Route {
    @Override
    public Object handle(final Request req, final Response res) {
      QueryParamsMap qm = req.queryMap();
      String email = qm.value("email");
      
      List<HalfDealObject> offers = new ArrayList<>();
      List<HalfDealObject> orders = new ArrayList<>();
      List<Deal> history = new ArrayList<>();
      try {
				history = Query.getHistory(email, conn);
			} catch (SQLException e) {
				e.printStackTrace();
			}
      List<DealObject> deals = new ArrayList<>();
      for (Deal d : history) {
      	String buyerEmail = d.getOrder().getUser().getEmail();
      	String sellerEmail = d.getOffer().getUser().getEmail();
      	String buyerName = d.getOrder().getUser().getName();
      	String sellerName = d.getOffer().getUser().getName();
      	String buyerContact = d.getOrder().getUser().getContact();
      	String sellerContact = d.getOffer().getUser().getContact();
      	double pc = d.getPrice();
        BigDecimal price = new BigDecimal(pc, new MathContext(3));
      	String eatery = d.getOrder().getEatery().getEateryName();
      	String time = d.getTime();
      	int buyerRating = d.getBuyerRating();
      	int sellerRating = d.getSellerRating();
      	String buyerComment = d.getBuyerComment();
      	String sellerComment = d.getSellerComment();

      	deals.add(new DealObject(buyerEmail, sellerEmail, buyerName, sellerName, buyerContact, sellerContact, price, eatery, time, buyerRating, sellerRating, buyerComment, sellerComment));
      }
      System.out.println(Global.getOffer());
      for (Offer of : Global.getOffer()) {
      	if (of.getUser().getEmail().equals(email)) {
      		List<Eatery> eateries = of.getEateries();
      		System.out.println("offer: " + of);
      		System.out.println("eateries: " + eateries);
      		StringBuilder sb = new StringBuilder();
      		for (Eatery e : eateries) {
      			sb.append(", " + e.getEateryName());
      		}
      		String eatery = sb.toString().substring(2);
      		String time = of.getTimeString();
      		int dur = of.getDuration() / 600000;
      		offers.add(new HalfDealObject(eatery, time, dur));
      	}
      }
      
      for (Order or : Global.getOrders()) {
      	if (or.getUser().getEmail().equals(email)) {
      		String eatery = or.getEatery().getEateryName();
      		String time = or.getTimeString();
      		int dur = or.getDuration() / 600000;
      		orders.add(new HalfDealObject(eatery, time, dur));
      	}
      }
      
      Collections.reverse(deals);
      
      Map<String, Object> variables = new ImmutableMap.Builder()
      		.put("offers", offers)
      		.put("orders", orders)
      		.put("history", deals).build();
      return GSON.toJson(variables);
    }
  }
  
  private class PlaceOfferHandler implements Route {
    @Override
    public Object handle(final Request req, final Response res) {
      QueryParamsMap qm = req.queryMap();
      
      String email = qm.value("user");
      int creditNum = Integer.parseInt(qm.value("creditNum"));
      int duration = Integer.parseInt(qm.value("duration"));
      double priceBound = Double.parseDouble(qm.value("bound"));
      String eateryName = qm.value("eatery");
      String contact = qm.value("contact");
      String userName = qm.value("username");
      String subscribe = qm.value("subscribe");
      boolean north = Boolean.parseBoolean(qm.value("north_deliver"));
      boolean center = Boolean.parseBoolean(qm.value("center_deliver"));
      boolean south = Boolean.parseBoolean(qm.value("south_deliver"));
      Query.updateUser(email, userName, contact, subscribe, conn);
      // set fields
      List<Eatery> eatery = new ArrayList<>();
      List<Location> location = new ArrayList<>();
      User user;
      // initialize fields
      try {
		    // user
				user = Query.getUser(email, conn);
				// eatery
	      String[] eateries = eateryName.split(",");
	      for (String s : eateries) {
	      	eatery.add(Query.getEatery(s, conn));
	      }
	      
				// location
		    if (north) {
		    	location.addAll(Query.getNorthLocation(conn));
		    }
		    if (south){
		    	location.addAll(Query.getSouthLocation(conn));
		    }
		    if (center){
		    	location.addAll(Query.getCenterLocation(conn));
		    }
		    System.out.println(location + " pppppppppppppppppppppp ");
      } catch (SQLException e) {
      	e.printStackTrace();
      	Map<String, Object> variables = new ImmutableMap.Builder()
	          .put("error", e.getMessage()).build();
      	return variables;
      }
      // create offer
      System.out.println("putoffer: " + eatery);
      Offer offer = new Offer(location, eatery, priceBound, duration * 600000, user, creditNum, new Date());
      Global.addOffer(offer);
      // match deals
      List<Deal> deal = Matcher.matchOffer(offer);
      System.out.print(deal);
      System.out.print("size: " + deal.size());
      
      System.out.println("offers size: " + Global.getOffer().size());
      System.out.println("orders size: " + Global.getOrders().size());
      
      if (deal.size() == 0) {
      	Map<String, Object> variables = new ImmutableMap.Builder()
	          .put("result", "nothing").build();
	      return GSON.toJson(variables);
    	} else if (deal.size() <= 3) {
      	// no match
        List<String> buyerNames = new ArrayList<>();
        List<String> buyerContacts = new ArrayList<>();
        List<BigDecimal> prices = new ArrayList<>();
        List<Double> buyerRatings = new ArrayList<>();
        List<String> times = new ArrayList<>();
        String sellerName = deal.get(0).getOffer().getUser().getName();
        String sellerContact = deal.get(0).getOffer().getUser().getContact();
        String offerId = deal.get(0).getOffer().getTimeString();
        for (Deal d : deal) {
          String buyerName = d.getOrder().getUser().getName();
          String buyerContact = d.getOrder().getUser().getContact();
          double buyerRating = d.getOrder().getUser().getRating();
          String time = d.getOrder().getTimeString();
          double price = d.getPrice();
          buyerContacts.add(buyerContact);
          buyerNames.add(buyerName);
          buyerRatings.add(buyerRating);
          times.add(time);
          prices.add(new BigDecimal(price, new MathContext(3)));
        }
        Map<String, Object> variables = ImmutableMap.<String, Object>builder()
            .put("sellerContact", sellerContact)
            .put("sellerName", sellerName)
            .put("prices", prices)
            .put("buyerNames", buyerNames)
            .put("buyerContacts", buyerContacts)
            .put("buyerRatings", buyerRatings)
            .put("orderIds", times)
            .put("offerId", offerId)
            .put("result", "suggestions").build();
        return GSON.toJson(variables);
      } else {
      	// has match
        Deal result = deal.get(0);
        String sellerName = result.getOffer().getUser().getName();
        String buyerName = result.getOrder().getUser().getName();
        String sellerContact = result.getOffer().getUser().getContact();
        String buyerContact = result.getOrder().getUser().getContact();
        String sellerEmail = result.getOffer().getUser().getEmail();
        String buyerEmail = result.getOrder().getUser().getEmail();
        double price = result.getPrice();
        String time = result.getTime();
        double userRating = result.getOrder().getUser().getRating();
        String ety = result.getOrder().getEatery().getEateryName();
        BigDecimal bd = new BigDecimal(price, new MathContext(3));
        String foodString = result.getOrder().getFoodString();
      	//send email
        String subject = "[creditMe] Your offer has been matched!";
        String body = "Your order has been matched! Order information:\n\n" + 
        		"Seller Name: " + sellerName + ";\n" +
        		"Seller Phone Number: " + sellerContact + ";\n" + 
        		"Buyer Name: " + buyerName + ";\n" +
        		"Buyer Phone Number: " + buyerContact + ";\n" + 
        		"Deal Price: " + bd + ";\n" + 
        		"Order items: " + foodString + ".\n\n" + 
        		"CreditMe";
        boolean success0 = Query.putDeal(result, conn);
      	boolean success1 = EmailSender.sendEmail(result.getOrder().getUser().getEmail(), subject, body);
      	boolean success2 = EmailSender.sendEmail(result.getOffer().getUser().getEmail(), subject, body);
        System.out.println(userRating);
        Map<String, Object> variables = ImmutableMap.<String, Object>builder()
            .put("sellerName", sellerName)
            .put("buyerName", buyerName)
            .put("sellerContact", sellerContact)
            .put("buyerContact", buyerContact)
            .put("sellerEmail", sellerEmail)
            .put("buyerEmail", buyerEmail)
            .put("price", price)
            .put("eatery", ety)
            .put("time", time)
            .put("buyerRating", userRating)
            .put("result", "match").build();
        return GSON.toJson(variables);
		  }
		}
  }

  private class PlaceOrderHandler implements Route {
    @Override
    public Object handle(final Request req, final Response res) {
      QueryParamsMap qm = req.queryMap();
      String address = qm.value("address");
      String email = qm.value("user");
      String menu = qm.value("menu");
      String contact = qm.value("contact");
      String userName = qm.value("username");
      String subscribe = qm.value("subscribe");
      int duration = Integer.parseInt(qm.value("duration"));
      double price = Double.parseDouble(qm.value("price"));
      double priceBound = Double.parseDouble(qm.value("priceBound"));
      String eateryName = qm.value("eatery");
      
      Query.updateUser(email, userName, contact, subscribe, conn);
      // set fields
      List<Food> foods = new ArrayList<>();
      Eatery eatery;
      Location location;
      User user;
      // initialize fields
      try {
      	// food
      	System.out.println(menu + "4444444444444444444444444444444444444444");
        String[] foodNames = menu.split("%%");
		    for (String s : foodNames) {
		    	System.out.println(s);
		    	foods.add(Query.getFood(s, conn));
		    }
		    // user
				user = Query.getUser(email, conn);
				// eatery
	      eatery = Query.getEatery(eateryName, conn);
				// location
		    if (address == null) {
		    	location = null;
		    } else {
					location = Query.getLocation(address, conn);
					System.out.println(location + "!!!!!!!!!!!!!!!!!!!!!!!");
		    }
      } catch (SQLException e) {
      	e.printStackTrace();
      	Map<String, Object> variables = new ImmutableMap.Builder()
	          .put("error", e.getMessage()).build();
      	return variables;
      }
      // create order

      
      Order order = new Order(location, eatery, priceBound, price, duration * 600000, user, foods, new Date());
      System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" + foods);
      Global.addOrders(order);
      // match deals
      
      List<Deal> deal = Matcher.matchOrder(order);
      System.out.print(deal);
      System.out.print("size: " + deal.size());
      
      System.out.println("offers size: " + Global.getOffer().size());
      
      if (deal.size() == 0) {
      	Map<String, Object> variables = new ImmutableMap.Builder()
	          .put("result", "nothing").build();
	      return GSON.toJson(variables);
    	} else if (deal.size() <= 3) {
      	// no match
    	  List<String> sellerNames = new ArrayList<>();
    	  List<String> sellerContacts = new ArrayList<>();
    	  List<Double> sellerRatings = new ArrayList<>();
    	  List<BigDecimal> prices = new ArrayList<>();
    	  List<String> times = new ArrayList<>();
        String buyerName = deal.get(0).getOrder().getUser().getName();
        String buyerContact = deal.get(0).getOrder().getUser().getContact();
        String orderId = deal.get(0).getOrder().getTimeString();
        for (Deal d : deal) {
          String sellerName = d.getOffer().getUser().getName();
          String sellerContact = d.getOffer().getUser().getContact();
          double dealPrice = d.getPrice();
          double rating = d.getOffer().getUser().getRating();
          String time = d.getOffer().getTimeString();
          sellerContacts.add(sellerContact);
          sellerNames.add(sellerName);
          prices.add(new BigDecimal(dealPrice, new MathContext(3)));
          sellerRatings.add(rating);
          times.add(time);
        }
      	Map<String, Object> variables = ImmutableMap.<String, Object>builder()
	          .put("sellerContacts", sellerContacts)
	          .put("sellerNames", sellerNames)
	          .put("sellerRatings", sellerRatings)
            .put("prices", prices)
            .put("offerIds", times)
            .put("orderId", orderId)
	          .put("buyerName", buyerName)
	          .put("buyerContact", buyerContact)
            .put("result", "suggestions").build();
	      return GSON.toJson(variables);
      } else {
      	// has match
      	Deal result = deal.get(0);
        String sellerName = result.getOffer().getUser().getName();
        String buyerName = result.getOrder().getUser().getName();
        String sellerContact = result.getOffer().getUser().getContact();
        String buyerContact = result.getOrder().getUser().getContact();
        String sellerEmail = result.getOffer().getUser().getEmail();
        String buyerEmail = result.getOrder().getUser().getEmail();
        double dealPrice = result.getPrice();
        String time = result.getTime();
        String ety = eatery.getEateryName();
        double userRating = result.getOffer().getUser().getRating();
        BigDecimal bd = new BigDecimal(dealPrice, new MathContext(3));
        String foodString = result.getOrder().getFoodString();
      	//send email
        String subject = "[creditMe] Your offer has been matched!";
        String body = "Your order has been matched! Order information:\n\n" + 
        		"Seller Name: " + sellerName + ";\n" +
        		"Seller Phone Number: " + sellerContact + ";\n" + 
        		"Buyer Name: " + buyerName + ";\n" +
        		"Buyer Phone Number: " + buyerContact + ";\n" + 
        		"Deal Price: " + bd + ";\n" + 
        		"Order items: " + foodString + ".\n\n" + 
        		"CreditMe";
        boolean success0 = Query.putDeal(result, conn);
      	boolean success1 = EmailSender.sendEmail(result.getOffer().getUser().getEmail(), subject, body);
        boolean success2 = EmailSender.sendEmail(result.getOrder().getUser().getEmail(), subject, body);
        System.out.println(userRating);
        Map<String, Object> variables = ImmutableMap.<String, Object>builder()
            .put("sellerName", sellerName)
            .put("buyerName", buyerName)
            .put("sellerContact", sellerContact)
            .put("buyerContact", buyerContact)
            .put("sellerEmail", sellerEmail)
            .put("buyerEmail", buyerEmail)
            .put("price", dealPrice)
            .put("eatery", ety)
            .put("time", time)
            .put("sellerRating", userRating)
            .put("result", "match")
            .build();
        return GSON.toJson(variables);
      }
    }
  }
}
