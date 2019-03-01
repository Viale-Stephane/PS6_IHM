package sample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static sample.Tag.FastFood;

public class RestaurantList {
    private ArrayList<Restaurant> restaurants;

    public RestaurantList(){
        this.restaurants = new ArrayList<>();
    }

    public void addRestaurant(Restaurant restaurant){
        this.restaurants.add(restaurant);
    }

    public ArrayList<Restaurant> getRestaurants(){
        return this.restaurants;
    }
    public void printRestaurants(){
        for(int i=0;i<this.restaurants.size();i++){
            System.out.println("Information du restaurant numéro "+i+ ": ");
            this.restaurants.get(i).printRestaurant();
        }
    }

    public void sampleRestaurant(){
        String[] schedule = {"09h-12h", "09h-12h", "09h-12h", "09h-12h", "09h-12h", "09h-12h", "09h-12h"};
        //ArrayList<Tag> tagsBK = (ArrayList<Tag>) Arrays.asList(Tag.Pizza,Tag.Burger);
        //tagsBK.add(Tag.FastFood);
        //tagsBK.add(Tag.Burger);
        Restaurant burgerKing = new Restaurant("BurgerKing","36 avenue Weisweiller, Angle Route De Grasse, 06600 Antibes","www.bkvousecoute.fr","0422000150",schedule,3.2,20,20, new ArrayList<Tag>(Arrays.asList(Tag.Burger,Tag.FastFood)));
        Restaurant chezBernard = new Restaurant("Chez Bernard","36 avenue Weisweiller, Angle Route De Grasse, 06600 Antibes","www.bkvousecoute.fr","0422000150",schedule,4.4,50,10,new ArrayList<Tag>(Arrays.asList(Tag.Francais, Tag.Fromage, Tag.Healthy)));
        Restaurant kebab = new Restaurant("Kebabinou","36 avenue Weisweiller, Angle Route De Grasse, 06600 Antibes","www.bkvousecoute.fr","0422000150",schedule,2.2,5,2, new ArrayList<Tag>(Arrays.asList(Tag.FastFood, Tag.Kebab)));
        Restaurant degueulasse = new Restaurant("C'est dégueulasse","36 avenue Weisweiller, Angle Route De Grasse, 06600 Antibes","www.bkvousecoute.fr","0422000150",schedule,1.2,109,50, new ArrayList<Tag>(Arrays.asList(Tag.Burger, Tag.Pizza, Tag.Kebab, Tag.FastFood)));
        Restaurant[] listOfRestaurants = {burgerKing,chezBernard,kebab,degueulasse};
        this.restaurants.addAll(Arrays.asList(listOfRestaurants));

    }
}
