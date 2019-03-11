package sample.models;

import javafx.scene.control.Alert;
import sample.Main;
import sample.Profile;

import java.util.ArrayList;

public class LoginModel extends Model {

    public LoginModel(){}

    public boolean connecting(String identifiant, String password){
        ArrayList<Profile> profiles = Main.profileList.getProfiles();
        for(Profile profile : profiles){
            if(profile.getUsername().equals(identifiant) && profile.getPassword().equals(password)){
                super.comeBackToHome(profile);
                return true;
            }
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Mot de passe/identifiant incorrect");
        alert.showAndWait();
        return false;
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
