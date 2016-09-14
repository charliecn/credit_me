package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
	

	private static final String urlPart = "jdbc:sqlite:";
	private static String url;
	
	/**
	 * sets the path of the url for the database to connect to
	 * @param path - path to the sql file
	 */
	public static void setUrl(String path){

		url = urlPart + path;
	}
	
	static{
		
		try{
			Class.forName("org.sqlite.JDBC");
		} catch(ClassNotFoundException cnfe) {
			throw new RuntimeException(cnfe);
		}
		
	}
	
	/**
	 * gets a connection to the database
	 * @return
	 */
	public static Connection getConnection(){

		try{
			Connection conn = DriverManager.getConnection(url);
			Statement stmt = conn.createStatement();
			stmt.executeUpdate("PRAGMA foreign_keys = ON;");
			return conn;
		} catch (SQLException e){
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * an interface to connect with the database
	 * @author lackley
	 *
	 * @param <T> type of operation 
	 */
	public interface Operation<T> {
		T executeWith(Connection c) throws SQLException;
	}
}
