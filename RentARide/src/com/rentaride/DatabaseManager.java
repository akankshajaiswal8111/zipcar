package com.rentaride;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
	protected static Connection initializeDatabase() 
	        throws SQLException, ClassNotFoundException 
	    { 
	    	 String dbDriver = "com.mysql.jdbc.Driver"; 
	    	   Class.forName(dbDriver); 
	    	
	         // Database name to access 
	    	 String hostname=<<AWS RDS URL>>;
	    	 String port="3306";
	         String dbName = "rentaride"; 
	         String dbUsername = <<DB Username>>; 
	         String dbPassword = <<DB Password>>; 
	   
	         String jdbcUrl = "jdbc:mysql://" + hostname + ":" + port + "/" + dbName + "?user=" + dbUsername + "&password=" + dbPassword;
	         Connection con = DriverManager.getConnection(jdbcUrl); 
	         System.out.print("1.."+con);
	         return con; 
	    } 
}
