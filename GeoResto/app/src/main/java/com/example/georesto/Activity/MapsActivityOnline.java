package com.example.georesto.Activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.georesto.Model.NewLocationModel;
import com.example.georesto.Model.NewLocationScheduleModel;
import com.example.georesto.Model.PopUpTimePickerModel;
import com.example.georesto.Model.ProfileModel;
import com.example.georesto.Model.Restaurant;
import com.example.georesto.Model.RestaurantList;
import com.example.georesto.Model.Tag;
import com.example.georesto.R;
import com.example.georesto.Model.ProfileList;
import com.google.android.gms.maps.model.LatLng;

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
            drawerMap.closeDrawer(searchView);
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
            Restaurant newRestaurant = new Restaurant(newLocationModel.getNameOfTheLocation(),newLocationModel.isARestaurant(),newLocationModel.getAdressOfTheLocation(), newLocationModel.getWebsiteOfTheLocation(),newLocationModel.getPhoneNumberOfTheLocation(),null,newLocationModel.getRatingOfTheLocation(),newLocationModel.getPriceOfTheLocation(),newLocationModel.getDistanceOfTheLocation(),newLocationModel.getCurrentFilters(),new LatLng(0,0));
            this.newLocationScheduleActions(newRestaurant);
        });
    }

    @SuppressLint("ClickableViewAccessibility")
    public void newLocationScheduleActions(Restaurant restaurant) {
        NewLocationScheduleModel newLocationScheduleModel = new NewLocationScheduleModel(profileView);
        newLocationScheduleModel.getMonday().setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                MapsActivityOnline.this.popUpTimePickerActions(newLocationScheduleModel.getDays());
                return true;
            }
        });
        newLocationScheduleModel.getTuesday().setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                MapsActivityOnline.this.popUpTimePickerActions(newLocationScheduleModel.getDays());
                return true;
            }
        });

        newLocationScheduleModel.getWednesday().setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                MapsActivityOnline.this.popUpTimePickerActions(newLocationScheduleModel.getDays());
                return true;
            }
        });

        newLocationScheduleModel.getThursday().setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                MapsActivityOnline.this.popUpTimePickerActions(newLocationScheduleModel.getDays());
                return true;
            }
        });

        newLocationScheduleModel.getFriday().setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                MapsActivityOnline.this.popUpTimePickerActions(newLocationScheduleModel.getDays());
                return true;
            }
        });

        newLocationScheduleModel.getSaturday().setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                MapsActivityOnline.this.popUpTimePickerActions(newLocationScheduleModel.getDays());
                return true;
            }
        });

        newLocationScheduleModel.getSunday().setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                MapsActivityOnline.this.popUpTimePickerActions(newLocationScheduleModel.getDays());
                return true;
            }
        });

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

    public void popUpTimePickerActions(EditText[] days) {
        LayoutInflater li = LayoutInflater.from(this);
        View view = li.inflate(R.layout.time_wheel_view, null);

        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this, R.style.AppTheme);
        alertDialogBuilder.setView(view);
        AlertDialog alertDialogCongratulations = alertDialogBuilder.create();
        alertDialogCongratulations.show();
        alertDialogCongratulations.getWindow().setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        PopUpTimePickerModel popUpTimePickerModel = new PopUpTimePickerModel(view);
        popUpTimePickerModel.getCancel().setOnClickListener(_v -> {
            if(popUpTimePickerModel.isEndingSchedule() == false)
                alertDialogCongratulations.cancel();
            else {
                popUpTimePickerModel.cancelEndingSchedule();
                popUpTimePickerModel.setSchedule("Horaire d'ouverture");
            }
        });

        popUpTimePickerModel.getNext().setOnClickListener(_v -> {
            System.out.println(popUpTimePickerModel.isEndingSchedule());
            if(popUpTimePickerModel.isEndingSchedule()){
                popUpTimePickerModel.takeTime();
                days[0].setText(popUpTimePickerModel.schedulize());
                //set horaires
                alertDialogCongratulations.cancel();
            }else {
                popUpTimePickerModel.takeTime();
                popUpTimePickerModel.setSchedule("Horaire de fermeture");
            }
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
