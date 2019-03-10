package sample.models;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import sample.Main;
import sample.Profile;
import sample.Restaurant;
import sample.View;
import sample.controllers.*;

import java.io.IOException;
import java.util.ArrayList;

public class Model {

    public void accessingTo(Pane pane, Profile profile, String fxmlFile, String cssFile, String controller){
        try {
            FXMLLoader loader = new FXMLLoader();

            Pane newLoadedPane = loader.load(getClass().getResourceAsStream(fxmlFile));
            newLoadedPane.getStylesheets().add(cssFile);

            pane.getChildren().clear();
            pane.getChildren().add(newLoadedPane);

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
                case "ProfileController":
                    ((ProfileController) loader.getController()).init(profile);
                    break;
                case "ResearchRestaurantController":
                    ((ResearchRestaurantController) loader.getController()).init(profile);
                    break;
                case "MyRatingsController":
                    ((MyRatingsController) loader.getController()).init(profile);
                    break;
                case "SignInController":
                    ((SignInController) loader.getController()).init();
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void comeBackToHome(Profile profile) {
        try {
            FXMLLoader loader = new FXMLLoader();
            if (profile.isNull()) {
                Parent root = loader.load(getClass().getResourceAsStream(View.HOME_OFFLINE));
                root.getStylesheets().add(View.CSS_FILE);
                Scene scene = new Scene(root);
                Main.stage.setScene(scene);
                ((OfflineController) loader.getController()).init();
            } else {
                Parent root = loader.load(getClass().getResourceAsStream(View.HOME_ONLINE));
                root.getStylesheets().add(View.CSS_FILE);
                Scene scene = new Scene(root);
                Main.stage.setScene(scene);
                ((OnlineController) loader.getController()).init(profile);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void accessRestaurantPage(Pane pane, Restaurant restaurant, Profile profile) {
        try {
            FXMLLoader loader = new FXMLLoader();
            Parent newLoadedPane = loader.load(getClass().getResourceAsStream(View.RESTAURANT_PAGE));
            newLoadedPane.getStylesheets().add(View.CSS_FILE);

            pane.getChildren().clear();
            pane.getChildren().add(newLoadedPane);

            ((RestaurantPageController) loader.getController()).init(restaurant,profile);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public String search(Pane pane, String researchBarText,Profile profile) {
        ArrayList<Restaurant> restaurants = Main.restaurantList.getRestaurants();
        for(Restaurant restaurant: restaurants){
            if(restaurant.getName().equals(researchBarText)){
                this.accessRestaurantPage(pane, restaurant,profile);
                return "Accessing to the page of "+researchBarText+"..";
            }
        }
        return  "This location doesn't exist..";
    }

    public void clearPanes(Pane... panes) {
        for (Pane pane : panes) {
            pane.getChildren().clear();
        }
    }
}
