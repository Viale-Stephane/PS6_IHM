package sample.models;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
            case "logIn":
                answer = "Accessing to your history..";
                fxmlFile = View.LOG_IN;
                break;
            case "signIn":
                answer = "Accessing to your favorites..";
                fxmlFile = View.SIGN_IN;
                break;
            case "informations":
                answer = "Accessing to your ratings..";
                fxmlFile = View.INFORMATIONS;
                break;
            case "filter":
                answer ="You will be redirected to the new location system..";
                fxmlFile = View.MENU_FILTER;
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
