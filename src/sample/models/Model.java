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

    public Model(){
    }

    public String compute(String button){
        Profile profile = new Profile(null,null,null,null,null);

        String answer="";
        String fxmlFile="";
        switch(button) {
            case "logIn":
                answer = "Accessing to the log-in page..";
                fxmlFile = View.LOG_IN;
                break;
            case "signIn":
                answer = "Accessing to the sign-in page..";
                fxmlFile = View.SIGN_IN;
                break;
            case "informations":
                answer = "Accessing to the website informations..";
                fxmlFile = View.INFORMATIONS;
                break;
            case "filter":
                answer ="Accessing to the research filters..";
                fxmlFile = View.MENU_FILTER;
                break;
        }
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.getClass().getResource(fxmlFile);
            Parent root = loader.load(getClass().getResourceAsStream(fxmlFile));
            root.getStylesheets().add(View.CSS_FILE);
            Scene scene = new Scene(root);
            Main.stage.setScene(scene);
            switch(button) {
                case "logIn":
                    ((LoginController) loader.getController()).init();
                    break;
                case "signIn":
                    ((SignInController) loader.getController()).init();
                    break;
                case "informations":
                    ((ApplicationInformationsController) loader.getController()).init(profile);
                    break;
                case "filter":
                    ((ResearchRestaurantController) loader.getController()).init(profile);
                    break;
            }
            Main.stage.show();

        }catch (IOException e){
            e.printStackTrace();
        }

        return answer;
    }

    public String search(String researchBarText) {
        Profile profile = new Profile(null,null,null,null,null);
        ArrayList<Restaurant> restaurants = Main.restaurantList.getRestaurants();
        for(Restaurant restaurant: restaurants){
            if(restaurant.getName().equals(researchBarText)){
                String fxmlFile = View.RESTORANT_PAGE;
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
