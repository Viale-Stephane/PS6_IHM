package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import sample.models.ProfileModel;

public class ProfileController {
    @FXML
    private Button history;
    @FXML
    private Button favorites;
    @FXML
    private Button myRatings;
    @FXML
    private Button addLocation;

    @FXML
    private Button logOut;


    public void results(String instruction){
        ProfileModel profileModel =new ProfileModel();
        System.out.println(profileModel.compute(instruction));
    }

    public void init() {
        history.setOnAction( event -> results("history"));
        favorites.setOnAction( event -> results("favorites"));
        myRatings.setOnAction( event -> results("myRatings"));
        addLocation.setOnAction( event -> results("addLocation"));
        logOut.setOnAction( event -> results("logOut"));
    }

}
