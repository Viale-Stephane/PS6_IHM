package com.example.georesto.Model;

import android.support.design.widget.NavigationView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.georesto.R;

import java.util.ArrayList;
import java.util.List;

public class NewLocationModel {
    private View currentHeader;
    private ToggleButton restaurantButton;
    private ToggleButton commerceButton;
    private EditText nameLocation;
    private  RatingBar ratingBar;
    private EditText adress;
    private EditText website;
    private EditText phoneNumber;
    private Spinner tagList;
    private TextView actualFilters;
    private TextView price;
    private SeekBar seekBarPrice;
    private Button cancel;
    private Button next;

    //------------------------------------//
    private final List<Tag> tags = new ArrayList<>();
    private List<Tag> currentFilters;
    private boolean isInit;


    public NewLocationModel(NavigationView profileView) {
        this.currentHeader = profileView.getHeaderView(0);
        this.restaurantButton = currentHeader.findViewById(R.id.restaurantButton);
        this.commerceButton = currentHeader.findViewById(R.id.commerceButton);
        this.nameLocation = currentHeader.findViewById(R.id.editTextNameLocation);
        this.ratingBar = currentHeader.findViewById(R.id.ratingBar);
        this.adress = currentHeader.findViewById(R.id.editTextAdress);
        this.website = currentHeader.findViewById(R.id.editTextWebsite);
        this.phoneNumber = currentHeader.findViewById(R.id.editTextPhoneNumber);
        this.tagList = currentHeader.findViewById(R.id.tagList);
        this.actualFilters = currentHeader.findViewById(R.id.actualFilters);
        this.price = currentHeader.findViewById(R.id.price);
        this.seekBarPrice = currentHeader.findViewById(R.id.seekBarPrice);
        this.cancel = currentHeader.findViewById(R.id.cancelButton);
        this.next = currentHeader.findViewById(R.id.nextButton);
        //-------------------------------------------------------//
        this.tags.addAll(Tag.getFullList());
        this.currentFilters = new ArrayList<>();
        this.isInit = true;
    }

    public void init(Restaurant restaurant) {
        if(restaurant != null) {
            this.restaurantButton.setChecked(restaurant.isKindRestaurant());
            this.commerceButton.setChecked(!restaurant.isKindRestaurant());
            this.nameLocation.setText(restaurant.getName());
            this.ratingBar.setRating(((float) restaurant.getGrade()));
            this.adress.setText(restaurant.getAdress());
            this.website.setText(restaurant.getWebsite());
            this.phoneNumber.setText(restaurant.getPhoneNumber());

            this.currentFilters = restaurant.getTags();
            String filters = "";
            for(int i = 0; i < currentFilters.size(); i++) {
                filters += currentFilters.get(i).getName();
            }
            this.actualFilters.setText(filters);

            this.price.setText("Prix : " + restaurant.getPrice() + " €");
            this.seekBarPrice.setProgress((int) restaurant.getPrice());

        } else {
            this.seekBarPrice.setProgress(20);
        }
        this.seekBarPrice.setMax(500);
    }

    public void setAdapterToTagList(ArrayAdapter<Tag> spinnerArrayAdapter) {
        this.tagList.setAdapter(spinnerArrayAdapter);
    }

    public void clickOnRestaurantButton() {
        this.restaurantButton.setOnClickListener(v -> {
            if(this.restaurantButton.isChecked()){
                this.commerceButton.setChecked(false);
            }else {
                this.commerceButton.setChecked(true);
            }
        });
    }

    public void clickOnCommerceButton() {
        this.commerceButton.setOnClickListener(v -> {
            if(this.commerceButton.isChecked()) {
                this.restaurantButton.setChecked(false);
            }else {
                this.restaurantButton.setChecked(true);
            }
        });
    }

    public void selectTagInTagList() {
        this.tagList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (isInit == false) {
                    boolean isInTheList = false;
                    String filters = "";
                    for (Tag tag : currentFilters) {
                        if (tag != null && tagList.getItemAtPosition(position) == tag) {
                            currentFilters.remove(tag);
                            isInTheList = true;
                            break;
                        }
                    }
                    if (!isInTheList)
                        currentFilters.add((Tag) tagList.getItemAtPosition(position));
                    for (int i = 0; i < currentFilters.size(); i++) {
                        filters += currentFilters.get(i).getName();
                    }
                    actualFilters.setText(filters);
                }
                isInit = false;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void onClickOnSeekBarPrice() {
        this.seekBarPrice.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                price.setText("Prix: "+ progress + " €");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                price.setText("Prix: "+ seekBarPrice.getProgress() + " €");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                price.setText("Prix: "+ seekBarPrice.getProgress() + " €");
            }
        });
    }

    public Button getCancel() {
        return this.cancel;
    }

    public Button getNext() {
        return this.next;
    }

    public List<Tag> getTags() {//for array spinner adapter
        return tags;
    }

    public List<Tag> getCurrentFilters() {
        return this.currentFilters;
    }

    public String getNameOfTheLocation() {
        return this.nameLocation.getText().toString();
    }

    public boolean isARestaurant() {
        return this.restaurantButton.isChecked();
    }

    public String getAdressOfTheLocation() {
        return this.adress.getText().toString();
    }

    public String getWebsiteOfTheLocation() {
        return this.website.getText().toString();
    }

    public String getPhoneNumberOfTheLocation() {
        return this.phoneNumber.getText().toString();
    }

    public float getRatingOfTheLocation() {
        return this.ratingBar.getRating();
    }

    public double getPriceOfTheLocation() {
        return this.seekBarPrice.getProgress();
    }
}
