package com.karthick.profileDataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.HashMap;

import com.karthick.profileDAO.Profile;

public class ProfileDBInfo {
	
	private static HashMap<Long, Profile> profile = new HashMap<Long, Profile>();
	
	public static HashMap<Long,Profile> getProfileInfo() {
		return profile;
	}

	public static boolean createRecord(Profile profile) {
		
		boolean recordCreated = false;
		// create a mysql database connection
	      String myDriver = "com.mysql.jdbc.Driver";
	      String myUrl = "jdbc:mysql://localhost:3306/profileInfo";
	      try {
	    	  Class.forName(myDriver);
	    	  Connection conn = DriverManager.getConnection(myUrl, "root", "root");
	    	  System.out.println("Database connected!");
		      // create a sql date object so we can use it in our INSERT statement
		      Calendar calendar = Calendar.getInstance();
		      java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());

		      // the mysql insert statement
		      String query = " insert into profileinfo (profileId, profileName, firstName, lastName, createdDate)"
		        + " values (?, ?, ?, ?, ?)";

		      // create the mysql insert preparedstatement
		      PreparedStatement preparedStmt = conn.prepareStatement(query);
		      preparedStmt.setLong(1, profile.getProfileId());
		      preparedStmt.setString(2, profile.getProfileName());
		      preparedStmt.setString(3, profile.getFirstName());
		      preparedStmt.setString(4, profile.getLastName());
		      preparedStmt.setDate(5, startDate);

		      // execute the preparedstatement
		      preparedStmt.execute();
		      
		      conn.close();
		      recordCreated = true;
	      } catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
	    	  e.printStackTrace();
	      } catch (SQLException e) {
				// TODO Auto-generated catch block
	    	  e.printStackTrace();
	      }
	      
	      return recordCreated;
	}
}
