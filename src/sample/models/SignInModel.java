package sample.models;


import sample.Main;
import sample.Profile;
import sample.View;

public class SignInModel extends Model {

    public SignInModel(){

    }

    public String creatingProfile(String username, String email, String password,String verifyPassword, String firstName, String lastName) {
        if(email.isEmpty())
            return "Vous devez remplir le champ \"Email\"";
        else if(username.isEmpty())
            return "Vous devez remplir le champ \"Nom d'utilisateur\"";
        else if(password.isEmpty())
            return "Vous devez remplir le champ \"Mot de passe\"";
        else if(verifyPassword.isEmpty())
            return "Vous devez remplir le champ \"Répétez le mot de passe\"";
        else if(firstName.isEmpty())
            return "Vous devez remplir le champ \"Prénom\"";
        else if(lastName.isEmpty())
            return "Vous devez remplir le champ \"Nom\"";
        else if(!password.equals(verifyPassword))
            return "les deux champs mot de passe ne sont pas identique";
        Profile profile = new Profile(username,password,firstName,lastName,email);
        Main.profileList.add(profile);
        super.comeBackToHome(profile);
        return null;
    }

}
