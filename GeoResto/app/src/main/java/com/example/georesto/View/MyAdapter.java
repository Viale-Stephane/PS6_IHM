package com.example.georesto.View;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.georesto.Activity.MapsActivityOnline;
import com.example.georesto.Model.ProfileList;
import com.example.georesto.Model.Restaurant;
import com.example.georesto.R;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.AdapterHolder> {
    private ArrayList<Restaurant> history = new ArrayList<>();
    /*
        ImageView avatar = findViewById(R.id.accessProfile);
        int imageResource = getResources().getIdentifier(user.getLinkImage(), null, this.getPackageName());
        avatar.setBackgroundResource(imageResource);
     */
    public class AdapterHolder extends RecyclerView.ViewHolder {
        public TextView text;

        public AdapterHolder(View v) {
            super(v);
            text = v.findViewById(R.id.frag_nom);
        }

        public void display(String s) {
            text.setText(s);
        }
    }

    public MyAdapter(ArrayList<Restaurant> res) {
        history.addAll(res);
    }

    @NonNull
    @Override
    public AdapterHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_restaurant, viewGroup, false);
        return new AdapterHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterHolder adapterHolder, int i) {
        adapterHolder.display(history.get(i).getName());
    }

    @Override
    public int getItemCount() {
        return history.size();
    }
}
