package com.karthick.profileResources;

import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.karthick.profileDAO.Profile;
import com.karthick.profileService.ProfileServiceImpl;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("/profiles")
public class ProfileResource {

	ProfileServiceImpl profileService = new ProfileServiceImpl();
    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Path("/getProfiles")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Profile> getProfileInfo() {
        return profileService.getProfileInfo();
    }
    
    @GET
    @Path("/getProfiles/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Profile getProfileInfoById(@PathParam("id") Long profileId) {
        return profileService.getProfileInfoById(profileId);
    }
    
    @POST
    @Path("/addProfile")
    public Profile addProfileInfo(Profile profile) {
        Profile profileCreated = profileService.addProfileInfo(profile);
    	if(profileCreated != null) {
        	return profileCreated;
        }
    		return null;
    }
    
    @PUT
    @Path("/updateProfile/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Profile updateProfileInformation(@PathParam("id") Long profileId, Profile profile) {
    	profile.setProfileId(profileId);
        return profileService.updateProfileInfo1(profileId, profile);
    }

    @DELETE
    @Path("/deleteProfile/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteProfileInformation(@PathParam("id") Long profileId) {
        profileService.removeProfileInfo(profileId);
    }
}
