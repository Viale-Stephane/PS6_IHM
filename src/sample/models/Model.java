package sample.models;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.Main;
import sample.View;
import sample.controllers.AddLocationController;
import sample.controllers.Controller;

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
                fxmlFile = "../data/FXML/addResto.fxml";
                break;
            case "logOut":
                answer = "You are getting disconnected..";
                fxmlFile = "../data/FXML/";
            break;
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent rootNode = loader.load(getClass().getResourceAsStream(fxmlFile));
            loader.setController(Main.addLocationController);
            //Main.addLocationController.init();
            Scene scene = new Scene(rootNode);
            View.changeStage(scene, View.LABEL,fxmlFile,View.CSS_FILE);


        } catch (IOException e) {
            e.printStackTrace();
        }
        return answer;
    }
}
