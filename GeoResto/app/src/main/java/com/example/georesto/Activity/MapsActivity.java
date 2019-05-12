package com.example.georesto.Activity;

import android.Manifest;
import android.app.Dialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
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
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.tasks.Task;

import java.text.DecimalFormat;
import java.util.Objects;


public abstract class MapsActivity extends FragmentActivity implements OnMapReadyCallback, NavigationView.OnNavigationItemSelectedListener {

    public static final DecimalFormat df = new DecimalFormat("####.###");
    static final String TAG = "MapsActivity";
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;
    private static final float DEFAULT_ZOOM = 15.0f;
    //mocks
    public static RestaurantList restaurantList = new RestaurantList();
    public static RestaurantList filteredList;
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
    protected  boolean hasZoomed = false;
    // GoogleMaps
    protected GoogleMap mMap;
    // Notifications
    public static String CHANNEL_ID = "reminder";
    public static int NOTIFICATION_ID = 1221;


    Boolean mLocationPermissionGranted = false;
    LatLng userLocation;

    private FusedLocationProviderClient mFusedLocationProviderClient;
    private Toolbar toolbar;

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
        createNotificationChannel();
        setContentView(R.layout.main);

        if (init) {
            restaurantList.sampleRestaurant(this);
            profileList.instantiateProfiles(restaurantList);
            init = false;
            filteredList = restaurantList;
        }

        user = ProfileList.getCurrentUser();

        System.out.println(ProfileList.getProfiles().size());
        if (isServicesOK()) {
            getLocationPermissions();
        }

        configureDrawerLayout();

        configureSideViews();

        Thread t = new Thread() {
            @Override
            public void run() {
                while (!isInterrupted()) {
                    try {
                        Thread.sleep(1000);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if(userLocation != null && !hasZoomed) {
                                    hasZoomed = true;
                                    moveCamera(userLocation,DEFAULT_ZOOM);
                                }
                                getDeviceLocation();
                                for (Restaurant resto : restaurantList.getRestaurants()) {
                                    resto.setDistance(userLocation);
                                }
                            }
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        };

        t.start();

        ImageButton logo = findViewById(R.id.logo);
        logo.setOnClickListener(info -> {
            profileView.removeHeaderView(profileView.getHeaderView(0));
            profileView.inflateHeaderView(R.layout.info);
            drawerMap.openDrawer(profileView);
        });

        toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_search_black_24dp);
        toolbar.setNavigationOnClickListener(v -> {
            if (!drawerMap.isDrawerOpen(searchView)) {
                drawerMap.closeDrawer(profileView);
                drawerMap.openDrawer(searchView);
                filteredList = restaurantList;
                updateMapsMarker(filteredList);
                toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
                new SearchActivity(this, searchView,this);
            } else {
                drawerMap.closeDrawer(searchView);
                toolbar.setNavigationIcon(R.drawable.ic_search_black_24dp);
            }
        });
        try {
            Thread.sleep(1000);
            moveCamera(userLocation, DEFAULT_ZOOM);
        } catch (Exception e) {
        }
    }

    @Override
    public void onBackPressed() {
        if (this.drawerMap.isDrawerOpen(profileView)) {
            this.drawerMap.closeDrawer(profileView);
        } else if (this.drawerMap.isDrawerOpen(searchView)) {
            this.drawerMap.closeDrawer(searchView);
            toolbar.setNavigationIcon(R.drawable.ic_search_black_24dp);
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

    public void moveCamera(LatLng latLng, float zoom) {
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));
    }

    void getDeviceLocation() {
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
                    } else {
                        locationManager.requestLocationUpdates(bestProvider, 1000, 0, new LocationListener() {
                            @Override
                            public void onLocationChanged(Location location1) {
                                double latitude = location1.getLatitude();
                                double longitude = location1.getLongitude();
                                userLocation = new LatLng(latitude, longitude);
                                Log.d(TAG, "onLocationChanged: " + latitude + "  " + longitude);
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
        for (Restaurant resto : filteredList.getRestaurants()) {
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

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {

            @Override
            public boolean onMarkerClick(Marker marker) {
                for(Restaurant resto: filteredList.getRestaurants()) {
                    if (marker.getTitle().equals(resto.getName())) {
                        drawerMap.openDrawer(searchView);

                        new RestaurantActivity(MapsActivity.this, searchView, resto);
                        searchView.setScrollX(0);
                        searchView.setScrollY(0);
                    }
                }
                //Using position get Value from arraylist
                return false;
            }
        });



    }


    private void createNotificationChannel() {
        // Créer le NotificationChannel, seulement pour API 26+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Notification channel name";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription("Notification channel description");
            // Enregister le canal sur le système : attention de ne plus rien modifier après
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            Objects.requireNonNull(notificationManager).createNotificationChannel(channel);
        }
    }

}
