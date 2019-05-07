package com.example.georesto.Activity;


import android.Manifest;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.UserHandle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.georesto.Model.NewLocationModel;
import com.example.georesto.Model.NewLocationScheduleModel;
import com.example.georesto.Model.PopUpTimePickerModel;
import com.example.georesto.Model.ProfileList;
import com.example.georesto.Model.ProfileModel;
import com.example.georesto.Model.Restaurant;
import com.example.georesto.Model.Tag;
import com.example.georesto.R;
import com.example.georesto.View.MyAdapter;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Locale;

import static android.media.MediaRecorder.VideoSource.CAMERA;


public class MapsActivityOnline extends MapsActivity {

    private static final int GALLERY = 0;
    private static final int CAMERA = 1;
    Boolean isAddingLocation = false;
    Boolean isAddingPosition = false;
    LatLng position = new LatLng(0,0);
    List<Address> address = null;
    NewLocationModel newLocationModel;
    Geocoder geocoder = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        geocoder = new Geocoder(this, Locale.getDefault());
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
            position = new LatLng(0,0);
            address = null;
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

    public void displayHistory() {
        RecyclerView rv = findViewById(R.id.history);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new MyAdapter(ProfileList.getCurrentUser().getHistory()));
    }


    private void showPictureDialog(){
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(this);
        pictureDialog.setTitle("Choisissez l'action que vous souhaitez réaliser");
        String[] pictureDialogItems = {
                "Choissisez une photo depuis votre galerie",
                "Prennez une nouvelle photo avec votre caméra" };
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                choosePhotoFromGallery();
                                break;
                            case 1:
                                takePhotoFromCamera();
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }

    public void choosePhotoFromGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(galleryIntent, GALLERY);
    }

    private void takePhotoFromCamera() {
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(takePictureIntent, CAMERA);
            }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == this.RESULT_CANCELED) {
            return;
        }
        if (requestCode == GALLERY) {
            if (data != null) {
                Uri contentURI = data.getData();
                ImageView restaurantPicture = findViewById(R.id.restaurantPicture);
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
                    restaurantPicture.setImageBitmap(bitmap);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        } else if (requestCode == CAMERA) {
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            ImageView restaurantPicture = findViewById(R.id.restaurantPicture);
            restaurantPicture.setImageBitmap(thumbnail);
        }
    }

    public void newLocationActions(Restaurant restaurant) {
        newLocationModel = new NewLocationModel(profileView);
        newLocationModel.init(restaurant);
        isAddingLocation = true;

        newLocationModel.getPictureLayout().setOnClickListener(v -> {
            this.showPictureDialog();
        });

        final ArrayAdapter<Tag> spinnerArrayAdapter = new ArrayAdapter<Tag>(this, R.layout.support_simple_spinner_dropdown_item, newLocationModel.getTags());
        spinnerArrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        newLocationModel.setAdapterToTagList(spinnerArrayAdapter);
        newLocationModel.clickOnRestaurantButton();
        newLocationModel.clickOnCommerceButton();
        newLocationModel.selectTagInTagList();
        newLocationModel.onClickOnSeekBarPrice();

        newLocationModel.getCancel().setOnClickListener(v -> {
            profileView.removeHeaderView(profileView.getHeaderView(0));
            profileView.inflateHeaderView(R.layout.profile);
            rightSideMenu = R.layout.profile;
            profileActions();
            isAddingLocation = false;
        });

       if (address != null){
            newLocationModel.getAdressTextField().setText(address.get(0).getAddressLine(0));
       } else {
           newLocationModel.getAdressTextField().setText("");
       }

        newLocationModel.getPosition().setOnClickListener(v -> {
            isAddingPosition = true;
            drawerMap.closeDrawer(profileView);
            newLocationModel.getPosition().setText("Changer");
        });

        if(position.latitude == 0 && position.longitude == 0) {
            newLocationModel.getPosition().setText("Choisir");
        }  else {
            newLocationModel.getPosition().setText("Changer");
        }

        newLocationModel.getNext().setOnClickListener(v -> {
            profileView.removeHeaderView(profileView.getHeaderView(0));
            profileView.inflateHeaderView(R.layout.new_location_schedule);
            rightSideMenu = R.layout.new_location_schedule;
            String[] schedule;
            if (restaurant != null) {
                schedule = restaurant.getCompleteSchedule();
            } else {
                schedule = null;
            }
            BitmapDrawable drawable =((BitmapDrawable)newLocationModel.getRestaurantPicture().getDrawable());
            Bitmap image;
            if(drawable != null) {
                image = drawable.getBitmap();
            } else {
                image = null;
            }
            Restaurant newRestaurant = new Restaurant(newLocationModel.getNameOfTheLocation(), newLocationModel.isARestaurant(), newLocationModel.getAdressTextField().getText().toString(), newLocationModel.getWebsiteOfTheLocation(), newLocationModel.getPhoneNumberOfTheLocation(), schedule, newLocationModel.getRatingOfTheLocation(), newLocationModel.getPriceOfTheLocation(), newLocationModel.getCurrentFilters(), position, image);
            this.newLocationScheduleActions(newRestaurant, position);
        });
    }

    public void newLocationScheduleActions(Restaurant restaurant, LatLng position) {
        NewLocationScheduleModel newLocationScheduleModel = new NewLocationScheduleModel(profileView);
        newLocationScheduleModel.init(restaurant);
        newLocationScheduleModel.getMonday().setOnClickListener(v -> {
            this.popUpTimePickerActions(newLocationScheduleModel.getDays(), 0);
        });

        newLocationScheduleModel.getTuesday().setOnClickListener(v -> {
            this.popUpTimePickerActions(newLocationScheduleModel.getDays(), 1);
        });

        newLocationScheduleModel.getWednesday().setOnClickListener(v -> {
            this.popUpTimePickerActions(newLocationScheduleModel.getDays(), 2);
        });

        newLocationScheduleModel.getThursday().setOnClickListener(v -> {
            this.popUpTimePickerActions(newLocationScheduleModel.getDays(), 3);
        });

        newLocationScheduleModel.getFriday().setOnClickListener(v -> {
            this.popUpTimePickerActions(newLocationScheduleModel.getDays(), 4);
        });

        newLocationScheduleModel.getSaturday().setOnClickListener(v -> {
            MapsActivityOnline.this.popUpTimePickerActions(newLocationScheduleModel.getDays(), 5);
        });

        newLocationScheduleModel.getSunday().setOnClickListener(v -> {
            MapsActivityOnline.this.popUpTimePickerActions(newLocationScheduleModel.getDays(), 6);
        });

        newLocationScheduleModel.getCancelButton().setOnClickListener(v -> {
            restaurant.addSchedule(newLocationScheduleModel.getSchedule());
            profileView.removeHeaderView(profileView.getHeaderView(0));
            profileView.inflateHeaderView(R.layout.new_location);
            rightSideMenu = R.layout.new_location;
            this.newLocationActions(restaurant);
        });

        newLocationScheduleModel.getValidateButton().setOnClickListener(v -> {
            restaurant.addSchedule(newLocationScheduleModel.getSchedule());
            restaurantList.addRestaurant(restaurant);
            updateMapsMarker(restaurantList);
            profileView.removeHeaderView(profileView.getHeaderView(0));
            profileView.inflateHeaderView(R.layout.profile);
            isAddingLocation = false;
            this.profileActions();
        });
    }

    public void popUpTimePickerActions(EditText[] days, int dayNumber) {
        LayoutInflater li = LayoutInflater.from(this);
        View view = li.inflate(R.layout.time_wheel_view, null);

        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setView(view);
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.getWindow().setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        alertDialog.show();
        PopUpTimePickerModel popUpTimePickerModel = new PopUpTimePickerModel(view);
        popUpTimePickerModel.getCancel().setOnClickListener(_v -> {
            if (popUpTimePickerModel.isEndingSchedule() == false)
                alertDialog.cancel();
            else {
                popUpTimePickerModel.cancelEndingSchedule();
                popUpTimePickerModel.setSchedule("Horaire d'ouverture");
            }
        });

        popUpTimePickerModel.getNext().setOnClickListener(_v -> {
            if (popUpTimePickerModel.isEndingSchedule()) {
                popUpTimePickerModel.takeTime();
                String toggleButtonId = popUpTimePickerModel.whichToggleButtonIsChecked();
                if (toggleButtonId.equals("day")) {
                    days[dayNumber].setText(popUpTimePickerModel.schedulize());
                } else if (toggleButtonId.equals("fullWeek")) {
                    for (int i = 0; i < 7; i++) {
                        days[i].setText(popUpTimePickerModel.schedulize());
                    }
                } else if (toggleButtonId.equals("week")) {
                    for (int i = 0; i < 5; i++) {
                        days[i].setText(popUpTimePickerModel.schedulize());
                    }
                }
                alertDialog.cancel();
            } else {
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
            if(!isAddingLocation) {
                profileView.removeHeaderView(profileView.getHeaderView(0));
                profileView.inflateHeaderView(R.layout.new_location);
                rightSideMenu = R.layout.new_location;
                this.position = point;
                try {
                    this.address = geocoder.getFromLocation(point.latitude,point.longitude,1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                newLocationActions(null);
                drawerMap.openDrawer(profileView);
            }
            if(isAddingPosition) {
                try {
                    this.address = geocoder.getFromLocation(point.latitude,point.longitude,1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                this.position = point;
                newLocationModel.getAdressTextField().setText(address.get(0).getAddressLine(0));
                drawerMap.openDrawer(profileView);
            }
        });
    }
}
