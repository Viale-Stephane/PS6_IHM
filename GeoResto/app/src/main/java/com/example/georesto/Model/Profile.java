package com.example.georesto.Model;

import android.media.Image;

public class Profile {
    private String username, password;
    private String firstName, lastName, email;
    private Image profileImage;

    public Profile(String username, String password, String firstName, String lastName, String email, Image profileImage) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.profileImage = profileImage;
    }

    public String getUsername() {
        return this.username;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }
}
