package com.example.georesto.model;


public class LoginModel {

    public Profile connect(String login, String password) {
        for (Profile profile : ProfileList.getProfiles()) {
            if ((profile.getUsername().equals(login) || profile.getEmail().equals(login)) && profile.getPassword().equals(password)) {
                ProfileList.setCurrentUser(profile);
                return profile;
            }
        }
        return null;
    }
}
