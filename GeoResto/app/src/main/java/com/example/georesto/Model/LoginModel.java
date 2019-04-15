package com.example.georesto.Model;



import com.example.georesto.Profile;
import com.example.georesto.ProfileList;

public class LoginModel {

    public Profile connect(String login, String password){
        for(Profile profile : ProfileList.getProfiles()){
            if((profile.getUsername().equals(login) || profile.getEmail().equals(login)) && profile.getPassword().equals(password)){
                ProfileList.setCurrentUser(profile);
                return profile;
            }
        }
        return null;
    }
}
