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
    private Pane mainPane;
    @FXML
    private ListView<Pane> paneList;
    @FXML
    private Button goBack;

    //-----------------------------------------------------------

    private ObservableList<Pane> panes = FXCollections.observableArrayList();
    private ObservableList<Restaurant> items = FXCollections.observableArrayList();

    private int prefHeight = 100;
    private int prefWidth = 218;

    RestaurantListModel model = new RestaurantListModel();


    public void init(Profile profile, RestaurantList whiteListedRestaurant){
        items.addAll(whiteListedRestaurant.getRestaurants());
        for(int i=0 ;i<whiteListedRestaurant.size();i++){
            Pane pane = new Pane();
            Label restaurantName = new Label();
            Label monday = new Label(), thuesday = new Label(), wednesday = new Label(), thursday = new Label(), friday = new Label(), saturday = new Label(), sunday = new Label();
            ImageView image = new ImageView(whiteListedRestaurant.getRestaurant(i).getRestaurantPicture());
            image.setPreserveRatio(false);
            image.setFitWidth(this.prefWidth/2);
            image.setFitHeight(this.prefHeight);
            image.setX(this.prefWidth/2);
            image.setY(0);

            Label[] schedule = {monday,thuesday,wednesday,thursday,friday,saturday,sunday};
            model.setSchedule(schedule,whiteListedRestaurant.getRestaurant(i));

            ImageView star1 = new ImageView(), star2 = new ImageView(), star3 = new ImageView(), star4 = new ImageView(), star5 = new ImageView();
            model.setSizeAndPosition(new ImageView[]{star1, star2, star3, star4, star5}, whiteListedRestaurant.getRestaurant(i).getGrade());

            pane.setId("pane"+i);
            pane.setPrefHeight(this.prefHeight);
            pane.setPrefWidth(this.prefWidth);

            restaurantName.setText(whiteListedRestaurant.getRestaurant(i).getName());

            pane.getChildren().add(image);
            pane.getChildren().add(restaurantName);
            pane.getChildren().addAll(new ImageView[]{star1,star2,star3,star4,star5});
            pane.getChildren().addAll(schedule);
            panes.add(pane);
        }

        paneList.setItems(panes);

        for(int i=0; i<paneList.getItems().size();i++) {
            int finalI = i;
            paneList.getItems().get(i).setOnMouseClicked(event -> model.accessRestaurantPage(mainPane,whiteListedRestaurant.getRestaurant(finalI),profile));
            if((i+1) == paneList.getItems().size()){
                goBack.setLayoutX(paneList.getWidth()-goBack.getWidth());
                goBack.setLayoutY(paneList.getHeight()-goBack.getHeight());

            }
        }

        goBack.setOnMouseClicked(event -> model.accessingTo(mainPane,profile,View.MENU_FILTER,View.CSS_FILE,"ResearchRestaurantController"));
    }
}
