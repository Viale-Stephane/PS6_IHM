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
        Profile admin = new Profile("admin", "", "administrator", "administrator", "administrator@gmail.com", "@drawable/default_profile");
        Profile noName = new Profile("", "", "noName", "noLastName", "noNameIsNice@gmail.com", "@drawable/default_profile");
        Profile camille = new Profile("CamBou", "azerty", "Camille", "Bourgeois", "camille.bourgeois@hotmail.com", "@drawable/camille");
        Profile elvis = new Profile("Elvis", "azerty", "Elvis", "Pressé", "elvisLeBG@orange.fr", "@drawable/elvis");
        Profile richard = new Profile("GrisonLover", "azerty", "Richard", "Cuterie", "richouCute@gmail.com", "@drawable/richard");

        profiles.addAll(Arrays.asList(
                admin,
                noName,
                camille,
                elvis,
                richard
        ));
    }

    public static String addProfile(Profile profile, String passwordConfirmation) {
        if (!profile.getPassword().equals(passwordConfirmation)) {
            return "Mots de passe différents";
        } else {
            for (Profile existingProfile : profiles) {
                if (profile.getUsername().equals(existingProfile.getUsername())) {
                    return "Nom d'utilisateur déjà existant";
                } else if (profile.getEmail().equals(existingProfile.getEmail())) {
                    return "Email déjà existant";
                }
            }
        }
        profiles.add(profile);
        currentUser = profile;
        return "Connexion en cours..";
    }
}
