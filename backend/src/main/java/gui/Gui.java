package gui;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;

import database.Query;
import deal.Deal;
import deal.Offer;
import deal.Order;
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
import user.BrownUser;
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

	/**
	 * constructor.
	 */
  public Gui(String db) {
  	matcher = new Matcher();
  	Global.setDb(db);
  }

  /**
   * Starts the spark server. Initializes different handlers to manage
   * REST and generate web page templates with autocorrect.
   */
  public void run() {
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
    Spark.post("/usersignup", new UserSignUpHandler());
    Spark.post("/signupsuccess", new SignUpSuccessHandler());
    Spark.post("/userforgetpwd", new UserForgetPwdHandler());
    Spark.post("/changepasswordsuccess", new ChangePasswordSuccessHandler());
    Spark.post("/userchangepwd", new UserChangePwdHandler());
    Spark.post("/changeinfo", new ChangeInfoHandler());
    Spark.post("/placeorder", new PlaceOrderHandler());
    Spark.post("/placeoffer", new PlaceOfferHandler());
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
  
  private class UserLoginHandler implements Route {
    @Override
    public Object handle(final Request req, final Response res) {
      QueryParamsMap qm = req.queryMap();
      String email = qm.value("email");
      String password = qm.value("password");

      //get user
     
      User user = null;

			try {
				user = Query.getUser(email, Global.md5(password), Global.getDb().getConnection());
			} catch (SQLException e) {
				e.printStackTrace();
			}

      if (user == null) {
	      Map<String, Object> variables = new ImmutableMap.Builder()
	          .put("error", "incorrect email address or password!").build();
	      return GSON.toJson(variables);
      } else {
	      Map<String, Object> variables = new ImmutableMap.Builder()
	          .put("user", user).build();
	      return GSON.toJson(variables);
      }
    }
  }
  
  private class UserSignUpHandler implements Route {
    @Override
    public Object handle(final Request req, final Response res) {
      System.out.println("intohandler");
      QueryParamsMap qm = req.queryMap();
      String name = qm.value("username");
      String pwd = qm.value("pwd");
      String email = qm.value("email");
      String subscribe = qm.value("subscribe");
      boolean subs;
      //System.out.println("");
      if (subscribe.equals("true")) {
      	subs = true;
      } else {
      	subs = false;
      }
    	User user = new BrownUser(name, email, Global.md5(pwd), subs);
      System.out.println("pwd: " + user.getPassword());
    	//send email
    	Query.putUser(user, Global.getDb().getConnection());
      Map<String, Object> variables = new ImmutableMap.Builder()
      		.put("success", true).build();
      return GSON.toJson(variables);
    }
  }
  
  private class SignUpSuccessHandler implements Route {
    @Override
    public Object handle(final Request req, final Response res) {
      QueryParamsMap qm = req.queryMap();
      
      Map<String, Object> variables = new ImmutableMap.Builder()
          .put("done", true).build();
      return GSON.toJson(variables);
    }
  }
  
  private class UserForgetPwdHandler implements Route {
    @Override
    public Object handle(final Request req, final Response res) {
      QueryParamsMap qm = req.queryMap();
      String email = qm.value("email");
      // send email
      Map<String, Object> variables = new ImmutableMap.Builder()
          .put("success", true).build();
      return GSON.toJson(variables);
    }
  }
  
  private class ChangePasswordSuccessHandler implements Route {
    @Override
    public Object handle(final Request req, final Response res) {
      QueryParamsMap qm = req.queryMap();
      
      Map<String, Object> variables = new ImmutableMap.Builder()
          .put("done", true).build();
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
      User user;
			try {
				user = Query.getUser(email, Global.md5(prevPwd), Global.getDb().getConnection());
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
      if (user == null) {
	      Map<String, Object> variables = new ImmutableMap.Builder()
	          .put("error", "incorrect password!").build();
	      return GSON.toJson(variables);
      } else {
      	Query.changePassword(email, prevPwd, newPwd, Global.getDb().getConnection());
	      Map<String, Object> variables = new ImmutableMap.Builder()
	          .put("done", "done").build();
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
      boolean subs;
      if (subscribe.equals("true")) {
      	subs = true;
      } else {
      	subs = false;
      }
    	User user;
			try {
				user = Query.getUser(email, Global.getDb().getConnection());
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
    	user.setContact(contact);
    	user.setSubsribe(subs);
    	user.setName(name);
    	Query.putUser(user, Global.getDb().getConnection());
      Map<String, Object> variables = new ImmutableMap.Builder().build();
      return GSON.toJson(variables);
    }
  }

  private class PlaceOfferHandler implements Route {
    @Override
    public Object handle(final Request req, final Response res) {
      QueryParamsMap qm = req.queryMap();
      String address = qm.value("address");
      String email = qm.value("user");
      int creditNum = Integer.parseInt(qm.value("creditNum"));
      int duration = Integer.parseInt(qm.value("duration"));
      double priceBound = Double.parseDouble(qm.value("priceBound"));
      String eateryName = qm.value("eatery");
      // set fields
      Eatery eatery;
      Location location;
      User user;
      // initialize fields
      try {
		    // user
				user = Query.getUser(email, Global.getDb().getConnection());
				// eatery
	      eatery = Query.getEatery(eateryName, Global.getDb().getConnection());
				// location
		    if (address == null) {
		    	 location = null;
		    } else {
					location = Query.getLocation(address, Global.getDb().getConnection());
		    }
      } catch (SQLException e) {
      	e.printStackTrace();
      	return null;
      }
      
      Offer offer = new Offer(location, eatery, priceBound, duration * 600000, user, creditNum);
      List<Deal> deal = matcher.matchOffer(offer);
      if (deal == null) {
      	Map<String, Object> variables = new ImmutableMap.Builder()
	          .put("deal", deal).build();
	      return GSON.toJson(variables);
      } else {
	      Map<String, Object> variables = new ImmutableMap.Builder()
	          .put("deal", deal).build();
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
      int duration = Integer.parseInt(qm.value("duration"));
      double price = Double.parseDouble(qm.value("price"));
      double priceBound = Double.parseDouble(qm.value("priceBound"));
      String eateryName = qm.value("eatery");
      // set fields
      List<Food> foods = new ArrayList<>();
      Eatery eatery;
      Location location;
      User user;
      // initialize fields
      try {
      	// food
        String[] foodIds = menu.split("//s");
		    for (String s : foodIds) {
						foods.add(Query.getFood(s, Global.getDb().getConnection()));
		    }
		    // user
				user = Query.getUser(email, Global.getDb().getConnection());
				// eatery
	      eatery = Query.getEatery(eateryName, Global.getDb().getConnection());
				// location
		    if (address == null) {
		    	 location = null;
		    } else {
					location = Query.getLocation(address, Global.getDb().getConnection());
		    }
      } catch (SQLException e) {
      	e.printStackTrace();
      	return null;
      }
      // create order
      Order order = new Order(location, eatery, priceBound, price, duration * 600000, user, foods);
      // match deals
      List<Deal> deal = matcher.matchOrder(order);
      if (deal.size() == 0) {
      	Map<String, Object> variables = new ImmutableMap.Builder()
	          .put("result", "nothing").build();
	      return GSON.toJson(variables);
    	} else if (deal.size() <= 3) {
      	// no match
      	Map<String, Object> variables = new ImmutableMap.Builder()
	          .put("deal", deal)
	          .put("result", "suggestions").build();
	      return GSON.toJson(variables);
      } else {
      	// has match
	      Map<String, Object> variables = new ImmutableMap.Builder()
	          .put("deal", deal.get(0))
	          .put("result", "match").build();
	      return GSON.toJson(variables);
      }
    }
  }
  
  
}
