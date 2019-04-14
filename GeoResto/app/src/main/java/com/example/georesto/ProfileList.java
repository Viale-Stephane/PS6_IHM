package com.example.georesto;


import android.media.Image;
import android.media.ImageReader;

import java.util.ArrayList;

public class ProfileList {
    static ArrayList<Profile> profiles = new ArrayList<>();

    public ProfileList(){
        Profile admin = new Profile("admin","","administrator","administrator","administrator@gmail.com",null);
        profiles.add(admin);
    }

    public static ArrayList<Profile> getProfiles(){
        return profiles;
    }
}
