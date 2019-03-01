package sample;

import java.util.ArrayList;
import java.util.Arrays;

public class ProfileList {
    private ArrayList<Profile> profiles;

    public ProfileList(){
        profiles = new ArrayList<>();
    }

    public ArrayList<Profile> getProfiles(){
        return this.profiles;
    }

    public void add(Profile profile){
        this.profiles.add(profile);
    }

    public void profileSample(){
        Profile Paul = new Profile("Paul","azerty", "Paul", "Aimé", "paulaime@gmail.com");
        Profile administrator = new Profile("", "", "administrator", "administrator", "administrator@gmail.com");
        Profile[] samples = {Paul, administrator};
        this.profiles.addAll(Arrays.asList(samples));
    }
}
