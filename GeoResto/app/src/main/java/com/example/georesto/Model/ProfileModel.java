package com.example.georesto.Model;

import android.support.design.widget.NavigationView;
import android.view.View;
import android.widget.Button;

import com.example.georesto.R;

public class ProfileModel {
    private Button history;
    private Button favourites;
    private Button comments;
    private Button newLocation;
    private Button logOut;

    public ProfileModel(NavigationView profileView) {
        View currentHeader = profileView.getHeaderView(0);
        this.history = currentHeader.findViewById(R.id.historyButton);
        this.favourites = currentHeader.findViewById(R.id.favouritesButton);
        this.comments = currentHeader.findViewById(R.id.commentsButton);
        this.newLocation = currentHeader.findViewById(R.id.newLocationButton);
        this.logOut = currentHeader.findViewById(R.id.logOutButton);
    }

    public Button getHistoryButton() {
        return this.history;
    }

    public Button getFavouritesButton() {
        return favourites;
    }

    public Button getCommentsButton() {
        return comments;
    }

    public Button getNewLocationButton() {
        return newLocation;
    }

    public Button getLogOutButton() {
        return logOut;
    }
}
