package sample.models;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import sample.Main;
import sample.Profile;
import sample.View;
import sample.controllers.LoginController;
import sample.controllers.OnlineController;
import sample.controllers.OfflineController;

import java.io.IOException;

public class SignInModel extends Model {

    public SignInModel(){

    }

    public void login(){

        String fxmlFile = View.LOG_IN;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.getClass().getResource(fxmlFile);
            Parent root = loader.load(getClass().getResourceAsStream(fxmlFile));
            root.getStylesheets().add(View.CSS_FILE);
            Scene scene = new Scene(root);
            Main.stage.setScene(scene);
            ((LoginController) loader.getController()).init();
            Main.stage.show();

        }catch (IOException e){
            e.printStackTrace();
        }
    }


    public void creatingProfile(String username, String email, String password, String firstName, String lastName) {
        Profile profile = new Profile(username,password,firstName,lastName,email);
        Main.profileList.add(profile);
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
