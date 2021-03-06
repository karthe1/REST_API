package com.karthick.profileDataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;

import com.karthick.profileDAO.Profile;

public class ProfileDBInfo {
	
	private static HashMap<Long, Profile> profile = new HashMap<Long, Profile>();
	private static long count = 0 ; 
	
	enum ProfileUpdate { 
	    PROFILENAME, FIRSTNAME, LASTNAME; 
	}
	
	public ProfileDBInfo() {
		try {
			Statement statement;
			statement = DBConnection().createStatement();
	   	  	ResultSet resultSet = statement.executeQuery("select count(*) as total from profileinfo");
		    while(resultSet.next()){
		    	count = resultSet.getInt("total");
		    }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static Connection DBConnection() {
		
		// Create a MYSQL DB connection
	      String myDriver = "com.mysql.jdbc.Driver";
	      String myUrl = "jdbc:mysql://localhost:3306/profileInfo";
	      try {
				Class.forName(myDriver);
				Connection conn = DriverManager.getConnection(myUrl, "root", "root");
				return conn;
	      } catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
	      } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      		return null;
	}
	
	public static HashMap<Long,Profile> getProfileInfo() {
		return profile;
	}

	public boolean createRecord(Profile profile) {
		
		boolean recordCreated = false;
		 
	      try {
	    	  profile.setProfileId(count+1);
		      // Create a sql DATE object so that we can use it in our INSERT STATEMENT
		      Calendar calendar = Calendar.getInstance();
		      java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());
	
		      // MYSQL INSERT QUERY STATEMENT
		      String query = " insert into profileinfo (profileId, profileName, firstName, lastName, createdDate)"
		        + " values (?, ?, ?, ?, ?)";
	
		      PreparedStatement preparedStmt = DBConnection().prepareStatement(query);
		      preparedStmt.setLong(1, profile.getProfileId());
		      preparedStmt.setString(2, profile.getProfileName());
		      preparedStmt.setString(3, profile.getFirstName());
		      preparedStmt.setString(4, profile.getLastName());
		      preparedStmt.setDate(5, startDate);
	
		      // execute the prepared statement
		      preparedStmt.execute();
		      
		      DBConnection().close();
		      recordCreated = true;
		      count++;
	      } catch (SQLException e) {
				// TODO Auto-generated catch block
	    	  e.printStackTrace();
	      }
	      
	      return recordCreated;
	}
	
	public Collection<Profile> retrieveRecord() {
		
		try {
		      // MYSQL INSERT QUERY STATEMENT
	    	  Statement statement;
				statement = DBConnection().createStatement();
		   	  	ResultSet resultSet = statement.executeQuery("select * from profileinfo");
		   	  	while(resultSet.next()){
			    	profile.put(Long.parseLong(resultSet.getString("profileId")), 
			    				new Profile(Long.parseLong(resultSet.getString("profileId")), resultSet.getString("profileName"), resultSet.getString("firstName"), resultSet.getString("lastName"), resultSet.getDate("createdDate")));
			    }
		      DBConnection().close();
		      
	      } catch (SQLException e) {
				// TODO Auto-generated catch block
	    	  e.printStackTrace();
	      }
	      return profile.values();
	}
	
	public Profile retrieveRecordById(Long profileId) {
		
		try {
		      // MYSQL SELECT QUERY STATEMENT
	    	  Statement statement;
	    	  	statement = DBConnection().createStatement();
  				ResultSet resultSet = statement.executeQuery("select * from profileinfo where profileId='" + profileId + "'");
	   	  		while(resultSet.next()){
	   	  			profile.put(Long.parseLong(resultSet.getString("profileId")), 
		    				new Profile(Long.parseLong(resultSet.getString("profileId")), resultSet.getString("profileName"), resultSet.getString("firstName"), resultSet.getString("lastName"), resultSet.getDate("createdDate")));
		    }
		      DBConnection().close();
		      
	      } catch (SQLException e) {
				// TODO Auto-generated catch block
	    	  e.printStackTrace();
	      }
	      return profile.get(profileId);
	}
	
	public void deleteRecordById(Long profileId) {
		
		try {
		      // MYSQL DELETE QUERY STATEMENT
	    	  Statement statement;
	    	  statement = DBConnection().createStatement();
	    	  statement.executeUpdate("delete from profileinfo where profileId='" + profileId + "'");
	    	  DBConnection().close();
		      
	      } catch (SQLException e) {
				// TODO Auto-generated catch block
	    	  e.printStackTrace();
	      }
	}
	
	public Profile updateRecordById(Long profileId, Profile updateParameter) {
		
		try {
			
			Statement statement;
			statement = DBConnection().createStatement();
			ProfileUpdate updateParams = null;
			ResultSet resultSet;
			
			if(updateParameter.toString().contains("profileName")) {
				updateParams = ProfileUpdate.PROFILENAME;
			} else if(updateParameter.toString().contains("firstName")) {
				updateParams = ProfileUpdate.FIRSTNAME;
			} else if(updateParameter.toString().contains("lastName")) {
				updateParams = ProfileUpdate.LASTNAME;
			}
				
			switch (updateParams) {
				case PROFILENAME:
					resultSet = statement.executeQuery("update profileinfo set profileName = '"+ updateParameter +"' where profileId='" + profileId + "'");
					while(resultSet.next()){
		   	  			profile.put(Long.parseLong(resultSet.getString("profileId")), 
			    				new Profile(Long.parseLong(resultSet.getString("profileId")), resultSet.getString("profileName"), resultSet.getString("firstName"), resultSet.getString("lastName"), resultSet.getDate("createdDate")));
	  				}
					break;
				case FIRSTNAME:
					resultSet = statement.executeQuery("update profileinfo set firstName = '"+ updateParameter +"' where profileId='" + profileId + "'");
					while(resultSet.next()){
		   	  			profile.put(Long.parseLong(resultSet.getString("profileId")), 
			    				new Profile(Long.parseLong(resultSet.getString("profileId")), resultSet.getString("profileName"), resultSet.getString("firstName"), resultSet.getString("lastName"), resultSet.getDate("createdDate")));
	  				}
					break;
				case LASTNAME:
					resultSet = statement.executeQuery("update profileinfo set lastName = '"+ updateParameter +"' where profileId='" + profileId + "'");
					while(resultSet.next()){
		   	  			profile.put(Long.parseLong(resultSet.getString("profileId")), 
			    				new Profile(Long.parseLong(resultSet.getString("profileId")), resultSet.getString("profileName"), resultSet.getString("firstName"), resultSet.getString("lastName"), resultSet.getDate("createdDate")));
	  				}
					break;
				default:
							
					break;
			}
	   	  		
		      DBConnection().close();
		      
	      } catch (SQLException e) {
				// TODO Auto-generated catch block
	    	  e.printStackTrace();
	      }
	      return profile.get(profileId);
	}
	
}
