package com.example.georesto.View;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.georesto.Model.Comment;
import com.example.georesto.Model.CommentList;
import com.example.georesto.Model.Restaurant;
import com.example.georesto.R;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder> {

    private CommentList commentList;
    private CommentViewHolder viewHolder;
    private DrawerLayout drawerMap;
    private NavigationView profileView;
    private NavigationView searchView;

    public CommentAdapter(CommentList commentList, DrawerLayout drawerMap, NavigationView profileView, NavigationView searchView) {
        this.commentList = commentList;
        this.drawerMap = drawerMap;
        this.profileView = profileView;
        this.searchView = searchView;
    }

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.restaurant_list_item, viewGroup, false);
        this.viewHolder = new CommentViewHolder(view);
        return this.viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder commentViewHolder, int i) {
        Comment comment = this.commentList.get(i);
        commentViewHolder.display(comment);
        commentViewHolder.getItemView().setOnClickListener(v -> {
            this.drawerMap.closeDrawer(this.profileView);
            this.drawerMap.openDrawer(this.searchView);
            searchView.removeHeaderView(searchView.getHeaderView(0));
            searchView.inflateHeaderView(R.layout.info_restaurant);
        });
    }

    @Override
    public int getItemCount() {
        return this.commentList.size();
    }

    class CommentViewHolder extends RecyclerView.ViewHolder {
        private final TextView name;
        private final ImageView picture;
        private final TextView description;

        private Restaurant currentResto;

        CommentViewHolder(@NonNull View itemView) {
            super(itemView);

            this.name = itemView.findViewById(R.id.restaurant_list_item_name);
            this.picture = itemView.findViewById(R.id.restaurant_list_item_image);
            this.description = itemView.findViewById(R.id.restaurant_list_item_description);
        }

        void display(Comment comment) {
            currentResto = comment.getRestaurant();
            name.setText(currentResto.getName());
            picture.setImageBitmap(currentResto.getPicture());
            description.setText(comment.getComment());
        }

        public View getItemView() {
            return this.itemView;
        }
    }
}

