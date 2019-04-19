package com.example.georesto.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.georesto.Model.Restaurant;
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
            this.profileActions();
        });
    }

    public void profileActions() {
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
            newLocationActions(null);
        });

        logOut.setOnClickListener(v -> {
            profileView.removeHeaderView(profileView.getHeaderView(0));
            rightSideMenu = R.layout.info;
            ProfileList.setCurrentUser(null);
            startActivity(new Intent(MapsActivityOnline.this, MapsActivityOffline.class));
        });
    }

    public void newLocationActions(Restaurant restaurant) {
        View currentHeader = profileView.getHeaderView(0);
        ToggleButton restaurantButton = currentHeader.findViewById(R.id.restaurantButton);
        ToggleButton commerceButton = currentHeader.findViewById(R.id.commerceButton);
        EditText nameLocation = currentHeader.findViewById(R.id.editTextNameLocation);
        RatingBar ratingBar = currentHeader.findViewById(R.id.ratingBar);
        EditText adress = currentHeader.findViewById(R.id.editTextAdress);
        EditText website = currentHeader.findViewById(R.id.editTextWebsite);
        EditText phoneNumber = currentHeader.findViewById(R.id.editTextPhoneNumber);
        Spinner tagList = currentHeader.findViewById(R.id.tagSpinner);
        TextView price = currentHeader.findViewById(R.id.price);
        SeekBar seekBarPrice = currentHeader.findViewById(R.id.seekBarPrice);
        TextView distance = currentHeader.findViewById(R.id.distance);
        SeekBar seekBarDistance = currentHeader.findViewById(R.id.seekBarDistance);
        Button cancel = currentHeader.findViewById(R.id.cancelButton);
        Button next = currentHeader.findViewById(R.id.nextButton);
        if(restaurant!= null) {
            System.out.println(restaurant.getGrade());
            restaurantButton.setChecked(restaurant.isKindRestaurant());
            commerceButton.setChecked(!restaurant.isKindRestaurant());
            nameLocation.setText(restaurant.getName());
            ratingBar.setRating(((float) restaurant.getGrade()));
            adress.setText(restaurant.getAdress());
            website.setText(restaurant.getWebsite());
            phoneNumber.setText(restaurant.getPhoneNumber());
            // tagList
            price.setText("Prix : " + restaurant.getPrice() + " €");
            seekBarPrice.setProgress((int) restaurant.getPrice());
            distance.setText("Distance : " + restaurant.getDistance() + " km");
            seekBarDistance.setProgress((int) restaurant.getDistance());
        }

        cancel.setOnClickListener(v -> {
            profileView.removeHeaderView(profileView.getHeaderView(0));
            profileView.inflateHeaderView(R.layout.profile);
            rightSideMenu = R.layout.profile;
            profileActions();
        });

        next.setOnClickListener(v -> {
            profileView.removeHeaderView(profileView.getHeaderView(0));
            profileView.inflateHeaderView(R.layout.new_location_schedule);
            rightSideMenu = R.layout.new_location_schedule;
            Restaurant newRestaurant = new Restaurant(nameLocation.getText().toString(),restaurantButton.isActivated(),adress.getText().toString(), website.getText().toString(),phoneNumber.getText().toString(),null,ratingBar.getRating(),seekBarPrice.getProgress(),seekBarDistance.getProgress(),null);
            newLocationScheduleActions(newRestaurant);
        });
    }

    public void newLocationScheduleActions(Restaurant restaurant) {
        View currentHeader = profileView.getHeaderView(0);
        Button cancel = currentHeader.findViewById(R.id.cancelButton);
        cancel.setOnClickListener(v -> {
            profileView.removeHeaderView(profileView.getHeaderView(0));
            profileView.inflateHeaderView(R.layout.new_location);
            rightSideMenu = R.layout.new_location;
            newLocationActions(restaurant);
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
        this.profileActions();
    }
}
