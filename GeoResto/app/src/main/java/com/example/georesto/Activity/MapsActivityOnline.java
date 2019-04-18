package com.example.georesto.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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
            profileView.getMenu().clear();
            profileView.removeHeaderView(profileView.getHeaderView(0));
            profileView.inflateMenu(R.menu.profile);
            setPersonalInformation();
            drawerMap.openDrawer(profileView);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        profileView.getMenu().clear();
        profileView.inflateMenu(R.menu.profile);
        setPersonalInformation();
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        profileView.getMenu().clear();
        switch (rightSideMenu) {
            case R.menu.history:
                profileView.inflateMenu(R.menu.history);
                break;
            case R.menu.favourite:
                profileView.inflateMenu(R.menu.favourite);
                break;
            case R.menu.comments:
                profileView.inflateMenu(R.menu.comments);
                break;
            case R.menu.new_location:
                profileView.inflateMenu(R.menu.new_location);
                break;
            default:
                profileView.inflateMenu(R.menu.profile);
                setPersonalInformation();
                break;
        }

        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.history) {
            rightSideMenu = R.menu.history;
        } else if (id == R.id.favourites) {
            rightSideMenu = R.menu.favourite;
        } else if (id == R.id.comments) {
            rightSideMenu = R.menu.comments;
        } else if (id == R.id.newLocation) {
            rightSideMenu = R.menu.new_location;
        } else if (id == R.id.logOut) {
            ProfileList.setCurrentUser(null);
            startActivity(new Intent(MapsActivityOnline.this, MapsActivityOffline.class));
        }
        openOptionsMenu();
        return true;
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
        profileView.inflateMenu(R.menu.profile);
    }
}
