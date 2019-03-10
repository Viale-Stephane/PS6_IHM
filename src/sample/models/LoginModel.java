package sample.models;

import sample.Main;
import sample.Profile;

import java.util.ArrayList;

public class LoginModel extends Model {

    public LoginModel(){}

    public void connecting(String identifiant, String password){
        ArrayList<Profile> profiles = Main.profileList.getProfiles();
        for(Profile profile : profiles){
            if(profile.getUsername().equals(identifiant) && profile.getPassword().equals(password)){
                super.comeBackToHome(profile);
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
