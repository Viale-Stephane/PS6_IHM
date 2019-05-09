package com.example.georesto.Model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.georesto.R;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class RestaurantList {
    private ArrayList<Restaurant> restaurants = new ArrayList<>();

    public RestaurantList() {
    }

    public RestaurantList(List<Restaurant> restaurants) {
        this.restaurants.addAll(restaurants);
    }

    public void addRestaurant(Restaurant restaurant) {
        this.restaurants.add(restaurant);
    }

    public void sampleRestaurant(Context context) {
        String[] schedule = {"09h-12h", "09h-12h", "09h-12h", "09h-12h", "09h-12h", "09h-12h", "09h-12h"};
        //ArrayList<Tag> tagsBK = (ArrayList<Tag>) Arrays.asList(Tag.Pizza,Tag.Burger);
        //tagsBK.add(Tag.FastFood);
        //tagsBK.add(Tag.Burger);
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.camille);
        Restaurant burgerKing = new Restaurant("BurgerKing", true, "36 avenue Weisweiller, Angle Route De Grasse, 06600 Antibes", "www.bkvousecoute.fr", "0422000150", schedule, 3.2, 20, new ArrayList<>(Arrays.asList(Tag.Burger, Tag.FastFood)), new LatLng(43.599070, 7.085523), bitmap);
        Restaurant mcDonaldS = new Restaurant("McDonald's", true, "1990 Route de Grasse, 06600 Antibes", "https://www.mcdonalds.fr/", "https://www.mcdonalds.fr/", schedule, 4.4, 50, new ArrayList<>(Arrays.asList(Tag.Burger, Tag.Fromage, Tag.FastFood)), new LatLng(43.599959, 7.086446), bitmap);
        Restaurant ilRistorante = new Restaurant("IL RISTORANTE", true, "50 voie Marie Fisher, Centre commercial Olympie, 06160 Antibes", "www.ilristorante.fr", "0422000150", schedule, 3.2, 5, new ArrayList<>(Arrays.asList(Tag.Italien, Tag.Fromage)), new LatLng(43.599730, 7.085336), bitmap);
        Restaurant laCiteDOr = new Restaurant("La Cité d'or", true, "172 Avenue Weisweiller, 06600 Antibes", "citéd'or.fr", "0422000150", schedule, 1.2, 109, new ArrayList<>(Arrays.asList(Tag.Chinois, Tag.Healthy, Tag.FastFood)), new LatLng(43.597924, 7.084902), bitmap);
        Restaurant carrefourAntibes = new Restaurant("Carrefour", false, "Chemin de Saint-Claude, 06600 Antibes", "www.carrefour.fr", "0682392412", schedule, 4.2, 3, new ArrayList<>(Arrays.asList(Tag.Francais, Tag.FastFood, Tag.Healthy)), new LatLng(43.603813, 7.089160), bitmap);
        Restaurant[] listOfRestaurants = {burgerKing, mcDonaldS, ilRistorante, laCiteDOr, carrefourAntibes};

        this.restaurants.addAll(Arrays.asList(listOfRestaurants));
    }

    public ArrayList<Restaurant> getRestaurants() {
        return this.restaurants;
    }

    public Restaurant getRestaurant(int i) { return this.restaurants.get(i); }

    public int size() {return this.restaurants.size(); }
}
