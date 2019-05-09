package com.example.georesto.Activity;

import android.app.Activity;
import android.support.design.widget.NavigationView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.georesto.Model.Tag;
import com.example.georesto.R;

import java.util.ArrayList;

class SearchActivity {
    private final Activity parent;
    private final NavigationView search;
    private final ToggleButton toggleRestaurant;
    private final ToggleButton toggleCommerce;
    private final Spinner tagSpinner;
    private final TextView tagShow;
    private final TextView priceShow;
    private final SeekBar priceSeekBar;
    private final TextView distanceShow;
    private final SeekBar distanceSeekBar;
    private final RatingBar ratingBar;
    private final Button filterButton;

    private ArrayList<Tag> tagsToShow;
    private boolean isInit;

    // Parent Activity, Navigation View to show
    SearchActivity(Activity parent, NavigationView view) {
        this.parent = parent;
        this.search = parent.findViewById(R.id.research);

        search.removeHeaderView(search.getHeaderView(0));
        search.inflateHeaderView(R.layout.research);

        View currentHeader = view.getHeaderView(0);
        this.toggleRestaurant = currentHeader.findViewById(R.id.research_toggle_restaurant);
        this.toggleCommerce = currentHeader.findViewById(R.id.research_toggle_commerce);
        this.tagSpinner = currentHeader.findViewById(R.id.research_tag_spinner);
        this.tagShow = currentHeader.findViewById(R.id.research_tag_show);
        this.priceShow = currentHeader.findViewById(R.id.research_price_show);
        this.priceSeekBar = currentHeader.findViewById(R.id.research_price_seek_bar);
        this.distanceShow = currentHeader.findViewById(R.id.research_distance_show);
        this.distanceSeekBar = currentHeader.findViewById(R.id.research_distance_seek_bar);
        this.ratingBar = currentHeader.findViewById(R.id.research_rating_bar);
        this.filterButton = currentHeader.findViewById(R.id.research_filter);

        tagsToShow = new ArrayList<>();
        isInit = true;

        this.configureToggles();
        this.configureSpinner();
        this.configureSeekBars();

        filterButton.setOnClickListener(v -> {
            // TODO : LA RECHERCHE MDR
            //this.search.removeHeaderView(view.getHeaderView(0));
            //this.search.inflateHeaderView(R.layout.info);
        });
    }

    private void configureSeekBars() {
        priceSeekBar.setProgress(20);
        priceSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                priceShow.setText(String.format(parent.getString(R.string.priceSeekBarText), progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                priceShow.setText(String.format(parent.getString(R.string.priceSeekBarText), seekBar.getProgress()));
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                priceShow.setText(String.format(parent.getString(R.string.priceSeekBarText), seekBar.getProgress()));
            }
        });

        distanceSeekBar.setProgress(5);
        distanceSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                distanceShow.setText(String.format(parent.getString(R.string.distanceSeekBarText), progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                distanceShow.setText(String.format(parent.getString(R.string.distanceSeekBarText), seekBar.getProgress()));
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                distanceShow.setText(String.format(parent.getString(R.string.distanceSeekBarText), seekBar.getProgress()));
            }
        });
    }

    private void configureSpinner() {
        ArrayList<Tag> tags = new ArrayList<>(Tag.getFullList());
        final ArrayAdapter<Tag> spinnerArrayAdapter = new ArrayAdapter<>(parent, R.layout.support_simple_spinner_dropdown_item, tags);
        spinnerArrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        tagSpinner.setAdapter(spinnerArrayAdapter);

        tagSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (!isInit) {
                    Tag currentTag = tags.get(position);
                    if (tagsToShow.contains(currentTag)) {
                        tagsToShow.remove(currentTag);
                    } else {
                        tagsToShow.add(currentTag);
                    }
                    updateTagShow();
                }
                isInit = false;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void updateTagShow() {
        StringBuilder filters = new StringBuilder();
        for (Tag tag : tagsToShow) {
            filters.append(" #").append(tag);
        }
        tagShow.setText(filters.toString());
    }

    private void configureToggles() {
        toggleRestaurant.setOnClickListener(v -> {
            if (toggleRestaurant.isChecked()) {
                toggleCommerce.setChecked(false);
            } else {
                toggleCommerce.setChecked(true);
            }
        });

        toggleCommerce.setOnClickListener(v -> {
            if (toggleCommerce.isChecked()) {
                toggleRestaurant.setChecked(false);
            } else {
                toggleRestaurant.setChecked(true);
            }
        });
    }
}
