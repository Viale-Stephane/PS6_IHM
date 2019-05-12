package com.example.georesto.View;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.georesto.Activity.RestaurantActivity;
import com.example.georesto.Model.Restaurant;
import com.example.georesto.Model.RestaurantList;
import com.example.georesto.Model.Tag;
import com.example.georesto.R;

import static com.example.georesto.Activity.MapsActivity.df;

public class RestaurantSuggestionAdapter extends RecyclerView.Adapter<RestaurantSuggestionAdapter.RestaurantSuggestionViewHolder>  {

    private RestaurantList restaurants;
    private RestaurantSuggestionViewHolder restaurantSuggestionViewHolder;
    private NavigationView profileView;
    private NavigationView searchView;
    private RestaurantActivity restaurantModel;

    public RestaurantSuggestionAdapter(RestaurantList restaurants, NavigationView searchView) {

        RestaurantList suggestionList = new RestaurantList();
        for(Restaurant restaurant : restaurants.getRestaurants()) {
            for(Tag tag: restaurant.getTags()){
                if(tag == Tag.Healthy ||tag == Tag.Salade || tag == Tag.Vege) {
                    suggestionList.addRestaurant(restaurant);
                    break;
                }
            }
        }
        this.restaurants = suggestionList;
        this.searchView = searchView;
    }

    @NonNull
    @Override
    public RestaurantSuggestionAdapter.RestaurantSuggestionViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.restaurant_list_item, viewGroup, false);
        this.restaurantSuggestionViewHolder = new RestaurantSuggestionViewHolder(view);
        return this.restaurantSuggestionViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantSuggestionViewHolder restaurantSuggestionViewHolder, int i) {
        Restaurant restaurant = this.restaurants.getRestaurant(i);
        restaurantSuggestionViewHolder.display(restaurant);
        /*restaurantSuggestionViewHolder.getItemView().setOnClickListener(v -> {
            this.drawerMap.closeDrawer(this.profileView);
            this.drawerMap.openDrawer(this.searchView);
            searchView.removeHeaderView(searchView.getHeaderView(0));
            searchView.inflateHeaderView(R.layout.info_restaurant);
            restaurantModel = new RestaurantActivity(searchView);
            restaurantModel.init(restaurant);
        });*/
    }


    @Override
    public int getItemCount() {
        return this.restaurants.size();
    }

    class RestaurantSuggestionViewHolder extends RecyclerView.ViewHolder {
        private final TextView name;
        private final ImageView picture;
        private final TextView description;
        private final TextView description2;
        private final TextView distance;

        private Restaurant currentResto;

        RestaurantSuggestionViewHolder(@NonNull View itemView) {
            super(itemView);

            this.name = itemView.findViewById(R.id.restaurant_list_item_name);
            this.picture = itemView.findViewById(R.id.restaurant_list_item_image);
            this.description = itemView.findViewById(R.id.restaurant_list_item_tag);
            this.description2 = itemView.findViewById(R.id.restaurant_list_item_tag2);
            this.distance = itemView.findViewById(R.id.restaurant_list_item_distance);
        }

        void display(Restaurant restaurant) {
            currentResto = restaurant;
            name.setText(restaurant.getName());
            picture.setImageBitmap(restaurant.getPicture());
            if(restaurant.getTags().size() != 0)
                description.setText(restaurant.getTags().get(0).getName());
            if (restaurant.getTags().size() >= 2) {
                description2.setText(restaurant.getTags().get(1).getName());
            } else {
                description2.setText("");
                description2.setBackgroundColor(255);
            }
            distance.setText("distance :" + df.format(restaurant.getDistance()/1000) + " km");
        }

        public View getItemView() {
            return this.itemView;
        }
    }
}
