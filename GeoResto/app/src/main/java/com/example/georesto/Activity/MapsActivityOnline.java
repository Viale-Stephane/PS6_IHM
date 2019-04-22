package com.example.georesto.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.georesto.Model.NewLocationModel;
import com.example.georesto.Model.NewLocationScheduleModel;
import com.example.georesto.Model.ProfileModel;
import com.example.georesto.Model.Restaurant;
import com.example.georesto.Model.RestaurantList;
import com.example.georesto.Model.Tag;
import com.example.georesto.R;
import com.example.georesto.Model.ProfileList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


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
        ProfileModel profileModel = new ProfileModel(profileView);
        profileModel.getHistoryButton().setOnClickListener(v -> {
                profileView.removeHeaderView(profileView.getHeaderView(0));
                profileView.inflateHeaderView(R.layout.history);
                rightSideMenu = R.layout.history;
        });

        profileModel.getFavouritesButton().setOnClickListener(v -> {
            profileView.removeHeaderView(profileView.getHeaderView(0));
            profileView.inflateHeaderView(R.layout.favourites);
            rightSideMenu = R.layout.favourites;
        });

        profileModel.getCommentsButton().setOnClickListener(v -> {
            profileView.removeHeaderView(profileView.getHeaderView(0));
            profileView.inflateHeaderView(R.layout.comments);
            rightSideMenu = R.layout.comments;
        });

        profileModel.getNewLocationButton().setOnClickListener(v -> {
            profileView.removeHeaderView(profileView.getHeaderView(0));
            profileView.inflateHeaderView(R.layout.new_location);
            rightSideMenu = R.layout.new_location;
            newLocationActions(null);
        });

        profileModel.getLogOutButton().setOnClickListener(v -> {
            profileView.removeHeaderView(profileView.getHeaderView(0));
            rightSideMenu = R.layout.info;
            ProfileList.setCurrentUser(null);
            startActivity(new Intent(MapsActivityOnline.this, MapsActivityOffline.class));
        });
    }

    public void newLocationActions(Restaurant restaurant) {
        NewLocationModel newLocationModel = new NewLocationModel(profileView);
        newLocationModel.init(restaurant);

        final ArrayAdapter<Tag> spinnerArrayAdapter = new ArrayAdapter<Tag>(this,R.layout.support_simple_spinner_dropdown_item,newLocationModel.getTags());
        spinnerArrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        newLocationModel.setAdapterToTagList(spinnerArrayAdapter);
        newLocationModel.clickOnRestaurantButton();
        newLocationModel.clickOnCommerceButton();
        newLocationModel.selectTagInTagList();
        newLocationModel.onClickOnSeekBarPrice();
        newLocationModel.onClickOnSeekBarDistance();

        newLocationModel.getCancel().setOnClickListener(v -> {
            profileView.removeHeaderView(profileView.getHeaderView(0));
            profileView.inflateHeaderView(R.layout.profile);
            rightSideMenu = R.layout.profile;
            profileActions();
        });

        newLocationModel.getNext().setOnClickListener(v -> {
            profileView.removeHeaderView(profileView.getHeaderView(0));
            profileView.inflateHeaderView(R.layout.new_location_schedule);
            rightSideMenu = R.layout.new_location_schedule;
            Restaurant newRestaurant = new Restaurant(newLocationModel.getNameOfTheLocation(),newLocationModel.isARestaurant(),newLocationModel.getAdressOfTheLocation(), newLocationModel.getWebsiteOfTheLocation(),newLocationModel.getPhoneNumberOfTheLocation(),null,newLocationModel.getRatingOfTheLocation(),newLocationModel.getPriceOfTheLocation(),newLocationModel.getDistanceOfTheLocation(),newLocationModel.getCurrentFilters());
            this.newLocationScheduleActions(newRestaurant);
        });
    }

    public void newLocationScheduleActions(Restaurant restaurant) {
        NewLocationScheduleModel newLocationScheduleModel = new NewLocationScheduleModel(profileView);

        newLocationScheduleModel.getCancelButton().setOnClickListener(v -> {
            profileView.removeHeaderView(profileView.getHeaderView(0));
            profileView.inflateHeaderView(R.layout.new_location);
            rightSideMenu = R.layout.new_location;
            this.newLocationActions(restaurant);
        });

        newLocationScheduleModel.getValidateButton().setOnClickListener(v -> {
            restaurant.addSchedule(newLocationScheduleModel.getSchedule());
            restaurantList.addRestaurant(restaurant);
            profileView.removeHeaderView(profileView.getHeaderView(0));
            profileView.inflateHeaderView(R.layout.profile);
            this.profileActions();
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
