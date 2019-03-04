package sample.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import sample.History;
import sample.Profile;
import sample.Restaurant;
import sample.models.HistoryModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class HistoryController {
    @FXML
    private ListView<Pane> historyList;

    //--------------------------------------------------------

    HistoryModel model = new HistoryModel();

    private ObservableList<Pane> panes = FXCollections.observableArrayList();
    private ObservableList<History> items = FXCollections.observableArrayList();


    public void init(Profile profile) {
        ArrayList<History> history = profile.getHistory();
        items.addAll(history);
        for (int i = 0; i < history.size(); i++) {
            Pane pane = new Pane();
            Label restaurantName = new Label();
            Label date = new Label();
            ImageView star1 = new ImageView(), star2 = new ImageView(), star3 = new ImageView(), star4 = new ImageView(), star5 = new ImageView();
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            String today = formatter.format(history.get(i).getDate());
            date.setText(today);
            date.setLayoutY(40);

            model.setSizeAndPosition(new ImageView[]{star1, star2, star3, star4, star5}, history.get(i).getHistory().getGrade());
            pane.setId("pane"+i);
            pane.setPrefHeight(100);
            pane.setPrefWidth(200);
            restaurantName.setText(history.get(i).getHistory().getName());
            pane.getChildren().add(restaurantName);
            pane.getChildren().add(date);
            pane.getChildren().addAll(new ImageView[]{star1,star2,star3,star4,star5});
            panes.add(pane);
        }
        historyList.setItems(panes);
        for(int i=0; i<historyList.getItems().size();i++) {
            int finalI = i;
            historyList.getItems().get(i).setOnMouseClicked((event -> model.accessRestaurantPage(history.get(finalI).getHistory(),profile)));
        }
    }
}
