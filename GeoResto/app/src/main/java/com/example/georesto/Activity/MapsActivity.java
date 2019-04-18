package com.example.georesto.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.example.georesto.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, NavigationView.OnNavigationItemSelectedListener {

    private static final String[] paths = {"item 1", "item 2", "item 3"};
    private DrawerLayout drawerMap;
    private NavigationView searchView;
    private Spinner spinner;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        findViewById(R.id.accessProfile).setVisibility(View.GONE);
        findViewById(R.id.usernameProfile).setVisibility(View.GONE);
        findViewById(R.id.home_button).setVisibility(View.GONE);

        drawerMap = (DrawerLayout) findViewById(R.id.drawerMaps);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerMap, findViewById(R.id.toolbar), R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerMap.addDrawerListener(toggle);
        toggle.syncState();


        Button connect = (Button) findViewById(R.id.accessToLogin);
        connect.setOnClickListener(new View.OnClickListener() {
            public void onClick(View connect) {
                startActivity(new Intent(MapsActivity.this, LoginActivity.class));
            }
        });

        Button register = (Button) findViewById(R.id.accessToRegister);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MapsActivity.this, RegisterActivity.class));
            }
        });

        Button accessToRegister = (Button) findViewById(R.id.accessToRegister);
        accessToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MapsActivity.this, RegisterActivity.class));
            }
        });

        searchView = findViewById(R.id.research);

        NavigationView profileView = (NavigationView) findViewById(R.id.profileNav);
        profileView.setNavigationItemSelectedListener(this);

        ImageButton logo = (ImageButton) findViewById(R.id.logo);

        logo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View info) {
                profileView.getMenu().clear();
                profileView.removeHeaderView(profileView.getHeaderView(0));
                profileView.inflateMenu(R.menu.info);
                profileView.inflateHeaderView(R.layout.info_header);
                drawerMap.openDrawer(profileView);
            }
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, paths);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        toolbar.setNavigationOnClickListener(v -> {
            if (!drawerMap.isDrawerOpen(searchView)) {
                drawerMap.openDrawer(searchView);

                spinner = findViewById(R.id.tagSpinner);
                spinner.setAdapter(adapter);
            } else {
                drawerMap.closeDrawer(searchView);
            }
        });


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


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        return false;
    }
}
