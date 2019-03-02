package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.*;
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

    @FXML
    private ImageView star1;
    @FXML
    private ImageView star2;
    @FXML
    private ImageView star3;
    @FXML
    private ImageView star4;
    @FXML
    private ImageView star5;
    //--------------------------------------

    Image FULL_STAR = new Image(View.FULL_STAR);
    Image EMPTY_STAR = new Image(View.EMPTY_STAR);

    public void addLocation(){
        AddLocationModel model = new AddLocationModel();
        int averagePrice = 20, averageDistance = 20;
        String[] schedule = {monday.getText(),thuesday.getText(),wednesday.getText(),thursday.getText(),friday.getText(),saturday.getText(),sunday.getText()};
        model.addLocation(restaurant.getText(), true, adress.getText(),website.getText(),phoneNumber.getText(),schedule,Integer.parseInt(grade.getText()), averagePrice, averageDistance,new ArrayList<Tag>(Arrays.asList(Tag.Francais, Tag.Fromage, Tag.Healthy)));
        Main.restaurantList.printRestaurants();
    }

    public void changePage(String instruction, Profile profile){
        AddLocationModel model = new AddLocationModel();
        model.compute(instruction, profile);
    }

    public void clickStar(int number){
        star5.setImage(EMPTY_STAR);
        star4.setImage(EMPTY_STAR);
        star3.setImage(EMPTY_STAR);
        star2.setImage(EMPTY_STAR);
        star1.setImage(EMPTY_STAR);
        switch(number){
            case 5:
                star5.setImage(FULL_STAR);
            case 4:
                star4.setImage(FULL_STAR);
            case 3:
                star3.setImage(FULL_STAR);
            case 2:
                star2.setImage(FULL_STAR);
            case 1:
                star1.setImage(FULL_STAR);
        }
        grade.setText(Integer.toString(number));
    }

    public void init(Profile profile) {
        star5.setImage(EMPTY_STAR);
        star4.setImage(EMPTY_STAR);
        star3.setImage(FULL_STAR);
        star2.setImage(FULL_STAR);
        star1.setImage(FULL_STAR);
        star1.setOnMouseClicked(event -> clickStar(1));
        star2.setOnMouseClicked(event -> clickStar(2));
        star3.setOnMouseClicked(event -> clickStar(3));
        star4.setOnMouseClicked(event -> clickStar(4));
        star5.setOnMouseClicked(event -> clickStar(5));


        save.setOnAction( event -> addLocation());
        cancel.setOnAction( event -> changePage("cancel", profile));
    }
}
