package com.example.georesto.Model;

import java.util.ArrayList;

public class Profile {
    private String username, password;
    private String firstName, lastName, email;
    private String linkImage;
    private RestaurantList history;
    private RestaurantList favourite;
    private CommentList userComments;


    public Profile(String username, String password, String firstName, String lastName, String email, String linkImage, RestaurantList history, RestaurantList favourite) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.linkImage = linkImage;
        this.history = history;
        this.favourite = favourite;
        this.userComments = new CommentList();
    }

    public void updateDistance(RestaurantList restaurantList) {
        for(Restaurant updatedRestaurant: restaurantList.getRestaurants()) {
            for (Restaurant restaurant : this.favourite.getRestaurants()) {
                if (updatedRestaurant == restaurant) {
                    restaurant.setDistance(updatedRestaurant.getDistance());
                }
            }
            for (Restaurant restaurant : this.history.getRestaurants()) {
                if(updatedRestaurant == restaurant) {
                    restaurant.setDistance(updatedRestaurant.getDistance());
                }
            }
            for(Comment comment : this.userComments.getCommentList()) {
                if(updatedRestaurant == comment.getRestaurant()) {
                    comment.getRestaurant().setDistance(updatedRestaurant.getDistance());

                }
            }
        }

    }

    public String getFirstName() { return this.firstName; }

    public String getLastName() { return this.lastName; }

    public String getUsername() {
        return this.username;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public String getLinkImage() { return this.linkImage; }

    public CommentList getUserComments() {
        return userComments;
    }

    public RestaurantList getHistory() { return this.history; }

    public RestaurantList getFavourite() { return this.favourite; }

    public ArrayList<Restaurant> getFavourites() { return this.favourite.getRestaurants(); }

    public void addFavourite(Restaurant restaurant) { this.favourite.getRestaurants().add(restaurant); }

    public void removeFavourite(Restaurant restaurant) { this.favourite.getRestaurants().remove(restaurant); }

}
