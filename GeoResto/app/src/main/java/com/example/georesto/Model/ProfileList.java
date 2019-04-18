package com.example.georesto.Model;


import java.util.ArrayList;
import java.util.Arrays;

public class ProfileList {
    private static ArrayList<Profile> profiles = new ArrayList<>();
    private static Profile currentUser = null;

    public ProfileList() {
        instantiateProfiles();
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

    private void instantiateProfiles() {
        Profile admin = new Profile("admin", "", "administrator", "administrator", "administrator@gmail.com", null);
        Profile noName = new Profile("", "", "noName", "noLastName", "noNameIsNice@gmail.com", null);
        Profile camille = new Profile("CamBou", "azerty", "Camille", "Bourgeois", "camille.bourgeois@hotmail.com", null);
        Profile elvis = new Profile("Elvis", "azerty", "Elvis", "Pressé", "elvisLeBG@orange.fr", "@drawable/elvis");
        Profile richard = new Profile("GrisonLover", "azerty", "Richard", "Cuterie", "richouCute@gmail.com", null);

        profiles.addAll(Arrays.asList(
                admin,
                noName,
                camille,
                elvis,
                richard
        ));
    }
}
