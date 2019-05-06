package com.example.georesto.Activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import com.example.georesto.View.MyAdapter;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

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
            displayHistory();
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
            newLocationActions(null,new LatLng(0,0));
        });

        profileModel.getLogOutButton().setOnClickListener(v -> {
            profileView.removeHeaderView(profileView.getHeaderView(0));
            rightSideMenu = R.layout.info;
            ProfileList.setCurrentUser(null);
            startActivity(new Intent(MapsActivityOnline.this, MapsActivityOffline.class));
        });
    }

    public void displayHistory() {
        RecyclerView rv = findViewById(R.id.history);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new MyAdapter(ProfileList.getCurrentUser().getHistorique()));
    }

    public void newLocationActions(Restaurant restaurant,LatLng position) {
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
            Restaurant newRestaurant;
            if(restaurant !=null) {
                newRestaurant = new Restaurant(newLocationModel.getNameOfTheLocation(), newLocationModel.isARestaurant(), newLocationModel.getAdressOfTheLocation(), newLocationModel.getWebsiteOfTheLocation(), newLocationModel.getPhoneNumberOfTheLocation(), restaurant.getCompleteSchedule(), newLocationModel.getRatingOfTheLocation(), newLocationModel.getPriceOfTheLocation(), newLocationModel.getDistanceOfTheLocation(), newLocationModel.getCurrentFilters(), position);
            } else {
                newRestaurant = new Restaurant(newLocationModel.getNameOfTheLocation(), newLocationModel.isARestaurant(), newLocationModel.getAdressOfTheLocation(), newLocationModel.getWebsiteOfTheLocation(), newLocationModel.getPhoneNumberOfTheLocation(), null, newLocationModel.getRatingOfTheLocation(), newLocationModel.getPriceOfTheLocation(), newLocationModel.getDistanceOfTheLocation(), newLocationModel.getCurrentFilters(), position);
                this.newLocationScheduleActions(newRestaurant,position);
            }
        });
    }

    public void newLocationScheduleActions(Restaurant restaurant,LatLng position) {
        NewLocationScheduleModel newLocationScheduleModel = new NewLocationScheduleModel(profileView);
        newLocationScheduleModel.init(restaurant);
        newLocationScheduleModel.getMonday().setOnClickListener(v -> {
            this.popUpTimePickerActions(newLocationScheduleModel.getDays(),0);
        });

        newLocationScheduleModel.getTuesday().setOnClickListener(v -> {
                this.popUpTimePickerActions(newLocationScheduleModel.getDays(),1);
        });

        newLocationScheduleModel.getWednesday().setOnClickListener(v -> {
                this.popUpTimePickerActions(newLocationScheduleModel.getDays(),2);
        });

        newLocationScheduleModel.getThursday().setOnClickListener(v -> {
                this.popUpTimePickerActions(newLocationScheduleModel.getDays(),3);
        });

        newLocationScheduleModel.getFriday().setOnClickListener(v -> {
                this.popUpTimePickerActions(newLocationScheduleModel.getDays(),4);
        });

        newLocationScheduleModel.getSaturday().setOnClickListener(v -> {
                MapsActivityOnline.this.popUpTimePickerActions(newLocationScheduleModel.getDays(),5);
        });

        newLocationScheduleModel.getSunday().setOnClickListener(v -> {
                MapsActivityOnline.this.popUpTimePickerActions(newLocationScheduleModel.getDays(),6);
        });

        newLocationScheduleModel.getCancelButton().setOnClickListener(v -> {
            restaurant.addSchedule(newLocationScheduleModel.getSchedule());
            profileView.removeHeaderView(profileView.getHeaderView(0));
            profileView.inflateHeaderView(R.layout.new_location);
            rightSideMenu = R.layout.new_location;
            this.newLocationActions(restaurant,position);
        });

        newLocationScheduleModel.getValidateButton().setOnClickListener(v -> {
            restaurant.addSchedule(newLocationScheduleModel.getSchedule());
            restaurantList.addRestaurant(restaurant);
            profileView.removeHeaderView(profileView.getHeaderView(0));
            profileView.inflateHeaderView(R.layout.profile);
            this.profileActions();
        });
    }

    public void popUpTimePickerActions(EditText[] days, int dayNumber) {
        LayoutInflater li = LayoutInflater.from(this);
        View view = li.inflate(R.layout.time_wheel_view, null);

        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this, R.style.AppTheme);
        alertDialogBuilder.setView(view);
        AlertDialog alertDialogCongratulations = alertDialogBuilder.create();
        alertDialogCongratulations.getWindow().setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        alertDialogCongratulations.show();
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
            if(popUpTimePickerModel.isEndingSchedule()){
                popUpTimePickerModel.takeTime();
                days[dayNumber].setText(popUpTimePickerModel.schedulize());
                alertDialogCongratulations.cancel();
            }else {
                popUpTimePickerModel.takeTime();
                popUpTimePickerModel.setSchedule("Horaire de fermeture");
            }
        });
        popUpTimePickerModel.clickOnDayButton();
        popUpTimePickerModel.clickOnFullWeekButton();
        popUpTimePickerModel.clickOnWeekButton();
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

    // ---------------------------------
    //          MAPS
    // ---------------------------------

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        googleMap.getUiSettings().setCompassEnabled(true);
        googleMap.setMyLocationEnabled(true);
        googleMap.setPadding(0, 120, 0, 0);
        googleMap.getUiSettings().setZoomControlsEnabled(true);

        Log.d(TAG, "onMapReady: maps is ready");

        this.restaurantList.sampleRestaurant();
        for (Restaurant resto : this.restaurantList.getRestaurants()
        ) {
            resto.setMarkerOnMap(mMap);
        }

        if (mLocationPermissionGranted) {
            getDeviceLocation();
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
        }

        googleMap.setOnMapLongClickListener(point -> {
            profileView.removeHeaderView(profileView.getHeaderView(0));
            profileView.inflateHeaderView(R.layout.new_location);
            rightSideMenu = R.layout.new_location;
            mMap.addMarker(new MarkerOptions()
                            .position(point)
                            .title("df"));
            drawerMap.openDrawer(profileView);
            newLocationActions(null,point);
    });
    }
}
