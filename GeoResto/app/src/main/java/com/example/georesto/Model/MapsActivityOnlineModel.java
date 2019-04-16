package com.example.georesto.Model;

import android.view.MenuItem;
import android.widget.TextView;

public class MapsActivityOnlineModel {

    public void setPersonalInformation(MenuItem username, MenuItem mail, TextView usernameProfile) {
        username.setTitle(ProfileList.getCurrentUser().getUsername());
        mail.setTitle(ProfileList.getCurrentUser().getEmail());
        usernameProfile.setText(ProfileList.getCurrentUser().getUsername());
    }
}
