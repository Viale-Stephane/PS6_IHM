package sample;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class Profile {
    String username, password;
    String firstName, lastName, email;
    ArrayList<Restaurant> favoris= new ArrayList<>();
    ArrayList<History> history = new ArrayList<>();
    Image profileImage;
    CommentList userComments;

    public Profile(String username, String password, String firstName, String lastName, String email, Image profileImage){
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.profileImage = profileImage;
        this.userComments = new CommentList();
    }

    public Profile(){
        this.username = null;
        this.password = null;
        this.firstName = null;
        this.lastName = null;
        this.email = null;
        this.profileImage = null;
        this.userComments = null;
    }

    public void addFavori(Restaurant restaurant) {
        this.favoris.add(restaurant);
    }

    public void removeFavori(Restaurant restaurant) { this.favoris.remove(restaurant); }

    public Restaurant getFavori(int i){
        return this.favoris.get(i);
    }

    public ArrayList<Restaurant> getFavoris() {
        return this.favoris;
    }

    public boolean isFavori(Restaurant restaurant) {return favoris.contains(restaurant);}


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
        return this.email;
    }

    public Image getProfileImage() { return this.profileImage; }

    public CommentList getUserComments() {
        return userComments;
    }

    public boolean isNull(){
        if(this.username == null || this.password == null || this.firstName == null || this.lastName == null || this.email == null)
            return true;
        return false;
    }

    public ArrayList<History> getHistory(){
        return history;
    }

    public void addHistory(Restaurant restaurant, Date date){
        this.history.add(new History(restaurant,date));
    }

    public History getRestaurant(int number){
        return this.history.get(number);
    }
}
