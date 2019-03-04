package sample.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import sample.*;
import sample.models.RestaurantListModel;



public class RestaurantListController {
    @FXML
    private ListView<Pane> paneList;

    @FXML
    private Button goBack;

    //-----------------------------------------------------------

    private ObservableList<Pane> panes = FXCollections.observableArrayList();
    private ObservableList<Restaurant> items = FXCollections.observableArrayList();



    RestaurantListModel model = new RestaurantListModel();


    public void init(Profile profile, RestaurantList whiteListedRestaurant){
        items.addAll(whiteListedRestaurant.getRestaurants());
        for(int i=0 ;i<whiteListedRestaurant.size();i++){
            Pane pane = new Pane();
            Label restaurantName = new Label();
            Label monday = new Label(), thuesday = new Label(), wednesday = new Label(), thursday = new Label(), friday = new Label(), saturday = new Label(), sunday = new Label();
            Label[] schedule = {monday,thuesday,wednesday,thursday,friday,saturday,sunday};
            model.setSchedule(schedule,whiteListedRestaurant.getRestaurants(i));
            ImageView star1 = new ImageView(), star2 = new ImageView(), star3 = new ImageView(), star4 = new ImageView(), star5 = new ImageView();
            model.setSizeAndPosition(new ImageView[]{star1, star2, star3, star4, star5}, whiteListedRestaurant.getRestaurants(i).getGrade());
            pane.setId("pane"+i);
            pane.setPrefHeight(100);
            pane.setPrefWidth(200);
            restaurantName.setText(whiteListedRestaurant.getRestaurants(i).getName());
            pane.getChildren().add(restaurantName);
            pane.getChildren().addAll(new ImageView[]{star1,star2,star3,star4,star5});
            pane.getChildren().addAll(schedule);
            panes.add(pane);

        }
        paneList.setItems(panes);
        for(int i=0; i<paneList.getItems().size();i++) {
            int finalI = i;
            paneList.getItems().get(i).setOnMouseClicked(event -> model.accessRestaurantPage(whiteListedRestaurant.getRestaurants(finalI),profile));
            if((i+1) == paneList.getItems().size()){
                goBack.setLayoutX(paneList.getWidth()-goBack.getWidth());
                goBack.setLayoutY(paneList.getHeight()-goBack.getHeight());

            }
        }
        goBack.setOnMouseClicked(event -> model.goBack(profile));
    }
}
