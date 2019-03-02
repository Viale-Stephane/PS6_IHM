package sample.models;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import sample.*;
import sample.controllers.AddLocationController;
import sample.controllers.ResearchRestaurantController;
import sample.controllers.RestaurantListController;
import sample.controllers.RestaurantPageController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

public class ResearchRestaurantModel {

    public ResearchRestaurantModel(){}

    public Restaurant lookingForARestaurant(int minStar, Restaurant restaurant, double maxPrice, double maxDistance, ArrayList<String> tags){
        if(!tags.isEmpty()) {
            for (Tag restaurantTag : restaurant.getTags()) {
                for (String tag : tags) {
                    if (("#"+restaurantTag.toString()+" ").equals(tag) && restaurant.getPrice() <= maxPrice && restaurant.getDistance() <= maxDistance && restaurant.getGrade() >= minStar) {
                        return restaurant;
                    }
                }
            }
        }else {
            if (restaurant.getPrice() <= maxPrice && restaurant.getDistance() <= maxDistance && restaurant.getGrade() >= minStar) {
                return restaurant;
            }
        }
        return null;
    }

    public void filter(int minStar, boolean isItARestaurant, double maxPrice, double maxDistance, ArrayList<String> tags,Profile profile){
        if(profile.isNull()){
            profile = new Profile(null,null,null,null,null);
        }
        RestaurantList whiteListedRestaurant = new RestaurantList();
        ArrayList<Restaurant> restaurants = Main.restaurantList.getRestaurants();
        for (Restaurant restaurant : restaurants) {
            Restaurant choosenOne = this.lookingForARestaurant(minStar, restaurant, maxPrice, maxDistance, tags);
            if(choosenOne!= null)
                whiteListedRestaurant.addRestaurant(choosenOne);
        }
        String fxmlFile= View.RESTAURANT_LIST;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.getClass().getResource(fxmlFile);
            Parent root = loader.load(getClass().getResourceAsStream(fxmlFile));
            root.getStylesheets().add(View.CSS_FILE);
            Scene scene = new Scene(root);
            Main.stage.setScene(scene);
            ((RestaurantListController) loader.getController()).init(profile,whiteListedRestaurant);
            Main.stage.show();

        }catch (IOException e){
            e.printStackTrace();
        }
        whiteListedRestaurant.printRestaurants();
    }
}
