package sample.models;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import sample.Main;
import sample.View;
import sample.controllers.AddLocationController;
import sample.controllers.ResearchRestaurantController;

import java.io.IOException;

public class Model {

    public Model(){
    }

    public String compute(String button){
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
            Scene scene = new Scene(root);
            Main.stage.setScene(scene);
            switch(button) {
                case "logIn":
                    ((AddLocationController) loader.getController()).init();
                    break;
                case "signIn":
                    break;
                case "informations":
                    break;
                case "filter":
                    ((ResearchRestaurantController) loader.getController()).init();
                    break;
            }
            Main.stage.show();

        }catch (IOException e){
            e.printStackTrace();
        }

        return answer;
    }
}
