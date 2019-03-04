package sample.models;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import sample.Main;
import sample.Profile;
import sample.View;
import sample.controllers.OfflineController;
import sample.controllers.OnlineController;

import java.io.IOException;

public class Model {

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


    public void offline() {
        String fxmlFile = "../"+View.HOME_OFFLINE;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.getClass().getResource(fxmlFile);
            Parent root = loader.load(getClass().getResourceAsStream(fxmlFile));
            root.getStylesheets().add(View.CSS_FILE);
            Scene scene = new Scene(root);
            Main.stage.setScene(scene);
            ((OfflineController) loader.getController()).init();
            Main.stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
