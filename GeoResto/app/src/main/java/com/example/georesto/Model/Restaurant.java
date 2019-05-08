package com.example.georesto.Model;

import android.graphics.Bitmap;
import android.location.Location;
import android.support.annotation.NonNull;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.text.DecimalFormat;
import java.util.List;

public class Restaurant {
    private String restaurant, adress, website, phoneNumber;
    private String[] schedule;
    private List<Tag> tags;
    private LatLng position;
    private double grade, price, distance;
    private boolean kindRestaurant;
    private Bitmap restaurantPicture;
    private CommentList commentList;


    public Restaurant(String restaurant, boolean kindRestaurant, String adress, String website, String phoneNumber, String[] schedule, double grade, double price, List<Tag> tags, LatLng position,Bitmap restaurantPicture) {
        this.restaurant = restaurant;
        this.kindRestaurant = kindRestaurant;
        this.adress = adress;
        this.website = website;
        this.phoneNumber = phoneNumber;
        this.schedule = schedule;
        this.grade = grade;
        this.price = price;
        this.distance = 0;
        this.tags = tags;
        this.position = position;
        this.restaurantPicture = restaurantPicture;
        this.commentList = new CommentList();
    }

    public double getGrade() {
        return this.grade;
    }

    public double getPrice() {
        return this.price;
    }

    public double getDistance() {
        return this.distance;
    }

    public CommentList getCommentList(){
        return this.commentList;
    }

    public void setDistance(LatLng userLocation) {
        Location loc1 = new Location("");
        loc1.setLatitude(position.latitude);
        loc1.setLongitude(position.longitude);

        Location loc2 = new Location("");
        loc2.setLatitude(userLocation.latitude);
        loc2.setLongitude(userLocation.longitude);

        this.distance = loc1.distanceTo(loc2);
    }

    public String getName() {
        return this.restaurant;
    }

    public List<Tag> getTags() {
        return this.tags;
    }

    public String getSchedule(int dayNumber) {
        switch (dayNumber) {
            case 0:
                return this.schedule[0];
            case 1:
                return this.schedule[1];
            case 2:
                return this.schedule[2];
            case 3:
                return this.schedule[3];
            case 4:
                return this.schedule[4];
            case 5:
                return this.schedule[5];
            case 6:
                return this.schedule[6];
        }
        return null;
    }

    public String getWebsite() {
        return this.website;
    }

    public String getAdress() {
        return this.adress;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public String[] getCompleteSchedule() {
        if (schedule != null)
            return this.schedule;
        return null;
    }

    public boolean isKindRestaurant() {
        return this.kindRestaurant;
    }

    public void setPosition(LatLng position) {
        this.position = position;
    }

    public LatLng getPosition() { return this.position; }

    public void addSchedule(String[] schedule) {
        this.schedule = schedule;
    }

    public void setMarkerOnMap(GoogleMap map) {
        DecimalFormat df = new DecimalFormat("####.###");
        if (this.kindRestaurant) {
            map.addMarker(new MarkerOptions()
                    .position(this.position)
                    .title(this.restaurant)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

        } else {
            map.addMarker(new MarkerOptions()
                    .position(this.position)
                    .title(df.format(distance/1000) + " km")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));
        }

    }

    @NonNull
    @Override
    public String toString() {
        StringBuilder string = new StringBuilder("Le nom du restaurant est : " + this.restaurant + "\n" +
                "L'adresse du restaurant est : " + this.adress + "\n" +
                "Le site internet du restaurant est : " + this.website + "\n" +
                "Le numéro de téléphone du restaurant est : " + this.phoneNumber + "\n" +
                "L'emploi du temps du restaurant est le suivant : ");
        for (int i = 0; i < schedule.length; i++) {
            string.append("Jour ").append(i).append(" le restaurant a pour horaires ").append(schedule[i]);
        }
        string.append("Le restaurant a pour note : ").append(grade);

        return string.toString();
    }

    public Bitmap getPicture() {
        return this.restaurantPicture;
    }
}
