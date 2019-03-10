package sample;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class Profile {
    private String username, password;
    private String firstName, lastName, email;
    private ArrayList<Restaurant> favoris= new ArrayList<>();
    private ArrayList<History> history = new ArrayList<>();
    private Image profileImage;
    private CommentList userComments;

    private Image NEW_PROFILE = new Image("sample/data/Images/Profile_Picture/default_profile.jpg");
    public Profile(String username, String password, String firstName, String lastName, String email, Image profileImage){
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.profileImage = profileImage;
        this.userComments = new CommentList();
    }

    public Profile(String username, String password, String firstName, String lastName, String email){
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.profileImage = NEW_PROFILE;
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

    public History getHistory(int i){ return this.history.get(i);}

    public void addHistory(Restaurant restaurant, Date date){
        this.history.add(new History(restaurant,date));
    }

    public History getRestaurant(int number){
        return this.history.get(number);
    }
}
