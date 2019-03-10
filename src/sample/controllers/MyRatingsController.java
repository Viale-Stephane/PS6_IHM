package sample.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;
import sample.Comment;
import sample.Profile;
import sample.Restaurant;
import sample.View;
import sample.models.MyRatingsModel;


public class MyRatingsController {
    @FXML
    private ListView<Pane> ratingList;
    @FXML
    private Button goBack;

    private ObservableList<Pane> panes = FXCollections.observableArrayList();
    private ObservableList<Comment> items = FXCollections.observableArrayList();

    private int prefHeight = 100;
    private int prefWidth = 200;

    MyRatingsModel model = new MyRatingsModel();

    public void init(Profile profile) {
        items.addAll(profile.getUserComments().getCommentList());
        for(Comment comment : profile.getUserComments().getCommentList()){
            Pane pane = new Pane();
            Label restaurantName = new Label();
            Text commentUser = new Text();
            commentUser.setText(comment.getComment());
            commentUser.setY(30);
            commentUser.setWrappingWidth(prefWidth/2 - 2);
            ImageView image = new ImageView(comment.getRestaurant().getRestaurantPicture());

            image.setPreserveRatio(false);
            image.setFitWidth(this.prefWidth/2);
            image.setFitHeight(this.prefHeight);
            image.setX(this.prefWidth/2);
            image.setY(0);

            pane.setPrefHeight(this.prefHeight);
            pane.setPrefWidth(this.prefWidth);
            restaurantName.setText(comment.getRestaurant().getName());
            pane.getChildren().add(image);
            pane.getChildren().add(restaurantName);
            pane.getChildren().add(commentUser);
            panes.add(pane);

        }
        ratingList.setItems(panes);
        for(int i=0; i<ratingList.getItems().size();i++) {
            int finalI = i;
            ratingList.getItems().get(i).setOnMouseClicked(event -> model.accessRestaurantPage(profile.getUserComments().get(finalI).getRestaurant(),profile));
            if((i+1) == ratingList.getItems().size()){
                goBack.setLayoutX(ratingList.getWidth()-goBack.getWidth());
                goBack.setLayoutY(ratingList.getHeight()-goBack.getHeight());

            }
        }
        //goBack.setOnMouseClicked(event -> model.accessingTo(profile, View.PROFILE,View.CSS_FILE,"ProfileController"));
    }
}
