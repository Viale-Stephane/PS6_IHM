package com.example.georesto.View;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.georesto.Model.Restaurant;
import com.example.georesto.Activity.RestaurantActivity;
import com.example.georesto.Model.RestaurantList;
import com.example.georesto.R;

import static com.example.georesto.Activity.MapsActivity.df;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.MyViewHolder> {

    private Activity parent;
    private NavigationView navigationView;
    private NavigationView searchView;
    private RestaurantList restaurants;

    private MyViewHolder myViewHolder;
    private DrawerLayout drawerMap;

    public RestaurantAdapter(Activity parent, NavigationView navigationView, RestaurantList restaurants) {
        this.parent = parent;
        this.navigationView = navigationView;

        this.restaurants = restaurants;
        this.drawerMap = parent.findViewById(R.id.drawerMaps);
        this.searchView = parent.findViewById(R.id.research);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.restaurant_list_item, viewGroup, false);
        this.myViewHolder = new MyViewHolder(view);
        return this.myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        Restaurant restaurant = this.restaurants.getRestaurant(i);
        myViewHolder.display(restaurant);
        myViewHolder.getItemView().setOnClickListener(v -> {
            this.drawerMap.closeDrawer(this.navigationView);
            this.drawerMap.openDrawer(this.searchView);

            new RestaurantActivity(parent, searchView, restaurant);
        });
    }

    @Override
    public int getItemCount() {
        return this.restaurants.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView name;
        private final ImageView picture;
        private final TextView description;
        private final TextView distance;
        private final TextView horaire;

        private Restaurant currentResto;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);

            this.name = itemView.findViewById(R.id.restaurant_list_item_name);
            this.picture = itemView.findViewById(R.id.restaurant_list_item_image);
            this.description = itemView.findViewById(R.id.restaurant_list_item_description);
            this.distance = itemView.findViewById(R.id.restaurant_list_item_distance);
            this.horaire = itemView.findViewById(R.id.restaurant_list_item_horaire);

        }

        void display(Restaurant restaurant) {
            currentResto = restaurant;
            name.setText(restaurant.getName());
            picture.setImageBitmap(restaurant.getPicture());
            description.setText(restaurant.getAddress());
            distance.setText("distance :" + df.format(restaurant.getDistance()/1000) + " km");
            horaire.setText(restaurant.isItOpen());
        }

        public View getItemView() {
            return this.itemView;
        }
    }
}
