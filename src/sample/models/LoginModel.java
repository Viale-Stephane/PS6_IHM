package sample.models;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import sample.Main;
import sample.Profile;
import sample.View;
import sample.controllers.OfflineController;
import sample.controllers.OnlineController;
import sample.controllers.SignInController;

import java.io.IOException;
import java.util.ArrayList;

public class LoginModel extends Model {

    public LoginModel(){}

    public void connecting(String identifiant, String password){
        ArrayList<Profile> profiles = Main.profileList.getProfiles();
        String fxmlFile = View.HOME_ONLINE;
        for(Profile profile : profiles){
            if(profile.getUsername().equals(identifiant) && profile.getPassword().equals(password)){
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
    }

    public void signIn(){
        String fxmlFile = View.SIGN_IN;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.getClass().getResource(fxmlFile);
            Parent root = loader.load(getClass().getResourceAsStream(fxmlFile));
            root.getStylesheets().add(View.CSS_FILE);
            Scene scene = new Scene(root);
            Main.stage.setScene(scene);
            ((SignInController) loader.getController()).init();
            Main.stage.show();

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public String getPassword(String username) {
        ArrayList<Profile> profiles = Main.profileList.getProfiles();

        for(Profile profile : profiles){
            if(profile.getUsername().equals(username)){
                return "Votre mot de passe est : \""+profile.getPassword()+"\"";
            }
        }
        return "Votre identifiant n'est pas correct";
    }
}
