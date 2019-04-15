package com.example.georesto.Model;

import android.view.Menu;
import android.view.MenuItem;

import com.example.georesto.ProfileList;

public class MapsActivityOnlineModel {



    public void setPersonalInformation(MenuItem username, MenuItem mail) {
        username.setTitle(ProfileList.getCurrentUser().getUsername());
        mail.setTitle(ProfileList.getCurrentUser().getEmail());
    }
}
