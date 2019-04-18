package com.example.georesto.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ImageView;

import com.example.georesto.Model.*;
import com.example.georesto.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapsActivityOnline extends FragmentActivity implements OnMapReadyCallback, NavigationView.OnNavigationItemSelectedListener {
    private static final String[] paths = {"item 1", "item 2", "item 3"};

    private DrawerLayout drawerMap;
    private NavigationView profileView;
    private NavigationView searchView;

    private Profile user;

    private SearchView search;
    private Spinner tagSpinner;

    private int rightSideMenu = R.menu.profile;
    private int leftSideMenu = R.layout.research;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        user = ProfileList.getCurrentUser();

        findViewById(R.id.accessToLogin).setVisibility(View.GONE);
        findViewById(R.id.accessToRegister).setVisibility(View.GONE);
        findViewById(R.id.home_button).setVisibility(View.GONE);

        //findViewById(R.id.accessProfile).setBackgroundResource(user.);

        //TextView userProfile = findViewById(R.id.usernameProfile);
        //userProfile.setContentDescription(user.getUsername());

        configureGMaps();

        configureDrawerLayout();

        configureSideViews();

        setPersonalInformation();

        ImageButton profileButton = findViewById(R.id.accessProfile);
        profileButton.setOnClickListener(v -> {
            /*TextView headerUsername = findViewById(R.id.usernameProfile);
            TextView headerMail = findViewById(R.id.mailProfile);
            if (!drawerMap.isDrawerOpen(profileView)) {
                drawerMap.openDrawer(profileView);
                headerUsername.setText("OUGABOUGA");
                headerMail.setText("FOAKOAZKFOZA");
            } else {
                drawerMap.closeDrawer(profileView);
                headerUsername.setText("GéoResto");
            }*/
            profileView.getMenu().clear();
            profileView.removeHeaderView(profileView.getHeaderView(0));
            profileView.inflateMenu(R.menu.profile);
            profileView.inflateHeaderView(R.layout.profile_header);
            drawerMap.openDrawer(profileView);
        });

        ImageButton logo = findViewById(R.id.logo);
        logo.setOnClickListener(info -> {
            profileView.getMenu().clear();
            profileView.removeHeaderView(profileView.getHeaderView(0));
            profileView.inflateMenu(R.menu.info);
            profileView.inflateHeaderView(R.layout.info_header);
            drawerMap.openDrawer(profileView);
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, paths);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        toolbar.setNavigationOnClickListener(v -> {
            if (!drawerMap.isDrawerOpen(searchView)) {
                drawerMap.openDrawer(searchView);

                tagSpinner = findViewById(R.id.tagSpinner);
                tagSpinner.setAdapter(adapter);
            } else {
                drawerMap.closeDrawer(searchView);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (this.drawerMap.isDrawerOpen(rightSideMenu)) {
            this.drawerMap.closeDrawer(rightSideMenu);
        } else if (this.drawerMap.isDrawerOpen(leftSideMenu)) {
            this.drawerMap.closeDrawer(leftSideMenu);
        } else {
            super.onBackPressed();
        }
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
            startActivity(new Intent(MapsActivityOnline.this, MapsActivity.class));
        }
        openOptionsMenu();
        return true;
    }

    public void setPersonalInformation() {
        MenuItem username = profileView.getMenu().findItem(R.id.username);
        MenuItem mail = profileView.getMenu().findItem(R.id.mail);
        TextView usernameProfile = findViewById(R.id.usernameProfile);
        /*ImageView avatar = (ImageView) findViewById(R.id.profileImage);
        int imageRessource = getResources().getIdentifier(user.getLinkImage(), null, this.getPackageName());

        avatar.setImageResource(imageRessource);*/
        username.setTitle(user.getUsername());
        mail.setTitle(user.getEmail());
        usernameProfile.setText(user.getUsername());
    }


    // ----------------------
    //     CONFIGURATION
    // ----------------------

    private void configureDrawerLayout() {
        drawerMap = findViewById(R.id.drawerMaps);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerMap, findViewById(R.id.toolbar), R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerMap.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void configureSideViews() {
        // RightView
        profileView = findViewById(R.id.profileNav);
        profileView.setNavigationItemSelectedListener(this);
        profileView.getMenu().clear();
        profileView.removeHeaderView(profileView.getHeaderView(0));
        profileView.inflateMenu(rightSideMenu);
        profileView.inflateHeaderView(R.layout.profile_header);

        // LeftView
        searchView = findViewById(R.id.research);
        searchView.setNavigationItemSelectedListener(this);
        //searchView.getMenu().clear();
        //searchView.inflateMenu(leftSideMenu);
    }


    // --------------------
    //     GoogleMaps
    // --------------------

    private void configureGMaps() {
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);
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
