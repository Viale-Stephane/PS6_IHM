package com.example.georesto.Activity;

import android.Manifest;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.georesto.Model.Comment;
import com.example.georesto.Model.PopUpTimePickerModel;
import com.example.georesto.Model.Profile;
import com.example.georesto.Model.ProfileList;
import com.example.georesto.Model.Restaurant;
import com.example.georesto.R;
import com.example.georesto.View.CommentListAdapter;

import java.util.ArrayList;

public class RestaurantActivity {
    private Activity parent;
    private NavigationView navigationView;
    private Restaurant restaurant;

    private LinearLayout addressBlock;
    private LinearLayout websiteBlock;
    private LinearLayout phoneNumberBlock;

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

    private ToggleButton details;
    private ToggleButton comments;

    private ImageButton buttonStar;
    private boolean favourite;

    private Button addComment;
    private EditText newComment;

    private Button addContact;
    private Button rappel;

    PopUpTimePickerModel popUpTimePickerModel;


    public RestaurantActivity(Activity parent, NavigationView navigationView, Restaurant restaurant) {
        this.parent = parent;
        this.navigationView = navigationView;
        this.restaurant = restaurant;

        navigationView.removeHeaderView(navigationView.getHeaderView(0));
        navigationView.inflateHeaderView(R.layout.info_restaurant);

        this.currentHeader = navigationView.getHeaderView(0);

        this.addressBlock = currentHeader.findViewById(R.id.adressLayout);
        this.websiteBlock = currentHeader.findViewById(R.id.websiteLayout);
        this.phoneNumberBlock = currentHeader.findViewById(R.id.phoneNumberLayout);

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

        this.details = currentHeader.findViewById(R.id.infoRes_Details);
        this.comments = currentHeader.findViewById(R.id.infoRes_Comments);

        this.buttonStar = currentHeader.findViewById(R.id.infoRes_star);
        this.favourite = false;

        this.addComment = currentHeader.findViewById(R.id.buttonAddComment);
        this.newComment = currentHeader.findViewById(R.id.textAddComment);

        this.addContact = currentHeader.findViewById(R.id.buttonAddContact);
        this.rappel = currentHeader.findViewById(R.id.buttonAddRappel);

        this.setRestaurantInformation(restaurant);

        if (ProfileList.getCurrentUser() != null) {
            //Setup en online
            this.configureFavourite(restaurant);
            this.setToggleButtonsOnline();
            addComment.setOnClickListener(v -> {
                if(!newComment.getText().toString().equals("")) {
                    Profile currentUser = ProfileList.getCurrentUser();
                    Comment comment = new Comment(newComment.getText().toString(), currentUser, restaurant);
                    currentUser.getUserComments().addComment(comment);
                    displayComments(restaurant);
                }
            });
        } else {
            //Setup en offline
            buttonStar.setVisibility(View.GONE);
            currentHeader.findViewById(R.id.layoutAddComments).setVisibility(View.GONE);
            this.setToggleButtonsOffline();
        }

        this.displayComments(restaurant);
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
        rappel.setOnClickListener(v -> {
            showNotification(this.navigationView,10000,"Rappel : " + restaurant.getName(), restaurant.getName()+" est ouvert au " + restaurant.getAddress());
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

    private void setToggleButtonsOnline() {
        details.setOnClickListener(v -> {
            if (details.isChecked()) {
                comments.setChecked(false);
                currentHeader.findViewById(R.id.layoutDetails).setVisibility(View.VISIBLE);
                currentHeader.findViewById(R.id.layoutButtons).setVisibility(View.VISIBLE);
                currentHeader.findViewById(R.id.layoutComments).setVisibility(View.GONE);
                currentHeader.findViewById(R.id.layoutAddComments).setVisibility(View.GONE);
            } else {
                details.setChecked(true);
                currentHeader.findViewById(R.id.layoutDetails).setVisibility(View.VISIBLE);
                currentHeader.findViewById(R.id.layoutButtons).setVisibility(View.VISIBLE);
                currentHeader.findViewById(R.id.layoutComments).setVisibility(View.GONE);
                currentHeader.findViewById(R.id.layoutAddComments).setVisibility(View.GONE);
            }
        });

        comments.setOnClickListener(v -> {
            if (comments.isChecked()) {
                details.setChecked(false);
                currentHeader.findViewById(R.id.layoutDetails).setVisibility(View.GONE);
                currentHeader.findViewById(R.id.layoutButtons).setVisibility(View.GONE);
                currentHeader.findViewById(R.id.layoutComments).setVisibility(View.VISIBLE);
                currentHeader.findViewById(R.id.layoutAddComments).setVisibility(View.VISIBLE);
            } else {
                comments.setChecked(true);
                currentHeader.findViewById(R.id.layoutDetails).setVisibility(View.GONE);
                currentHeader.findViewById(R.id.layoutButtons).setVisibility(View.GONE);
                currentHeader.findViewById(R.id.layoutComments).setVisibility(View.VISIBLE);
                currentHeader.findViewById(R.id.layoutAddComments).setVisibility(View.VISIBLE);
            }
        });
    }

    private void setToggleButtonsOffline() {
        details.setOnClickListener(v -> {
            if (details.isChecked()) {
                comments.setChecked(false);
                currentHeader.findViewById(R.id.layoutDetails).setVisibility(View.VISIBLE);
                currentHeader.findViewById(R.id.layoutButtons).setVisibility(View.VISIBLE);
                currentHeader.findViewById(R.id.layoutComments).setVisibility(View.GONE);
            } else {
                details.setChecked(true);
                currentHeader.findViewById(R.id.layoutDetails).setVisibility(View.VISIBLE);
                currentHeader.findViewById(R.id.layoutButtons).setVisibility(View.VISIBLE);
                currentHeader.findViewById(R.id.layoutComments).setVisibility(View.GONE);
            }
        });

        comments.setOnClickListener(v -> {
            if (comments.isChecked()) {
                details.setChecked(false);
                currentHeader.findViewById(R.id.layoutDetails).setVisibility(View.GONE);
                currentHeader.findViewById(R.id.layoutButtons).setVisibility(View.GONE);
                currentHeader.findViewById(R.id.layoutComments).setVisibility(View.VISIBLE);
            } else {
                comments.setChecked(true);
                currentHeader.findViewById(R.id.layoutDetails).setVisibility(View.GONE);
                currentHeader.findViewById(R.id.layoutButtons).setVisibility(View.GONE);
                currentHeader.findViewById(R.id.layoutComments).setVisibility(View.VISIBLE);
            }
        });
    }

    private void displayComments(Restaurant restaurant) {
        RecyclerView rv = currentHeader.findViewById(R.id.infoRes_listComments);
        rv.setNestedScrollingEnabled(false);
        rv.setLayoutManager(new LinearLayoutManager(parent));

        ArrayList<Comment> commentsList = new ArrayList<>();
        for(Profile profile: ProfileList.getProfiles()) {
            for (Comment c : profile.getUserComments().getCommentList()) {
                if (c.getRestaurant().equals(restaurant)) commentsList.add(c);
            }
        }

        CommentListAdapter adapter = new CommentListAdapter(commentsList);
        rv.setAdapter(adapter);
    }

    private void setRestaurantInformation(Restaurant restaurant) {
        this.name.setText(restaurant.getName());
        this.picture.setImageBitmap(restaurant.getPicture());
        this.ratingBar.setRating((float) restaurant.getGrade());
        this.address.setText(restaurant.getAddress());
        this.website.setText(restaurant.getWebsite());
        this.phone.setText(restaurant.getPhoneNumber());
        this.monday.setText("Lundi : "+restaurant.getSchedule(0));
        this.tuesday.setText("Mardi : "+restaurant.getSchedule(1));
        this.wednesday.setText("Mercredi : "+restaurant.getSchedule(2));
        this.thursday.setText("Jeudi : "+restaurant.getSchedule(3));
        this.friday.setText("Vendredi : "+restaurant.getSchedule(4));
        this.saturday.setText("Samedi : "+restaurant.getSchedule(5));
        this.sunday.setText("Dimanche : "+restaurant.getSchedule(6));

        addressBlock.setOnClickListener(v -> ((MapsActivity) parent).moveCamera(restaurant.getPosition(), 15.0f));
        phoneNumberBlock.setOnClickListener(v -> {
            if(ContextCompat.checkSelfPermission(parent,Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + restaurant.getPhoneNumber()));
                parent.startActivity(intent);
            } else {
                ActivityCompat.requestPermissions(parent, new String[]{Manifest.permission.CALL_PHONE},0);
            }
        });
        websiteBlock.setOnClickListener(v -> {
            try {
                Intent launchBrowser = new Intent(Intent.ACTION_VIEW, Uri.parse(restaurant.getWebsite()));
                parent.startActivity(launchBrowser);
           /* Linkify.addLinks(website, Linkify.WEB_URLS);
            Linkify */
            } catch ( ActivityNotFoundException e) {
                e.printStackTrace();
            }
        });

    }

    private void configureFavourite(Restaurant restaurant) {
        buttonStar.setImageResource(android.R.drawable.star_big_off);

        buttonStar.setVisibility(View.VISIBLE);

        for (Restaurant res : ProfileList.getCurrentUser().getFavourites()) {
            if (res.equals(restaurant)) {
                buttonStar.setImageResource(android.R.drawable.star_big_on);
                favourite = true;
                break;
            }
        }

        buttonStar.setOnClickListener(view -> {
            if (favourite) {
                buttonStar.setImageResource(android.R.drawable.star_big_off);
                ProfileList.getCurrentUser().removeFavourite(restaurant);
            } else {
                buttonStar.setImageResource(android.R.drawable.star_big_on);
                ProfileList.getCurrentUser().addFavourite(restaurant);
            }
            favourite = !favourite;
        });
    }

    public void showNotification(View view,int millis, String title, String desc) {
        Thread notif = new Thread() {
            @Override
            public void run() {
                while (!isInterrupted()) {
                    try {
                        Thread.sleep(millis);

                        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(view.getContext());

                        NotificationCompat.Builder notifBuilder = new NotificationCompat.Builder(view.getContext(), MapsActivity.CHANNEL_ID)
                                .setSmallIcon(R.drawable.ic_launcher_foreground)
                                .setContentTitle(title)
                                .setContentText(desc)
                                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

                        // notificationId est un identificateur unique par notification qu'il vous faut définir
                        notificationManager.notify(MapsActivity.NOTIFICATION_ID, notifBuilder.build());

                    } catch (InterruptedException e) {}

                }

            }
        };

        notif.start();
    }
}
