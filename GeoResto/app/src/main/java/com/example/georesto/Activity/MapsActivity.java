package com.example.georesto.Activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.example.georesto.R;
import com.example.georesto.Model.Profile;
import com.example.georesto.Model.ProfileList;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public abstract class MapsActivity extends FragmentActivity implements OnMapReadyCallback, NavigationView.OnNavigationItemSelectedListener {
    // Testing Spinner
    protected final String[] paths = {"item 1", "item 2", "item 3"};

    // Navigation Things
    protected DrawerLayout drawerMap;
    protected NavigationView profileView;
    protected NavigationView searchView;
    protected int rightSideMenu = R.menu.info;
    protected int leftSideMenu = R.layout.research;

    // Search Side
    protected Spinner tagSpinner;

    // Model
    protected Profile user;

    // GoogleMaps
    protected GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        user = ProfileList.getCurrentUser();

        configureGMaps();

        configureDrawerLayout();

        configureSideViews();

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
    public abstract boolean onNavigationItemSelected(@NonNull MenuItem menuItem);

    // ----------------------
    //     CONFIGURATION
    // ----------------------

    private void configureDrawerLayout() {
        drawerMap = findViewById(R.id.drawerMaps);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerMap, findViewById(R.id.toolbar), R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerMap.addDrawerListener(toggle);
        toggle.syncState();
    }

    void configureSideViews() {
        // LeftView
        searchView = findViewById(R.id.research);
        searchView.setNavigationItemSelectedListener(this);
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
