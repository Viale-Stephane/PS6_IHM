package sample.models;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.Main;
import sample.View;
import sample.controllers.AddLocationController;

import java.io.IOException;

public class Model {


    public Model(){
    }

    public String compute(String button){
        String answer="";
        String fxmlFile="";
        switch(button) {
            case "history":
                answer = "Accessing to your history..";
                fxmlFile = "../data/FXML/";
                break;
            case "favorites":
                answer = "Accessing to your favorites..";
                fxmlFile = "../data/FXML/";
                break;
            case "myRatings":
                answer = "Accessing to your ratings..";
                fxmlFile = "../data/FXML/";
                break;
            case "addLocation":
                answer ="You will be redirected to the new location system..";
                fxmlFile = View.NEW_LOCATION;
                break;
            case "logOut":
                answer = "You are getting disconnected..";
                fxmlFile = "../data/FXML/";
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
