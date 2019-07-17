package com.karthick.profileService;

import java.util.Collection;
import java.util.HashMap;

import com.karthick.profileDAO.Profile;
import com.karthick.profileDataBase.ProfileDBInfo;

public class ProfileServiceImpl {
	
	public static HashMap<Long, Profile> profileInfo = ProfileDBInfo.getProfileInfo();
	
	public ProfileServiceImpl() {
		profileInfo.put(123L, new Profile(123, "karthick_raj", "Karthick", "Rajendran"));
		profileInfo.put(345L, new Profile(345, "dhana_raj", "Dhanasekaran", "Rajendran"));
	}
	
	public Collection<Profile> getProfileInfo() {
		return profileInfo.values();
	}
	
	public Profile getProfileInfoById(Long profileId) {
		return profileInfo.get(profileId);
	}
	
	public Profile addProfileInfo(Profile profile) {
		profile.setProfileId(profileInfo.size()+1);
		profileInfo.put(profile.getProfileId(), profile);
		return profile;
	}
	
	public Profile addProfileInfo1(Profile profile) {
		profile.setProfileId(profileInfo.size()+1);
//		profileInfo.put(profile.getProfileId(), profile);
		boolean profileCreated = ProfileDBInfo.createRecord(profile);
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
