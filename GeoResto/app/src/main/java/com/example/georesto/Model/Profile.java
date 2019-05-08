package com.example.georesto.Model;

import java.util.ArrayList;

public class Profile {
    private String username, password;
    private String firstName, lastName, email;
    private String linkImage;
    private ArrayList<Restaurant> history;
    private ArrayList<Restaurant> favourite;

    public Profile(String username, String password, String firstName, String lastName, String email, String linkImage, ArrayList<Restaurant> history, ArrayList<Restaurant> favourite) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.linkImage = linkImage;
        this.history = history;
        this.favourite = favourite;
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

    public ArrayList<Restaurant> getHistory() { return this.history; }

    public ArrayList<Restaurant> getFavourite() { return this.favourite; }
}
