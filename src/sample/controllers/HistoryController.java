package sample.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import sample.History;
import sample.Profile;
import sample.View;
import sample.models.HistoryModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class HistoryController {
    @FXML
    private ListView<Pane> historyList;
    @FXML
    private Button goBack;

    //--------------------------------------------------------

    HistoryModel model = new HistoryModel();

    private ObservableList<Pane> panes = FXCollections.observableArrayList();
    private ObservableList<History> items = FXCollections.observableArrayList();

    private int prefHeight = 100;
    private int prefWidth = 200;

    public void init(Profile profile) {
        ArrayList<History> history = profile.getHistory();
        items.addAll(history);
        for (int i = 0; i < history.size(); i++) {
            Pane pane = new Pane();
            Label restaurantName = new Label();
            Label date = new Label();
            ImageView star1 = new ImageView(), star2 = new ImageView(), star3 = new ImageView(), star4 = new ImageView(), star5 = new ImageView();
            ImageView image = new ImageView(profile.getFavori(i).getRestaurantPicture());
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            String today = formatter.format(history.get(i).getDate());
            date.setText(today);
            date.setLayoutY(40);

            image.setPreserveRatio(false);
            image.setFitWidth(this.prefWidth/2);
            image.setFitHeight(this.prefHeight);
            image.setX(this.prefWidth/2);
            image.setY(0);

            model.setSizeAndPosition(new ImageView[]{star1, star2, star3, star4, star5}, history.get(i).getHistory().getGrade());
            pane.setId("pane"+i);
            pane.setPrefHeight(this.prefHeight);
            pane.setPrefWidth(this.prefWidth);
            restaurantName.setText(history.get(i).getHistory().getName());
            pane.getChildren().add(image);
            pane.getChildren().add(restaurantName);
            pane.getChildren().add(date);
            pane.getChildren().addAll(new ImageView[]{star1,star2,star3,star4,star5});
            panes.add(pane);
        }
        historyList.setItems(panes);
        for(int i=0; i<historyList.getItems().size();i++) {
            int finalI = i;
            historyList.getItems().get(i).setOnMouseClicked((event -> model.accessRestaurantPage(history.get(finalI).getHistory(),profile)));
            if((i+1) == historyList.getItems().size()){
                goBack.setLayoutX(historyList.getWidth()-goBack.getWidth());
                goBack.setLayoutY(historyList.getHeight()-goBack.getHeight());

            }
        }
        //goBack.setOnMouseClicked(event -> model.accessingTo(profile, View.PROFILE,View.CSS_FILE,"ProfileController"));

    }
}
