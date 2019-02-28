package sample.models;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import sample.Main;
import sample.Restaurant;
import sample.RestaurantList;
import sample.View;
import sample.controllers.AddLocationController;
import sample.controllers.ResearchRestaurantController;
import sample.controllers.RestaurantPageController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

public class ResearchRestaurantModel {

    public ResearchRestaurantModel(){}

    public void filter(int minStar, boolean isItARestaurant, double maxPrice, double maxDistance){
        RestaurantList whiteListedRestaurant = new RestaurantList();
        ArrayList<Restaurant> restaurants = Main.restaurantList.getRestaurants();
        for (Restaurant restaurant : restaurants) {
            if (restaurant.getPrice() <= maxPrice && restaurant.getDistance() <= maxDistance && restaurant.getGrade() >= minStar) {
                whiteListedRestaurant.addRestaurant(restaurant);
            }
        }
        String fxmlFile= View.RESTORANT_PAGE;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.getClass().getResource(fxmlFile);
            Parent root = loader.load(getClass().getResourceAsStream(fxmlFile));
            Scene scene = new Scene(root);
            Main.stage.setScene(scene);
            ((RestaurantPageController) loader.getController()).init();
            Main.stage.show();

        }catch (IOException e){
            e.printStackTrace();
        }
        whiteListedRestaurant.printRestaurants();
    }
}
