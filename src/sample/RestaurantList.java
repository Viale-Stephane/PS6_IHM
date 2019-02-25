package sample;

import java.util.ArrayList;
import java.util.List;

public class RestaurantList {
    private static ArrayList<Restaurant> restaurants = new ArrayList<Restaurant>();

    public static void addRestaurant(Restaurant restaurant){
        restaurants.add(restaurant);
    }

    public List<Restaurant> getRestaurants(){
        return this.restaurants;
    }
    public static void printRestaurants(){
        for(int i=0;i<restaurants.size();i++){
            System.out.println("Information du restaurant numéro "+i+ ": ");
            restaurants.get(i).printRestaurant();
        }
    }
}
