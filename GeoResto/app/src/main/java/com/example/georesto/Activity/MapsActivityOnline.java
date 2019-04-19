package com.example.georesto.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.georesto.R;
import com.example.georesto.Model.ProfileList;


public class MapsActivityOnline extends MapsActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        findViewById(R.id.accessToLogin).setVisibility(View.GONE);
        findViewById(R.id.accessToRegister).setVisibility(View.GONE);
        findViewById(R.id.home_button).setVisibility(View.GONE);
        setPersonalInformation();

        ImageButton profileButton = findViewById(R.id.accessProfile);
        profileButton.setOnClickListener(v -> {
            profileView.removeHeaderView(profileView.getHeaderView(0));
            profileView.inflateHeaderView(R.layout.profile);
            setPersonalInformation();
            rightSideMenu = R.layout.profile;
            drawerMap.openDrawer(profileView);
            if(rightSideMenu == R.layout.profile) {
                this.profileButton();
            }
        });
    }

    public void profileButton() {
        View currentHeader = profileView.getHeaderView(0);
        Button history = currentHeader.findViewById(R.id.historyButton);
        Button favourites = currentHeader.findViewById(R.id.favouritesButton);
        Button comments = currentHeader.findViewById(R.id.commentsButton);
        Button newLocation = currentHeader.findViewById(R.id.newLocationButton);
        Button logOut = currentHeader.findViewById(R.id.logOutButton);
        history.setOnClickListener(v -> {
                profileView.removeHeaderView(profileView.getHeaderView(0));
                profileView.inflateHeaderView(R.layout.history);
                rightSideMenu = R.layout.history;
        });

        favourites.setOnClickListener(v -> {
            profileView.removeHeaderView(profileView.getHeaderView(0));
            profileView.inflateHeaderView(R.layout.favourites);
            rightSideMenu = R.layout.favourites;
        });

        comments.setOnClickListener(v -> {
            profileView.removeHeaderView(profileView.getHeaderView(0));
            profileView.inflateHeaderView(R.layout.comments);
            rightSideMenu = R.layout.comments;
        });

        newLocation.setOnClickListener(v -> {
            profileView.removeHeaderView(profileView.getHeaderView(0));
            profileView.inflateHeaderView(R.layout.new_location);
            rightSideMenu = R.layout.new_location;
        });

        logOut.setOnClickListener(v -> {
            profileView.removeHeaderView(profileView.getHeaderView(0));
            rightSideMenu = R.layout.info;
            ProfileList.setCurrentUser(null);
            startActivity(new Intent(MapsActivityOnline.this, MapsActivityOffline.class));
        });
    }

    public void setPersonalInformation() {
        TextView mailProfile = findViewById(R.id.mailProfile);
        TextView usernameProfile = findViewById(R.id.usernameProfile);

        mailProfile.setText(user.getEmail());
        usernameProfile.setText(user.getUsername());

        ImageView avatar = findViewById(R.id.accessProfile);
        int imageResource = getResources().getIdentifier(user.getLinkImage(), null, this.getPackageName());
        avatar.setBackgroundResource(imageResource);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        return false;
    }

    // ----------------------
    //     CONFIGURATION
    // ----------------------

    @Override
    void configureSideViews() {
        // LeftView
        super.configureSideViews();

        // RightView
        profileView = findViewById(R.id.profileNav);
        profileView.setNavigationItemSelectedListener(this);
        profileView.removeHeaderView(profileView.getHeaderView(0));
        profileView.inflateHeaderView(R.layout.profile);
        rightSideMenu = R.layout.profile;
        this.profileButton();
    }
}
