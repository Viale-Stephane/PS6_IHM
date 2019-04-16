package com.example.georesto.Model;


import java.util.ArrayList;

public class ProfileList {
    static ArrayList<Profile> profiles = new ArrayList<>();
    private static Profile currentUser = null;

    public ProfileList() {

        Profile admin = new Profile("admin", "", "administrator", "administrator", "administrator@gmail.com", null);
        Profile noName = new Profile("", "", "noName", "noLastName", "noNameIsNice@gmail.com", null);
        profiles.add(admin);
        profiles.add(noName);
    }

    public static ArrayList<Profile> getProfiles() {
        return profiles;
    }

    public static Profile getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(Profile user) {
        currentUser = user;
    }
}
