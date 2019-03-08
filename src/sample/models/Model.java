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

public class Model {

    public void accessingTo(Profile profile, String fxmlFile, String cssFile, String controller){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.getClass().getResource(fxmlFile);
            Parent root = loader.load(getClass().getResourceAsStream(fxmlFile));
            root.getStylesheets().add(cssFile);
            Scene scene = new Scene(root);
            Main.stage.setScene(scene);
            switch(controller){
                case "AddLocationController":
                    ((AddLocationController) loader.getController()).init(profile);
                    break;
                case "ApplicationInformationsController":
                    ((ApplicationInformationsController) loader.getController()).init(profile);
                    break;
                case "FavorisController":
                    ((FavorisController) loader.getController()).init(profile);
                    break;
                case "HistoryController":
                    ((HistoryController) loader.getController()).init(profile);
                    break;
                case "LoginController":
                    ((LoginController) loader.getController()).init();
                    break;
                case "OfflineController":
                    ((OfflineController) loader.getController()).init();
                    break;
                case "OnlineController":
                    ((OnlineController) loader.getController()).init(profile);
                    break;
                case "ProfileController":
                    ((ProfileController) loader.getController()).init(profile);
                    break;
                case "ResearchRestaurantController":
                    ((ResearchRestaurantController) loader.getController()).init(profile);
                    break;
                case "SignInController":
                    ((SignInController) loader.getController()).init();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void accessRestaurantPage(Restaurant restaurant, Profile profile) {
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
    }

    public String search(String researchBarText,Profile profile) {
        ArrayList<Restaurant> restaurants = Main.restaurantList.getRestaurants();
        for(Restaurant restaurant: restaurants){
            if(restaurant.getName().equals(researchBarText)){
                this.accessRestaurantPage(restaurant,profile);
                return "Accessing to the page of "+researchBarText+"..";
            }
        }
        return  "This location doesn't exist..";
    }
}
