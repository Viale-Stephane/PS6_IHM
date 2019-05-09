package com.example.georesto.Activity;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.georesto.Model.Profile;
import com.example.georesto.Model.ProfileList;
import com.example.georesto.Model.Restaurant;
import com.example.georesto.Model.RestaurantList;
import com.example.georesto.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.Task;


public abstract class MapsActivity extends FragmentActivity implements OnMapReadyCallback, NavigationView.OnNavigationItemSelectedListener {

    static final String TAG = "MapsActivity";
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;
    private static final float DEFAULT_ZOOM = 15.0f;
    //mocks
    public static RestaurantList restaurantList = new RestaurantList();
    public static ProfileList profileList = new ProfileList();
    // Testing Spinner
    protected final String[] paths = {"item 1", "item 2", "item 3"};
    // Navigation Things
    protected DrawerLayout drawerMap;
    protected NavigationView profileView;
    protected NavigationView searchView;
    // Search Side
    protected Spinner tagSpinner;
    // Model
    protected Profile user;
    //variables
    protected boolean init = true;
    // GoogleMaps
    protected GoogleMap mMap;


    Boolean mLocationPermissionGranted = false;
    LatLng userLocation;

    private FusedLocationProviderClient mFusedLocationProviderClient;

    public boolean isServicesOK() {
        Log.d(TAG, "isServicesOK: checking google services version");

        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(MapsActivity.this);

        if (available == ConnectionResult.SUCCESS) {
            //everything is fine and the user can make map requests
            Log.d(TAG, "isServicesOK: Google Play Services is working");
            return true;
        } else if (GoogleApiAvailability.getInstance().isUserResolvableError(available)) {
            //an error occured but we can resolve it
            Log.d(TAG, "isServicesOK: an error occured but we can fix it");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(MapsActivity.this, available, 4);
            dialog.show();
        } else {
            Toast.makeText(this, "You can't make map requests", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        if (init) {
            restaurantList.sampleRestaurant(this);
            profileList.instantiateProfiles(restaurantList);
            init = false;
        }

        user = ProfileList.getCurrentUser();

        if (isServicesOK()) {
            getLocationPermissions();
        }

        configureDrawerLayout();

        configureSideViews();


        ImageButton logo = findViewById(R.id.logo);
        logo.setOnClickListener(info -> {
            profileView.removeHeaderView(profileView.getHeaderView(0));
            profileView.inflateHeaderView(R.layout.info);
            drawerMap.openDrawer(profileView);
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, paths);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        toolbar.setNavigationOnClickListener(v -> {
            if (!drawerMap.isDrawerOpen(searchView)) {
                drawerMap.closeDrawer(profileView);
                drawerMap.openDrawer(searchView);
                this.configureSearchView();
                //tagSpinner = findViewById(R.id.tagSpinner);
                //tagSpinner.setAdapter(adapter);
            } else {
                drawerMap.closeDrawer(searchView);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (this.drawerMap.isDrawerOpen(profileView)) {
            this.drawerMap.closeDrawer(profileView);
        } else if (this.drawerMap.isDrawerOpen(searchView)) {
            this.drawerMap.closeDrawer(searchView);
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

    protected void configureSearchView() {
        new SearchActivity(this);
    }

    ;

    // --------------------
    //     GoogleMaps
    // --------------------
    private void getLocationPermissions() {
        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION};

        if (ContextCompat.checkSelfPermission(this.getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(this.getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                mLocationPermissionGranted = true;
                configureGMaps();
            } else {
                ActivityCompat.requestPermissions(this, permissions, LOCATION_PERMISSION_REQUEST_CODE);
            }
        } else {
            ActivityCompat.requestPermissions(this, permissions, LOCATION_PERMISSION_REQUEST_CODE);
        }
    }

    private void moveCamera(LatLng latLng, float zoom) {
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));
    }

    void getDeviceLocation() {
        Log.d(TAG, "getDeviceLocation: getting the current location");
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        String bestProvider = String.valueOf(locationManager.getBestProvider(criteria, true));
        try {
            if (mLocationPermissionGranted) {
                Task location = mFusedLocationProviderClient.getLastLocation();
                location.addOnCompleteListener(task -> {
                    if (task.isSuccessful() && task.getResult() != null) {
                        Location currentLocation = (Location) task.getResult();
                        userLocation = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
                        moveCamera(userLocation, DEFAULT_ZOOM);
                    } else {
                        locationManager.requestLocationUpdates(bestProvider, 1000, 0, new LocationListener() {
                            @Override
                            public void onLocationChanged(Location location1) {
                                double latitude = location1.getLatitude();
                                double longitude = location1.getLongitude();
                                userLocation = new LatLng(latitude, longitude);
                                Log.d(TAG, "onLocationChanged: " + latitude + "  " + longitude);
                                moveCamera(userLocation, DEFAULT_ZOOM);
                            }

                            @Override
                            public void onStatusChanged(String provider, int status, Bundle extras) {

                            }

                            @Override
                            public void onProviderEnabled(String provider) {

                            }

                            @Override
                            public void onProviderDisabled(String provider) {

                            }
                        });
                    }
                });
            }
        } catch (SecurityException e) {
            Log.d(TAG, "getDeviceLocation: Security Exception: " + e.getMessage());
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        mLocationPermissionGranted = false;
        switch (requestCode) {
            case LOCATION_PERMISSION_REQUEST_CODE: {
                if (grantResults.length > 0) {
                    for (int grantResult : grantResults) {
                        if (grantResult != PackageManager.PERMISSION_GRANTED) {
                            mLocationPermissionGranted = false;
                            return;
                        }
                    }
                    mLocationPermissionGranted = true;
                    Log.d(TAG, "getLocationPermission: granted");
                    configureGMaps();
                    //Initialize map
                }
            }
        }
    }

    private void configureGMaps() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(MapsActivity.this);
    }

    void updateMapsMarker(RestaurantList list) {
        mMap.clear();
        for (Restaurant resto : list.getRestaurants()
        ) {
            if (userLocation != null) {
                resto.setDistance(userLocation);
            }
            resto.setMarkerOnMap(mMap);
        }
    }

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
        mMap.clear();
        for (Restaurant resto : restaurantList.getRestaurants()) {
            if (userLocation != null) {
                resto.setDistance(userLocation);
            }
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

    }
}
