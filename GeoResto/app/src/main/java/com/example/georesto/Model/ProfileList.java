package com.example.georesto.Model;


import java.util.ArrayList;
import java.util.Arrays;

public class ProfileList {
    private static ArrayList<Profile> profiles = new ArrayList<>();
    private static Profile currentUser = null;

    public ProfileList() {
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

    public static String addProfile(Profile profile, String passwordConfirmation) {
        if (!profile.getPassword().equals(passwordConfirmation)) {
            return "Mots de passe différents";
        } else if (profile.getEmail().equals("") || profile.getFirstName().equals("") || profile.getLastName().equals("") || profile.getPassword().equals("") || profile.getUsername().equals("")) {
            return "Veuillez remplir toutes les informations";
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

    public void instantiateProfiles(RestaurantList restaurantList) {
        RestaurantList history = restaurantList;
        RestaurantList favourite = restaurantList;
        System.out.println("bonjour"+history.getRestaurants().size());

        Profile admin = new Profile("admin", "", "administrator", "administrator", "administrator@gmail.com", "@drawable/default_profile", history, favourite);
        Profile noName = new Profile("", "", "noName", "noLastName", "noNameIsNice@gmail.com", "@drawable/default_profile", history, favourite);
        Profile camille = new Profile("CamBou", "azerty", "Camille", "Bourgeois", "camille.bourgeois@hotmail.com", "@drawable/camille", history, favourite);
        Profile elvis = new Profile("Elvis", "azerty", "Elvis", "Pressé", "elvisLeBG@orange.fr", "@drawable/elvis", history, favourite);
        Profile richard = new Profile("GrisonLover", "azerty", "Richard", "Cuterie", "richouCute@gmail.com", "@drawable/richard", history, favourite);

        richard.getUserComments().addComment(new Comment("Tres bon service avec de tres bon burger", richard, restaurantList.getRestaurant(0)));
        noName.getUserComments().addComment(new Comment("Un fastfood classique, sans plus", noName, restaurantList.getRestaurant(0)));

        camille.getUserComments().addComment(new Comment("Accueil chaleureux et on est bien nourri !", camille, restaurantList.getRestaurant(1)));
        elvis.getUserComments().addComment(new Comment("Un restaurant miteux", elvis, restaurantList.getRestaurant(1)));

        admin.getUserComments().addComment(new Comment("Un kebab moyen mais pas cher", admin, restaurantList.getRestaurant(2)));
        richard.getUserComments().addComment(new Comment("Un tres bon kebab, je recommande !", richard, restaurantList.getRestaurant(2)));

        camille.getUserComments().addComment(new Comment("La qualité est a la hauteur de ces prix...", camille, restaurantList.getRestaurant(3)));
        elvis.getUserComments().addComment(new Comment("Pas cher et pas bon, génial !", elvis, restaurantList.getRestaurant(3)));
        admin.getUserComments().addComment(new Comment("Un endroit ou manger quand on est etudiant", admin, restaurantList.getRestaurant(3)));

        profiles.addAll(Arrays.asList(
                admin,
                noName,
                camille,
                elvis,
                richard
        ));
    }
}
