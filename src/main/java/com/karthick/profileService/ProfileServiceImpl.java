package com.karthick.profileService;

import java.util.Collection;
import java.util.HashMap;

import com.karthick.profileDAO.Profile;
import com.karthick.profileDataBase.ProfileDBInfo;

public class ProfileServiceImpl {
	
	public static HashMap<Long, Profile> profileInfo = ProfileDBInfo.getProfileInfo();
	public static ProfileDBInfo dbInfo = new ProfileDBInfo();
	
	public Collection<Profile> getProfileInfo() {
		return dbInfo.retrieveRecord();
	}
	
	public Profile getProfileInfoById(Long profileId) {
		return dbInfo.retrieveRecordById(profileId);
	}
	
	public Profile addProfileInfo(Profile profile) {
		boolean profileCreated = dbInfo.createRecord(profile);
		if (profileCreated) {
			return profile;	
		}
			return null;
	}
	
	public Profile updateProfileInfo(Long profileId, Profile profile) {

		if(profileInfo.containsKey(profileId)){
			profileInfo.put(profile.getProfileId(), profile);
			return profile;
		} 
		
		return null;
	}
	
	public void removeProfileInfo(Long profileId) {
		profileInfo.remove(profileId);
	}

}
