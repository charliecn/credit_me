package edu.brown.cs.credit_me;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import database.Query;
import global.Global;
import gui.Gui;

public class Main {
  public static void main(String[] args) throws FileNotFoundException, SQLException {
  	//Global.setDb("creditMe.sqlite3");
  	//Query.putFoods();
  	//System.out.println("done");
  	new Main().run();
  }
  
  private Main() {
  }

  private void run() {
  	new Gui("creditMe.sqlite3").run();
  }
}
