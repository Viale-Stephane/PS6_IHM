package sample;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class ProfileList {
    private ArrayList<Profile> profiles;

    private Image administratorImage = new Image("sample/data/Images/Profile_Picture/admin.jpg");
    private Image paulImage = new Image("sample/data/Images/Profile_Picture/paul.jpg");
    private Image balthazarImage = new Image("sample/data/Images/Profile_Picture/balthazar.jpg");
    private Image chloeImage = new Image("sample/data/Images/Profile_Picture/chloe.jpg");
    private Image sabineImage = new Image("sample/data/Images/Profile_Picture/sabine.jpg");
    private Image richardImage = new Image("sample/data/Images/Profile_Picture/richard.png");
    private Image camilleImage = new Image("sample/data/Images/Profile_Picture/camille.png");

    public ProfileList(){
        profiles = new ArrayList<>();
    }
    public RestaurantList restolist = new RestaurantList();
    public ArrayList<Profile> getProfiles(){
        return this.profiles;
    }

    public void add(Profile profile){
        this.profiles.add(profile);
    }

    public void profileSample(){

        restolist.sampleRestaurant();
        Profile Paul = new Profile("Paul","azerty", "Paul", "Aimé", "paulaime@gmail.com", paulImage);
        Profile Balthazar = new Profile("Balthazar","azerty", "Balthazar", "Noe", "baltNoe@orange.fr", balthazarImage);
        Profile Richard = new Profile("Richard","azerty", "Richard", "Cuterie", "charcuterie@outlook.com", richardImage);
        Profile Camille = new Profile("Camille","azerty", "Camille", "Bourgeois", "camBourgeois@orange.fr", camilleImage);
        Profile Chloe = new Profile("Chloe","azerty", "Chloe", "Jourdan", "chlochlo@outlook.com", chloeImage);
        Profile Sabine = new Profile("Sabine","azerty", "Sabine", "Pasquier", "sabinepas@orange.fr", sabineImage);
        Profile administrator = new Profile("administrator", "", "admin", "admin", "admin@gmail.com", administratorImage);
        administrator.addFavori(restolist.getRestaurant(0));
        administrator.addFavori(restolist.getRestaurant(1));
        administrator.addHistory(restolist.getRestaurant(0),new Date(112,0,14));
        administrator.addHistory(restolist.getRestaurant(1),new Date(119,11,12));

        Paul.addFavori(restolist.getRestaurant(2));
        Paul.addHistory(restolist.getRestaurant(2),new Date(117,3,14));
        Paul.addHistory(restolist.getRestaurant(4),new Date(119,11,12));

        Richard.addFavori(restolist.getRestaurant(0));
        Richard.addHistory(restolist.getRestaurant(0),new Date(118,7,14));
        Richard.addHistory(restolist.getRestaurant(2),new Date(119,11,12));

        Camille.addFavori(restolist.getRestaurant(1));
        Camille.addHistory(restolist.getRestaurant(1),new Date(117,7,2));
        Camille.addHistory(restolist.getRestaurant(3),new Date(119,8,5));

        Chloe.addHistory(restolist.getRestaurant(1),new Date(119,4,2));
        Chloe.addHistory(restolist.getRestaurant(3),new Date(119,10,5));
        
        Profile[] samples = {Paul, Balthazar, Richard, Camille, Chloe, Sabine, administrator};
        Richard.getUserComments().addComment(new Comment("Tres bon service avec de tres bon burger", Richard, restolist.getRestaurant(0)));
        Sabine.getUserComments().addComment(new Comment("Un fastfood classique, sans plus", Sabine, restolist.getRestaurant(0)));

        Camille.getUserComments().addComment(new Comment("Accueil chaleureux et on est bien nourri !", Camille, restolist.getRestaurant(1)));
        Chloe.getUserComments().addComment(new Comment("Un restaurant miteux", Chloe, restolist.getRestaurant(1)));

        Paul.getUserComments().addComment(new Comment("Un kebab moyen mais pas cher", Paul, restolist.getRestaurant(2)));
        Richard.getUserComments().addComment(new Comment("Un tres bon kebab, je recommande !", Richard, restolist.getRestaurant(2)));

        Camille.getUserComments().addComment(new Comment("La qualité est a la hauteur de ces prix...", Camille, restolist.getRestaurant(3)));
        Chloe.getUserComments().addComment(new Comment("Pas cher et pas bon, génial !", Chloe, restolist.getRestaurant(3)));
        Paul.getUserComments().addComment(new Comment("Un endroit ou manger quand on est etudiant", Paul, restolist.getRestaurant(3)));


        this.profiles.addAll(Arrays.asList(samples));
    }

}
