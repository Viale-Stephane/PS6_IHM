package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import sample.Main;
import sample.RestaurantList;
import sample.Tag;
import sample.models.AddLocationModel;

import java.util.ArrayList;
import java.util.Arrays;


public class AddLocationController {
    @FXML
    private TextField restaurant;
    @FXML
    private TextField adress;
    @FXML
    private TextField website;
    @FXML
    private TextField phoneNumber;
    @FXML
    private TextField monday;
    @FXML
    private TextField thuesday;
    @FXML
    private TextField wednesday;
    @FXML
    private TextField thursday;
    @FXML
    private TextField friday;
    @FXML
    private TextField saturday;
    @FXML
    private TextField sunday;
    @FXML
    private Label grade;
    @FXML
    private Button cancel;
    @FXML
    private Button save;

    public void addLocation(){
        AddLocationModel model = new AddLocationModel();
        int averagePrice = 20, averageDistance = 20;
        String[] schedule = {monday.getText(),thuesday.getText(),wednesday.getText(),thursday.getText(),friday.getText(),saturday.getText(),sunday.getText()};
        model.addLocation(restaurant.getText(), true, adress.getText(),website.getText(),phoneNumber.getText(),schedule,Integer.parseInt(grade.getText()), averagePrice, averageDistance,new ArrayList<Tag>(Arrays.asList(Tag.Francais, Tag.Fromage, Tag.Healthy)));
        Main.restaurantList.printRestaurants();
    }

    public void changePage(String instruction){
        AddLocationModel model = new AddLocationModel();
        model.compute(instruction);
    }

    public void init() {
        save.setOnAction( event -> addLocation());
        cancel.setOnAction( event -> changePage("cancel"));
    }
}
