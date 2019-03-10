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

    public void accessingTo(boolean right, Pane leftPane, Pane rightPane, Profile profile, String fxmlFile, String cssFile, String controller){
        try {
            FXMLLoader loader = new FXMLLoader();

            Pane newLoadedPane = loader.load(getClass().getResourceAsStream(fxmlFile));
            newLoadedPane.getStylesheets().add(cssFile);

            leftPane.getChildren().clear();
            rightPane.getChildren().clear();
            if (right) {
                rightPane.getChildren().add(newLoadedPane);
            } else {
                leftPane.getChildren().add(newLoadedPane);
            }

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

    public void clearPanes(Pane... panes) {
        for (Pane pane : panes) {
            pane.getChildren().clear();
        }
    }
}
