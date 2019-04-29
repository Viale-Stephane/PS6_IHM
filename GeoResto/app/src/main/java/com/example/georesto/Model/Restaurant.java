package com.example.georesto.Model;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private String restaurant, adress, website, phoneNumber;
    private String[] schedule;
    private List<Tag> tags;
    private LatLng position;
    private double grade, price, distance;
    private boolean kindRestaurant;

    public Restaurant(String restaurant, boolean kindRestaurant, String adress, String website, String phoneNumber, String[] schedule, double grade, double price, double distance, List<Tag> tags, LatLng position){
        this.restaurant=restaurant;
        this.kindRestaurant = kindRestaurant;
        this.adress=adress;
        this.website=website;
        this.phoneNumber=phoneNumber;
        this.schedule=schedule;
        this.grade=grade;
        this.price=price;
        this.distance=distance;
        this.tags = tags;
        this.position = position;
    }

    public double getGrade(){
        return this.grade;
    }

    public double getPrice(){
        return this.price;
    }

    public double getDistance(){
        return this.price;
    }

    public String getName(){
        return this.restaurant;
    }

    public void setMarkerOnMap(GoogleMap map) {
        if(this.kindRestaurant) {
            map.addMarker(new MarkerOptions()
                    .position(this.position)
                    .title(this.restaurant)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

        } else {
            map.addMarker(new MarkerOptions()
                    .position(this.position)
                    .title(this.restaurant)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));
        }

    }

    public List<Tag> getTags(){ return this.tags;}

    public void printRestaurant(){
        System.out.println("Le nom du restaurant est : "+this.restaurant);
        System.out.println("L'adresse du restaurant est : "+this.adress);
        System.out.println("Le site internet du restaurant est : "+this.website);
        System.out.println("Le numéro de téléphone du restaurant est : "+this.phoneNumber);
        System.out.println("L'emploi du temps du restaurant est le suivant : ");
        for(int i=0;i<schedule.length;i++){
            System.out.println("Jour "+i+" le restaurant a pour horaires "+schedule[i]);
        }
        System.out.println("Le restaurant a pour note : "+grade);
    }

    public void addSchedule(String[] schedule) {
        this.schedule = schedule;
    }

    public String getSchedule(int dayNumber) {
        switch(dayNumber){
            case 0:
                return "Lundi : "+this.schedule[0];
            case 1:
                return "Mardi : "+this.schedule[1];
            case 2:
                return "Mercredi : "+this.schedule[2];
            case 3:
                return "Jeudi : "+this.schedule[3];
            case 4:
                return "Vendredi : "+this.schedule[4];
            case 5:
                return "Samedi : "+this.schedule[5];
            case 6:
                return "Dimanche : "+this.schedule[6];
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

    public boolean isKindRestaurant() {
        return this.kindRestaurant;
    }
}
