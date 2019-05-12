package com.example.georesto.Activity;

import android.app.Activity;
import android.support.design.widget.NavigationView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.SearchView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.georesto.Model.ProfileList;
import com.example.georesto.Model.RestaurantList;
import com.example.georesto.Model.Tag;
import com.example.georesto.R;
import com.example.georesto.Service.FilterService;
import com.example.georesto.View.RestaurantAdapter;
import com.example.georesto.View.RestaurantSuggestionAdapter;

import java.util.ArrayList;

import static com.example.georesto.Activity.MapsActivity.restaurantList;
import static com.example.georesto.Activity.MapsActivity.filteredList;

class SearchActivity {
    private final Activity parent;
    private final NavigationView searchView;
    private final FilterService filterService;

    private SearchView searchBar;
    private ToggleButton toggleRestaurant;
    private ToggleButton toggleCommerce;
    private Spinner tagSpinner;
    private TextView tagShow;
    private TextView priceShow;
    private SeekBar priceSeekBar;
    private TextView distanceShow;
    private SeekBar distanceSeekBar;
    private RatingBar ratingBar;
    private Button filterButton;
    private Button suggestionButton;
    private ArrayList<Tag> tagsToShow;
    private boolean isInit;

    MapsActivity mapsActivity;

    // Parent Activity, Navigation View to show
    SearchActivity(Activity parent, NavigationView view, MapsActivity mapsActivity) {
        this.parent = parent;
        this.searchView = parent.findViewById(R.id.research);

        searchView.removeHeaderView(searchView.getHeaderView(0));
        searchView.inflateHeaderView(R.layout.research);

        View currentHeader = view.getHeaderView(0);
        this.searchBar = currentHeader.findViewById(R.id.research_search);
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
        this.suggestionButton = currentHeader.findViewById(R.id.suggestionButton);

        this.mapsActivity = mapsActivity;


        this.tagsToShow = new ArrayList<>();
        this.isInit = true;

        this.configureToggles();
        this.configureSpinner();
        this.configureSeekBars();

        filterService = new FilterService();

        filterButton.setOnClickListener(v -> filter(searchBar.getQuery()));
        suggestionButton.setOnClickListener(v -> {
            searchView.removeHeaderView(searchView.getHeaderView(0));
            searchView.inflateHeaderView(R.layout.suggestions);
            RecyclerView rv = parent.findViewById(R.id.suggestions);
            rv.setLayoutManager(new LinearLayoutManager(parent));
            RestaurantSuggestionAdapter adapter =new RestaurantSuggestionAdapter(parent, restaurantList);
            rv.setAdapter(adapter);
        });

        searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    private void filter(CharSequence query) {
        filteredList = restaurantList;
        if (ProfileList.getCurrentUser() == null) {

            filteredList = new RestaurantList(filterService.filter(query, toggleRestaurant.isChecked(), tagsToShow, priceSeekBar.getProgress(), distanceSeekBar.getProgress(), ratingBar.getProgress()));
        } else {
            filteredList = new RestaurantList(filterService.filter(query, toggleRestaurant.isChecked(), tagsToShow, priceSeekBar.getProgress(), distanceSeekBar.getProgress(), ratingBar.getProgress(), ProfileList.getCurrentUser()));
        }

        searchView.removeHeaderView(searchView.getHeaderView(0));
        searchView.inflateHeaderView(R.layout.result);

        RecyclerView recyclerView = searchView.findViewById(R.id.result);
        recyclerView.setLayoutManager(new LinearLayoutManager(parent));
        RestaurantAdapter adapter = new RestaurantAdapter(parent, searchView, filteredList);
        mapsActivity.updateMapsMarker(filteredList);
        recyclerView.setAdapter(adapter);
    }

    // ------------------ //
    //   CONFIGURATION    //
    // ------------------ //

    private void configureSeekBars() {
        priceSeekBar.setProgress(20);
        priceSeekBar.setMax(500);
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
        distanceSeekBar.setMax(200);
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
