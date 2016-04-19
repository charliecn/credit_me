package gui;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;

import database.Query;
import deal.Deal;
import deal.Order;
import freemarker.template.Configuration;
import global.Global;
import global.Matcher;
import locationfood.Food;
import locationfood.Location;
import spark.QueryParamsMap;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;
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
    Spark.post("/userlogin", new UserLoginHandler());
    Spark.post("/usersignup", new UserSignUpHandler());
    Spark.post("/userforgetpwd", new UserForgetPwdHandler());
    Spark.post("/userchangepwd", new UserChangePwdHandler());
    Spark.post("/placeorder", new PlaceOrderHandler());
  }

  private class UserLoginHandler implements Route {
    @Override
    public Object handle(final Request req, final Response res) {
      QueryParamsMap qm = req.queryMap();
      String email = qm.value("email");
      String password = qm.value("password");
      //get user
      User user = Global.getDb().getUser(email, Global.md5(password));
      
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
      QueryParamsMap qm = req.queryMap();
      String name = qm.value("username");
      String pwd = qm.value("pwd");
      String email = qm.value("email");
      String subscribe = qm.value("subscribe");
      boolean subs;
      if (subscribe.equals("true")) {
      	subs = true;
      } else {
      	subs = false;
      }
    	User user = new BrownUser(name, email, Global.md5(pwd), subs);
    	//put user
    	Global.getDb().putUser(user);
      Map<String, Object> variables = new ImmutableMap.Builder().build();
      return GSON.toJson(variables);
    }
  }
  
  private class UserForgetPwdHandler implements Route {
    @Override
    public Object handle(final Request req, final Response res) {
      QueryParamsMap qm = req.queryMap();
      String email = qm.value("email");
      //
      Map<String, Object> variables = new ImmutableMap.Builder()
          .put("done", "done").build();
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
      User user = Global.getDb().getUser(email, Global.md5(prevPwd));
      if (user == null) {
	      Map<String, Object> variables = new ImmutableMap.Builder()
	          .put("error", "incorrect password!").build();
	      return GSON.toJson(variables);
      } else {
      	Global.getDb().changePassword(email, prevPwd, newPwd);
	      Map<String, Object> variables = new ImmutableMap.Builder()
	          .put("done", "done").build();
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
      
      String[] foodIds = menu.split("//s");
      List<Food> foods = new ArrayList<>();
      for (String s : foodIds) {
      	foods.add(Global.getDb().getFood(s));
      }
      
      User user = Global.getDb().getUser(email);
      Location eatery = Global.getDb().getLocation(eateryName);
      Location location;
      if (address == null) {
      	 location = null;
      } else {
      	location = Global.getDb().getLocation(location);
      }
      Order order = new Order(location, eatery, priceBound, price, duration * 600000, foods, user);
      Deal deal = matcher.matchOrder(order);
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
  
  
}
