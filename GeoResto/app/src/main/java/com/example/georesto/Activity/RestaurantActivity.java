package com.example.georesto.Activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.Location;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.util.Linkify;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.georesto.Model.ProfileList;
import com.example.georesto.Model.Restaurant;
import com.example.georesto.R;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

public class RestaurantActivity {
    private Activity parent;
    private NavigationView navigationView;
    private Restaurant restaurant;

    private View currentHeader;
    private TextView name;
    private ImageView picture;
    private RatingBar ratingBar;
    private TextView address;
    private TextView website;
    private TextView phone;
    private TextView monday;
    private TextView tuesday;
    private TextView wednesday;
    private TextView thursday;
    private TextView friday;
    private TextView saturday;
    private TextView sunday;

    private ImageButton buttonStar;
    private boolean favourite;

    private Button addContact;
    private Button rappel;

    public RestaurantActivity(Activity parent, NavigationView navigationView, Restaurant restaurant) {
        this.parent = parent;
        this.navigationView = navigationView;
        this.restaurant = restaurant;

        navigationView.removeHeaderView(navigationView.getHeaderView(0));
        navigationView.inflateHeaderView(R.layout.info_restaurant);

        this.currentHeader = navigationView.getHeaderView(0);
        this.name = currentHeader.findViewById(R.id.infoRes_name);
        this.picture = currentHeader.findViewById(R.id.infoRes_picture);
        this.ratingBar = currentHeader.findViewById(R.id.infoRes_rate);
        this.ratingBar.setIsIndicator(true);
        this.address = currentHeader.findViewById(R.id.infoRes_address);
        this.website = currentHeader.findViewById(R.id.infoRes_website);
        this.phone = currentHeader.findViewById(R.id.infoRes_tel);
        this.monday = currentHeader.findViewById(R.id.infoRes_monday);
        this.tuesday = currentHeader.findViewById(R.id.infoRes_tuesday);
        this.wednesday = currentHeader.findViewById(R.id.infoRes_wednesday);
        this.thursday = currentHeader.findViewById(R.id.infoRes_thursday);
        this.friday = currentHeader.findViewById(R.id.infoRes_friday);
        this.saturday = currentHeader.findViewById(R.id.infoRes_saturday);
        this.sunday = currentHeader.findViewById(R.id.infoRes_sunday);

        this.buttonStar = currentHeader.findViewById(R.id.infoRes_star);
        this.favourite = false;

        this.addContact = currentHeader.findViewById(R.id.buttonAddContact);
        this.rappel = currentHeader.findViewById(R.id.buttonAddRappel);

        this.setRestaurantInformation(restaurant);

        this.setFavourite(restaurant);

        this.configureAddContact();
        this.configureAddRemind();
    }

    private void configureAddContact() {
        addContact.setOnClickListener(v -> {
            if (getContactPermissions()) {
                Uri lookupUri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(restaurant.getPhoneNumber()));
                Cursor cursor = parent.getContentResolver().query(lookupUri, new String[]{ContactsContract.PhoneLookup.DISPLAY_NAME, ContactsContract.PhoneLookup._ID}, null, null, null);
                if (cursor != null && !cursor.moveToFirst()) {
                    this.createContact(restaurant);
                    cursor.close();
                } else {
                    Toast.makeText(parent.getApplicationContext(), "Le contact existe déjà",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void configureAddRemind() {
        rappel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_EDIT);
                intent.setType("vnd.android.cursor.item/event");
                intent.putExtra("title", "Réservation "+restaurant.getName());
                intent.putExtra("eventLocation", restaurant.getAddress());
                parent.startActivity(intent);
            }
        });
    }

    private void createContact(Restaurant restaurant) {
        Intent intent = new Intent(Intent.ACTION_INSERT);
        intent.setType(ContactsContract.Contacts.CONTENT_TYPE);
        intent.putExtra(ContactsContract.Intents.Insert.NAME, restaurant.getName());
        intent.putExtra(ContactsContract.Intents.Insert.PHONE, restaurant.getPhoneNumber());
        intent.putExtra(ContactsContract.Intents.Insert.POSTAL, restaurant.getAddress());
        if (intent.resolveActivity(parent.getPackageManager()) != null) {
            parent.startActivity(intent);
        }
    }

    private boolean getContactPermissions() {
        String[] permissions = {Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_CONTACTS};
        boolean permission = true;
        if (ContextCompat.checkSelfPermission(parent.getApplicationContext(), Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(parent, permissions, 1);
            permission = false;
        }
        if (ContextCompat.checkSelfPermission(parent.getApplicationContext(), Manifest.permission.WRITE_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(parent, permissions, 2);
            permission = false;
        }
        return permission;
    }

    private void setRestaurantInformation(Restaurant restaurant) {
        this.name.setText(restaurant.getName());
        this.picture.setImageBitmap(restaurant.getPicture());
        this.ratingBar.setRating((float) restaurant.getGrade());
        this.address.setText(restaurant.getAddress());
        this.website.setText(restaurant.getWebsite());
        this.phone.setText(restaurant.getPhoneNumber());
        this.monday.setText(restaurant.getSchedule(0));
        this.tuesday.setText(restaurant.getSchedule(1));
        this.wednesday.setText(restaurant.getSchedule(2));
        this.thursday.setText(restaurant.getSchedule(3));
        this.friday.setText(restaurant.getSchedule(4));
        this.saturday.setText(restaurant.getSchedule(5));
        this.sunday.setText(restaurant.getSchedule(6));

        address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MapsActivity) parent ).moveCamera(restaurant.getPosition(), 15.0f);
            }
        });
        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:"+restaurant.getPhoneNumber()));
                parent.startActivity(intent);
            }
        });
        Linkify.addLinks(website, Linkify.WEB_URLS);
    }

    private void setFavourite(Restaurant restaurant) {
        buttonStar.setImageResource(android.R.drawable.star_big_off);
        for(Restaurant res: ProfileList.getCurrentUser().getFavourites()) {
            if(res.equals(restaurant)) {
                buttonStar.setImageResource(android.R.drawable.star_big_on);
                favourite = true;
                break;
            }
        }

        buttonStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (favourite){
                    buttonStar.setImageResource(android.R.drawable.star_big_off);
                    ProfileList.getCurrentUser().removeFavourite(restaurant);
                } else {
                    buttonStar.setImageResource(android.R.drawable.star_big_on);
                    ProfileList.getCurrentUser().addFavourite(restaurant);
                }
                favourite = !favourite;
            }
        });
    }
}
