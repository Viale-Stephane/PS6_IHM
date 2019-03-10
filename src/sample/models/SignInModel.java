package sample.models;


import sample.Main;
import sample.Profile;
import sample.View;

public class SignInModel extends Model {

    public SignInModel(){

    }

    public void creatingProfile(String username, String email, String password, String firstName, String lastName) {
        Profile profile = new Profile(username,password,firstName,lastName,email);
        Main.profileList.add(profile);
        super.accessingTo(profile,View.HOME_ONLINE,View.CSS_FILE,"OnlineController");
    }

}
