package edu.brown.cs.sren1.credit_me;

import emailer.EmailSender;
import global.Global;
import gui.Gui;

public class Main {
  public static void main(String[] args) {
    new Main().run();
  }
  
  private Main() {
  }

  private void run() {
  	new Gui("creditMe.sqlite3").run();
  }
}
