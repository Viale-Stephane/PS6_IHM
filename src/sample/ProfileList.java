package sample;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class ProfileList {
    private ArrayList<Profile> profiles;

    private Image administratorImage = new Image("sample/data/Images/Profile_Picture/admin.jpg");

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
        Profile Paul = new Profile("Paul","azerty", "Paul", "Aimé", "paulaime@gmail.com",administratorImage);
        Profile administrator = new Profile("", "", "administrator", "administrator", "administrator@gmail.com",administratorImage);
        administrator.addFavori(restolist.getRestaurant(0));
        administrator.addFavori(restolist.getRestaurant(1));
        administrator.addHistory(restolist.getRestaurant(0),new Date(112,0,14));
        administrator.addHistory(restolist.getRestaurant(1),new Date(119,11,12));

        Profile[] samples = {Paul, administrator};
        this.profiles.addAll(Arrays.asList(samples));
    }

}
