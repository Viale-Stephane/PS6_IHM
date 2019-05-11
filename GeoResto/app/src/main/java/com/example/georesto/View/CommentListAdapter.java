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

import com.example.georesto.Model.Comment;
import com.example.georesto.Model.CommentList;
import com.example.georesto.Model.Profile;
import com.example.georesto.Model.Restaurant;
import com.example.georesto.Activity.RestaurantActivity;
import com.example.georesto.R;

import java.util.ArrayList;

import static com.example.georesto.Activity.MapsActivity.df;

public class CommentListAdapter extends RecyclerView.Adapter<CommentListAdapter.CommentListViewHolder> {

    private ArrayList<Comment> commentList;
    private CommentListViewHolder viewHolder;

    public CommentListAdapter(ArrayList<Comment> commentList) {
        this.commentList = commentList;
    }

    @NonNull
    @Override
    public CommentListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.comment_list_item, viewGroup, false);
        this.viewHolder = new CommentListViewHolder(view);
        return this.viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CommentListViewHolder commentViewHolder, int i) {
        Comment comment = this.commentList.get(i);
        commentViewHolder.display(comment);
    }

    @Override
    public int getItemCount() {
        return this.commentList.size();
    }

    class CommentListViewHolder extends RecyclerView.ViewHolder {
        private final TextView name;
        private final TextView currentComment;
        private Profile currentProfile;

        CommentListViewHolder(@NonNull View itemView) {
            super(itemView);

            this.name = itemView.findViewById(R.id.comment_list_Name);
            this.currentComment = itemView.findViewById(R.id.comment_list_Comment);
        }

        void display(Comment comment) {
            currentProfile = comment.getProfile();
            name.setText(currentProfile.getFirstName()+" "+currentProfile.getLastName());
            currentComment.setText(comment.getComment());
        }

        public View getItemView() {
            return this.itemView;
        }
    }
}


