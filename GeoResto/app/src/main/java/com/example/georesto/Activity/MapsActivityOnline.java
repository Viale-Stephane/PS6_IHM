package com.example.georesto.Activity;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;


import com.example.georesto.Model.MapsActivityOnlineModel;
import com.example.georesto.ProfileList;
import com.example.georesto.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapsActivityOnline extends FragmentActivity implements OnMapReadyCallback, NavigationView.OnNavigationItemSelectedListener {
    private MapsActivityOnlineModel model = new MapsActivityOnlineModel();
    private GoogleMap mMap;
    private NavigationView profileView, searchView;
    private int menuToChooseRightSide = R.menu.profile;
    private int menuToChooseLeftSide = R.menu.research;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        findViewById(R.id.connect).setVisibility(View.GONE);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawerMaps);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, findViewById(R.id.toolbar), R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        profileView = (NavigationView) findViewById(R.id.profileNav);
        profileView.setNavigationItemSelectedListener(this);
        profileView.getMenu().clear();
        profileView.inflateMenu(R.menu.profile);
        model.setPersonalInformation(profileView.getMenu().findItem(R.id.username), profileView.getMenu().findItem(R.id.mail), findViewById(R.id.usernameProfile));

        searchView = (NavigationView) findViewById(R.id.research);
        searchView.setNavigationItemSelectedListener(this);
        searchView.getMenu().clear();
        searchView.inflateMenu(R.menu.research);

        ImageButton profileButton = findViewById(R.id.accessProfile);
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!drawer.isDrawerOpen(profileView))
                    drawer.openDrawer(profileView);
                else
                    drawer.closeDrawer(profileView);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        profileView.getMenu().clear();
        profileView.inflateMenu(R.menu.profile);
        model.setPersonalInformation(profileView.getMenu().findItem(R.id.username), profileView.getMenu().findItem(R.id.mail),findViewById(R.id.usernameProfile));
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        profileView.getMenu().clear();
        switch(menuToChooseRightSide){
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
                model.setPersonalInformation(profileView.getMenu().findItem(R.id.username), profileView.getMenu().findItem(R.id.mail), findViewById(R.id.usernameProfile));
                break;
        }

        return true;
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.history) {
            menuToChooseRightSide = R.menu.history;
        } else if (id == R.id.favourites) {
            menuToChooseRightSide = R.menu.favourite;
        } else if (id == R.id.comments) {
            menuToChooseRightSide = R.menu.comments;
        } else if (id == R.id.newLocation) {
            menuToChooseRightSide = R.menu.new_location;
        } else if (id == R.id.logOut) {
            ProfileList.setCurrentUser(null);
            startActivity(new Intent(MapsActivityOnline.this, MapsActivity.class));
        }
        openOptionsMenu();
        return true;
    }




    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-134, -47);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}
