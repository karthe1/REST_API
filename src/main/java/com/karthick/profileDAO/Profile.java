package com.karthick.profileDAO;

import java.util.Date;

public class Profile {

	private long profileId;
	private String profileName;
	private String firstName;
	private String lastName;
	private Date profileCreatedDate = new Date();
	
	public Profile() {
		// TODO Auto-generated constructor stub
		
	}

	public Profile(long profileId, String profileName, String firstName, String lastName) {
		this.profileId = profileId;
		this.profileName = profileName;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public long getProfileId() {
		return profileId;
	}
	public void setProfileId(long profileId) {
		this.profileId = profileId;
	}
	public String getProfileName() {
		return profileName;
	}
	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}
	public Date getProfileCreatedDate() {
		return profileCreatedDate;
	}
	public void setProfileCreatedDate(Date profileCreatedDate) {
		this.profileCreatedDate = profileCreatedDate;
	} 
}
