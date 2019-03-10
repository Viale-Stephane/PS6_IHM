package sample.controllers;

import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import sample.*;
import sample.models.FavorisModel;
import sample.models.RestaurantListModel;


public class FavorisController {

    @FXML
    private ListView<Pane> paneListView;
    @FXML
    private Button goBack;

    private ObservableList<Pane> panes = FXCollections.observableArrayList();
    private ObservableList<Restaurant> items = FXCollections.observableArrayList();

    private int prefHeight = 100;
    private int prefWidth = 200;

    FavorisModel model = new FavorisModel();

    public void init(Profile profile) {
        items.addAll(profile.getFavoris());
        for(int i=0 ;i<profile.getFavoris().size();i++){
            Pane pane = new Pane();
            Label restaurantName = new Label();
            Label monday = new Label(), thuesday = new Label(), wednesday = new Label(), thursday = new Label(), friday = new Label(), saturday = new Label(), sunday = new Label();
            Label[] schedule = {monday,thuesday,wednesday,thursday,friday,saturday,sunday};
            ImageView image = new ImageView(profile.getFavori(i).getRestaurantPicture());

            image.setPreserveRatio(false);
            image.setFitWidth(this.prefWidth/2);
            image.setFitHeight(this.prefHeight);
            image.setX(this.prefWidth/2);
            image.setY(0);
            model.setSchedule(schedule,profile.getFavori(i));
            ImageView star1 = new ImageView(), star2 = new ImageView(), star3 = new ImageView(), star4 = new ImageView(), star5 = new ImageView();
            model.setSizeAndPosition(new ImageView[]{star1, star2, star3, star4, star5}, profile.getFavori(i).getGrade());
            pane.setId("pane"+i);
            pane.setPrefHeight(this.prefHeight);
            pane.setPrefWidth(this.prefWidth);
            restaurantName.setText(profile.getFavori(i).getName());
            pane.getChildren().add(image);
            pane.getChildren().add(restaurantName);
            pane.getChildren().addAll(new ImageView[]{star1,star2,star3,star4,star5});
            pane.getChildren().addAll(schedule);
            panes.add(pane);

        }
        paneListView.setItems(panes);
        System.out.println(paneListView.getWidth());
        for(int i=0; i<paneListView.getItems().size();i++) {
            int finalI = i;
            paneListView.getItems().get(i).setOnMouseClicked(event -> model.accessRestaurantPage(profile.getFavori(finalI),profile));
            System.out.println(paneListView.getHeight());
            if((i+1) == paneListView.getItems().size()){
                goBack.setLayoutX(paneListView.getWidth()-goBack.getWidth());
                goBack.setLayoutY(paneListView.getHeight()-goBack.getHeight());

            }
        }
        //goBack.setOnMouseClicked(event -> model.accessingTo(profile,View.PROFILE,View.CSS_FILE,"ProfileController"));
    }
}
