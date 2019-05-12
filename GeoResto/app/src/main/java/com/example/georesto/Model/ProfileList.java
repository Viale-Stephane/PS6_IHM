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

    public static Profile findUserByUsername(String username) {
        for(Profile profile : profiles) {
            if(profile.getUsername().equals(username) || profile.getEmail().equals(username)) {
                return profile;
            }
        }
        return null;
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
        if(profiles.size() != 5) {
            RestaurantList history1 = new RestaurantList();
            RestaurantList history2 = new RestaurantList();
            RestaurantList history3 = new RestaurantList();
            RestaurantList history4 = new RestaurantList();

            RestaurantList favourite = new RestaurantList();
            RestaurantList favourite1 = new RestaurantList();
            RestaurantList favourite2 = new RestaurantList();
            RestaurantList favourite3 = new RestaurantList();

            for (int i = 0; i < restaurantList.size(); i++) {

                if ((2 * i) < restaurantList.size())
                    history1.addRestaurant(restaurantList.getRestaurant(2 * i));
                if ((3 * i) < restaurantList.size())
                    history2.addRestaurant(restaurantList.getRestaurant(3 * i));
                if ((4 * i) < restaurantList.size())
                    history3.addRestaurant(restaurantList.getRestaurant(4 * i));
                history4.addRestaurant(restaurantList.getRestaurant(i));

                if ((5 * i) < restaurantList.size())
                    favourite.addRestaurant(restaurantList.getRestaurant(5 * i));
                if ((1 + (5 * i)) < restaurantList.size())
                    favourite1.addRestaurant(restaurantList.getRestaurant(1 + (5 * i)));
                if ((2 + (5 * i)) < restaurantList.size())
                    favourite2.addRestaurant(restaurantList.getRestaurant(2 + (5 * i)));
                if ((3 + (5 * i)) < restaurantList.size())
                    favourite3.addRestaurant(restaurantList.getRestaurant(3 + (5 * i)));
            }

            Profile admin = new Profile("admin", "", "administrator", "administrator", "administrator@gmail.com", "@drawable/default_profile", history1, favourite);
            Profile noName = new Profile("", "", "noName", "noLastName", "noNameIsNice@gmail.com", "@drawable/default_profile", history2, favourite1);
            Profile camille = new Profile("camille", "azerty", "Camille", "Bourgeois", "camille.bourgeois@hotmail.com", "@drawable/camille", history3, favourite2);
            Profile elvis = new Profile("elvis", "azerty", "Elvis", "Pressé", "elvisLeBG@orange.fr", "@drawable/elvis", history4, favourite3);
            Profile richard = new Profile("richard", "azerty", "Richard", "Cuterie", "richouCute@gmail.com", "@drawable/richard", history1, favourite);

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

            for (Profile profile : profiles) {
                for (Comment comment : profile.getUserComments().getCommentList()) {
                    comment.getRestaurant().getCommentList().addComment(comment);
                }
            }
        }
    }
}
