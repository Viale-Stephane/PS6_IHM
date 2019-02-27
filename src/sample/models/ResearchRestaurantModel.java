package sample.models;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import sample.Main;
import sample.View;
import sample.controllers.AddLocationController;
import sample.controllers.ResearchRestaurantController;
import sample.controllers.RestaurantPageController;

import java.io.IOException;

public class ResearchRestaurantModel {

    public ResearchRestaurantModel(){}

    public void slideBarPrice(){

    }

    public void filter(){
        String fxmlFile= View.RESTORANT_PAGE;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.getClass().getResource(fxmlFile);
            Parent root = loader.load(getClass().getResourceAsStream(fxmlFile));
            Scene scene = new Scene(root);
            Main.stage.setScene(scene);
            ((RestaurantPageController) loader.getController()).init();
            Main.stage.show();

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
