package com.example.georesto.View;

import android.app.AlertDialog;
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
import com.example.georesto.R;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private ArrayList<Restaurant> restaurants;
    private MyViewHolder myViewHolder;
    private DrawerLayout drawerMap;
    private NavigationView profileView;
    private NavigationView searchView;

    public MyAdapter(ArrayList<Restaurant> restaurants, DrawerLayout drawerMap, NavigationView profileView, NavigationView searchView) {
        this.restaurants = restaurants;
        this.drawerMap = drawerMap;
        this.profileView = profileView;
        this.searchView = searchView;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.restaurant_list_item, viewGroup, false);
        this.myViewHolder = new MyViewHolder(view);
        System.out.println("ici");
        return this.myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        Restaurant restaurant = this.restaurants.get(i);
        myViewHolder.display(restaurant);
        myViewHolder.getItemView().setOnClickListener(v -> {
            this.drawerMap.closeDrawer(this.profileView);
            this.drawerMap.openDrawer(this.searchView);
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

        private Restaurant currentResto;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);

            this.name = itemView.findViewById(R.id.restaurant_list_item_name);
            this.picture = itemView.findViewById(R.id.restaurant_list_item_image);
            this.description = itemView.findViewById(R.id.restaurant_list_item_description);
            itemView.setOnClickListener((view) -> {
                System.out.println(currentResto.getName());
            });
            /*itemView.setOnClickListener((view) ->
                    new AlertDialog.Builder(itemView.getContext())
                            .setTitle(currentResto.getName()));*/
        }

        void display(Restaurant restaurant) {
            currentResto = restaurant;
            name.setText(restaurant.getName());
            picture.setImageBitmap(restaurant.getPicture());
            description.setText(restaurant.getAdress());
        }

        public View getItemView() {
            return this.itemView;
        }
    }
}
