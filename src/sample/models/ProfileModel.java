package sample.models;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.Main;
import sample.View;
import sample.controllers.AddLocationController;

import java.io.IOException;

public class ProfileModel {


    public ProfileModel(){
    }

    public String compute(String button){
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
                fxmlFile = View.LOG_OUT;
                break;
        }
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.getClass().getResource(fxmlFile);
            Parent root = loader.load(getClass().getResourceAsStream(fxmlFile));
            Scene scene = new Scene(root);
            Main.stage.setScene(scene);
            ((AddLocationController) loader.getController()).init();
            Main.stage.show();

        }catch (IOException e){
            e.printStackTrace();
        }

        return answer;
    }
}
