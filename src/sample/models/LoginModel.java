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
        for(Profile profile : profiles){
            if(profile.getUsername().equals(identifiant) && profile.getPassword().equals(password)){
                //super.accessingTo(profile,View.HOME_ONLINE,View.CSS_FILE,"OnlineController");
            }
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
