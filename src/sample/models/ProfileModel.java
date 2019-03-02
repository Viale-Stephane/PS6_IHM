package sample.models;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.Main;
import sample.Profile;
import sample.View;
import sample.controllers.AddLocationController;
import sample.controllers.Controller;
import sample.controllers.FavorisController;
import sample.controllers.OnlineController;

import java.io.IOException;

public class ProfileModel {


    public ProfileModel(){
    }

    public String accessingTo(String button, Profile profile){
        String answer="";
        String fxmlFile="";
        switch(button) {
            case "history":
                answer = "Accessing to your history..";
                fxmlFile = View.HISTORY;
                break;
            case "favorites":
                answer = "Accessing to your favorites..";
                fxmlFile = View.FAVORITES;
                break;
            case "myRatings":
                answer = "Accessing to your ratings..";
                fxmlFile = View.MY_RATINGS;
                break;
            case "addLocation":
                answer ="You will be redirected to the new location system..";
                fxmlFile = View.NEW_LOCATION;
                break;
            case "logOut":
                answer = "You are getting disconnected..";
                fxmlFile = "../"+View.HOME_OFFLINE;
                break;
        }
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.getClass().getResource(fxmlFile);
            Parent root = loader.load(getClass().getResourceAsStream(fxmlFile));
            root.getStylesheets().add(View.CSS_FILE);
            Scene scene = new Scene(root);
            Main.stage.setScene(scene);
            switch(button){
                case "history":
                    break;
                case "favorites":
                    ((FavorisController) loader.getController()).init(profile);
                    break;
                case "myRatings":
                    break;
                case "addLocation":
                    ((AddLocationController) loader.getController()).init(profile);
                    break;
                case "logOut":
                    ((Controller) loader.getController()).init();
                    break;
            }
            Main.stage.show();

        }catch (IOException e){
            e.printStackTrace();
        }

        return answer;
    }

    public void goBack(Profile profile) {
        String fxmlFile = View.HOME_ONLINE;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.getClass().getResource(fxmlFile);
            Parent root = loader.load(getClass().getResourceAsStream(fxmlFile));
            root.getStylesheets().add(View.CSS_FILE);
            Scene scene = new Scene(root);
            Main.stage.setScene(scene);
            ((OnlineController) loader.getController()).init(profile);
            Main.stage.show();

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
