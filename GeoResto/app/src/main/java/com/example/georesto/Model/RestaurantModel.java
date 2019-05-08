package com.example.georesto.Model;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.georesto.R;

import org.w3c.dom.Text;

public class RestaurantModel {
    private View currentHeader;
    private TextView name;
    private ImageView picture;
    private RatingBar ratingBar;
    private TextView adress;
    private TextView website;
    private TextView phone;
    private TextView monday;
    private TextView tuesday;
    private TextView wednesday;
    private TextView thursday;
    private TextView friday;
    private TextView saturday;
    private TextView sunday;

    private Button rappel;

    public RestaurantModel(NavigationView resView) {
        this.currentHeader = resView.getHeaderView(0);
        this.name = currentHeader.findViewById(R.id.infoRes_name);
        this.picture = currentHeader.findViewById(R.id.infoRes_picture);
        this.ratingBar = currentHeader.findViewById(R.id.infoRes_rate);
        this.adress = currentHeader.findViewById(R.id.infoRes_address);
        this.website = currentHeader.findViewById(R.id.infoRes_website);
        this.phone = currentHeader.findViewById(R.id.infoRes_tel);
        this.monday = currentHeader.findViewById(R.id.infoRes_monday);
        this.tuesday = currentHeader.findViewById(R.id.infoRes_tuesday);
        this.wednesday = currentHeader.findViewById(R.id.infoRes_wednesday);
        this.thursday = currentHeader.findViewById(R.id.infoRes_thursday);
        this.friday = currentHeader.findViewById(R.id.infoRes_friday);
        this.saturday = currentHeader.findViewById(R.id.infoRes_saturday);
        this.sunday = currentHeader.findViewById(R.id.infoRes_sunday);

        this.rappel = currentHeader.findViewById(R.id.buttonAddRappel);
    }

    public void init(Restaurant restaurant) {
        this.name.setText(restaurant.getName());
        this.picture.setImageBitmap(restaurant.getPicture());
        this.ratingBar.setRating(((float) restaurant.getGrade()));
        this.adress.setText(restaurant.getAdress());
        this.website.setText(restaurant.getWebsite());
        this.phone.setText(restaurant.getPhoneNumber());
        this.monday.setText(restaurant.getSchedule(0));
        this.tuesday.setText(restaurant.getSchedule(1));
        this.wednesday.setText(restaurant.getSchedule(2));
        this.thursday.setText(restaurant.getSchedule(3));
        this.friday.setText(restaurant.getSchedule(4));
        this.saturday.setText(restaurant.getSchedule(5));
        this.sunday.setText(restaurant.getSchedule(6));

        /*
        rappel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_EDIT);
                intent.setType("vnd.android.cursor.item/event");
                intent.putExtra("title", "Réservation "+restaurant.getName());
                intent.putExtra("eventLocation", restaurant.getAdress());
                startActivity(intent);
            }
        });
        */
    }
}
