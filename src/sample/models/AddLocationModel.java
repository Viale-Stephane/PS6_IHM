package sample.models;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import sample.*;
import sample.controllers.ProfileController;

import java.io.IOException;
import java.util.ArrayList;

public class AddLocationModel {

    public AddLocationModel(){
    }

    public void addLocation(String restaurant, boolean kindRestaurant, String adress, String website, String phoneNumber, String[] schedule, int grade, int averagePrice, int averageDistance, ArrayList<Tag> tags){
        Restaurant newRestaurant = new Restaurant(restaurant, kindRestaurant, adress, website, phoneNumber, schedule, grade, averagePrice, averageDistance,tags);
        Main.restaurantList.addRestaurant(newRestaurant);
    }

    public String compute(String button){
        String answer="";
        String fxmlFile="";
        switch(button) {
            case "cancel":
                answer = "Cancelling...";
                fxmlFile = View.PROFILE;
                break;
            case "save":
                answer = "Adding the restaurant to our database..";
                fxmlFile = "../data/FXML/";//MODIFY
                break;
        }
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.getClass().getResource(fxmlFile);
            Parent root = loader.load(getClass().getResourceAsStream(fxmlFile));
            Scene scene = new Scene(root);
            Main.stage.setScene(scene);
            ((ProfileController) loader.getController()).init();
            Main.stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return answer;
    }
}
