package sample;

import java.util.ArrayList;
import java.util.Arrays;

public class ProfileList {
    private ArrayList<Profile> profiles;

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
        Profile Paul = new Profile("Paul","azerty", "Paul", "Aimé", "paulaime@gmail.com");
        Profile administrator = new Profile("", "", "administrator", "administrator", "administrator@gmail.com");
        Paul.addFavori(restolist.getRestaurants(0));
        Paul.addFavori(restolist.getRestaurants(1));
        System.out.println(Paul.getFavori(1).getName());
        Profile[] samples = {Paul, administrator};
        this.profiles.addAll(Arrays.asList(samples));
    }

}
