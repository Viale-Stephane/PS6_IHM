package com.example.georesto.Model;

import android.graphics.Bitmap;
import android.location.Location;
import android.support.annotation.NonNull;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import static com.example.georesto.Activity.MapsActivity.df;

public class Restaurant {
    private String restaurant, address, website, phoneNumber;
    private String[] schedule;
    private List<Tag> tags;
    private LatLng position;
    private double grade, price, distance;
    private boolean kindRestaurant;
    private Bitmap restaurantPicture;
    private CommentList commentList;
    private static final DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");


    public Restaurant(String restaurant, boolean kindRestaurant, String address, String website, String phoneNumber, String[] schedule, double grade, double price, List<Tag> tags, LatLng position,Bitmap restaurantPicture) {
        this.restaurant = restaurant;
        this.kindRestaurant = kindRestaurant;
        this.address = address;
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

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public void setDistance(LatLng userLocation) {
        if(userLocation!= null) {
            Location loc1 = new Location("");
            loc1.setLatitude(position.latitude);
            loc1.setLongitude(position.longitude);

            Location loc2 = new Location("");
            loc2.setLatitude(userLocation.latitude);
            loc2.setLongitude(userLocation.longitude);
            this.distance = loc1.distanceTo(loc2);
        }
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

    public String isItOpen(){
        Date date = new Date();
        String currentDay = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date);
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);   // assigns calendar to given date
        calendar.get(Calendar.HOUR_OF_DAY);
        String openingHours = "";
        String closingHours = "";
        int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY) + calendar.get(Calendar.MINUTE)/60;
        int opHours = 0;
        int clHours= 0;
        int i=0;
        String result = "";
        switch (currentDay) {
            case "Monday":
                i=0;
                break;
            case "Tuesday":
                i=1;
                break;
            case "Wednesday":
                i=2;
                break;
            case "Thursday":
                i=3;
            case "Sunday":
                i=6;
                break;
            case "Saturday":
                i=5;
                break;
            case "Friday" :
                i=4;
                break;
        }
        openingHours = this.schedule[i].split(" ")[1];
        closingHours = this.schedule[i].split(" ")[3];
        opHours = Integer.parseInt(openingHours.split(":")[0]) + Integer.parseInt(openingHours.split(":")[1])/60;
        clHours = Integer.parseInt(closingHours.split(":")[0]) + Integer.parseInt(closingHours.split(":")[1])/60;
        if ( hourOfDay > opHours && hourOfDay < clHours) {
            result = "Ouvert: ferme à " + closingHours;
        } else if (hourOfDay < opHours || hourOfDay > clHours) {
            result = "Fermé: ouvre à " + openingHours;
            result = "Fermé: ouvre à " + openingHours;
        }

        return result;
    }

    public String getWebsite() {
        return this.website;
    }

    public String getAddress() {
        return this.address;
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
                "L'adresse du restaurant est : " + this.address + "\n" +
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
