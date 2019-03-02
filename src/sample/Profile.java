package sample;

import java.util.ArrayList;

public class Profile {
    String username, password;
    String firstName, lastName, email;
    ArrayList<Restaurant> favoris= new ArrayList<>();
    public Profile(String username, String password, String firstName, String lastName, String email){
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public void addFavori(Restaurant restaurant) {
        this.favoris.add(restaurant);
    }

    public Restaurant getFavori(int i){
        return this.favoris.get(i);
    }

    public ArrayList<Restaurant> getFavoris() {
        return this.favoris;
    }

    public String getUsername(){
        return this.username;
    }

    public String getPassword(){
        return this.password;
    }

    public String getFirstName(){
        return this.firstName;
    }

    public String getLastName(){
        return this.lastName;
    }

    public String getEmail(){
        return  this.email;
    }

    public boolean isNull(){
        if(this.username == null || this.password == null || this.firstName == null || this.lastName == null || this.email == null)
            return true;
        return false;
    }
}
