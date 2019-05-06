package com.example.georesto.Model;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Arrays;


public class RestaurantList {
    private ArrayList<Restaurant> restaurants = new ArrayList<>();

    public RestaurantList() {
        sampleRestaurant();
    }

    public void addRestaurant(Restaurant restaurant) {
        this.restaurants.add(restaurant);
    }

    public ArrayList<Restaurant> getRestaurants() {
        return this.restaurants;
    }

    public void printRestaurants() {
        for (int i = 0; i < this.restaurants.size(); i++) {
            System.out.println("Information du restaurant numéro " + i + ": ");
            this.restaurants.get(i).printRestaurant();
        }
    }

    public void sampleRestaurant() {
        String[] schedule = {"09h-12h", "09h-12h", "09h-12h", "09h-12h", "09h-12h", "09h-12h", "09h-12h"};
        //ArrayList<Tag> tagsBK = (ArrayList<Tag>) Arrays.asList(Tag.Pizza,Tag.Burger);
        //tagsBK.add(Tag.FastFood);
        //tagsBK.add(Tag.Burger);

        Restaurant burgerKing = new Restaurant("BurgerKing", true,"36 avenue Weisweiller, Angle Route De Grasse, 06600 Antibes","www.bkvousecoute.fr","0422000150",schedule,3.2,20, new ArrayList<Tag>(Arrays.asList(Tag.Burger,Tag.FastFood)),new LatLng(43.599070,7.085523));
        Restaurant McDonalds = new Restaurant("McDonald's", true,"1990 Route de Grasse, 06600 Antibes","https://www.mcdonalds.fr/","https://www.mcdonalds.fr/",schedule,4.4,50,new ArrayList<Tag>(Arrays.asList(Tag.Burger, Tag.Fromage, Tag.FastFood)),new LatLng(43.599959,7.086446));
        Restaurant IlRistorante = new Restaurant("IL RISTORANTE", true,"50 voie Marie Fisher, Centre commercial Olympie, 06160 Antibes","www.ilristorante.fr","0422000150",schedule,3.2,5, new ArrayList<Tag>(Arrays.asList(Tag.Italien, Tag.Fromage)),new LatLng(43.599730,7.085336));
        Restaurant Lacitédor = new Restaurant("La Cité d'or", true,"172 Avenue Weisweiller, 06600 Antibes","citéd'or.fr","0422000150",schedule,1.2,109, new ArrayList<Tag>(Arrays.asList(Tag.Chinois, Tag.Healthy, Tag.FastFood)), new LatLng(43.597924,7.084902));
        Restaurant CarrefourAntibes = new Restaurant("Carrefour", false, "Chemin de Saint-Claude, 06600 Antibes", "www.carrefour.fr", "0682392412", schedule, 4.2,3, new ArrayList<Tag>(Arrays.asList( Tag.Francais, Tag.FastFood, Tag.Healthy)),new LatLng(43.603813,7.089160));
        Restaurant[] listOfRestaurants = {burgerKing,McDonalds,IlRistorante,Lacitédor,CarrefourAntibes};

        this.restaurants.addAll(Arrays.asList(listOfRestaurants));

    }

    public int size() {
        return this.restaurants.size();
    }

    public Restaurant getRestaurants(int i) {
        return this.restaurants.get(i);
    }
}
