package sample.models;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import sample.Main;
import sample.Profile;
import sample.Restaurant;
import sample.View;
import sample.controllers.*;

import java.io.IOException;
import java.util.ArrayList;

public class OnlineModel {

    public OnlineModel(){

    }

    public void moveToProfile(Profile profile) {
        String fxmlFile = View.PROFILE;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.getClass().getResource(fxmlFile);
            Parent root = loader.load(getClass().getResourceAsStream(fxmlFile));
            root.getStylesheets().add(View.CSS_FILE);
            Scene scene = new Scene(root);
            Main.stage.setScene(scene);
            ((ProfileController) loader.getController()).init(profile);
            Main.stage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void filter(Profile profile) {
        String fxmlFile = View.MENU_FILTER;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.getClass().getResource(fxmlFile);
            Parent root = loader.load(getClass().getResourceAsStream(fxmlFile));
            root.getStylesheets().add(View.CSS_FILE);
            Scene scene = new Scene(root);
            Main.stage.setScene(scene);
            ((ResearchRestaurantController) loader.getController()).init(profile);
            Main.stage.show();

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void accessInformations(Profile profile) {
        String fxmlFile = View.INFORMATIONS;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.getClass().getResource(fxmlFile);
            Parent root = loader.load(getClass().getResourceAsStream(fxmlFile));
            root.getStylesheets().add(View.CSS_FILE);
            Scene scene = new Scene(root);
            Main.stage.setScene(scene);
            ((ApplicationInformationsController) loader.getController()).init(profile);
            Main.stage.show();

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public String search(String researchBarText,Profile profile) {
        ArrayList<Restaurant> restaurants = Main.restaurantList.getRestaurants();
        for(Restaurant restaurant: restaurants){
            if(restaurant.getName().equals(researchBarText)){
                String fxmlFile = View.RESTAURANT_PAGE;
                try {
                    FXMLLoader loader = new FXMLLoader();
                    loader.getClass().getResource(fxmlFile);
                    Parent root = loader.load(getClass().getResourceAsStream(fxmlFile));
                    root.getStylesheets().add(View.CSS_FILE);
                    Scene scene = new Scene(root);
                    Main.stage.setScene(scene);
                    ((RestaurantPageController) loader.getController()).init(restaurant,profile);
                    Main.stage.show();

                }catch (IOException e){
                    e.printStackTrace();
                }
                return "Accessing to the page of "+researchBarText+"..";
            }
        }
        return  "This location doesn't exist..";
    }
}
