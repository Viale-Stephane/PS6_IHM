package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import sample.Profile;
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

    @FXML
    private Label lastName;
    @FXML
    private Label firstName;
    @FXML
    private Label email;

    //----------------------------------



    public void results(String instruction, Profile profile){
        ProfileModel profileModel =new ProfileModel();
        System.out.println(profileModel.accessingTo(instruction, profile));
    }

    public void init(Profile profile) {
        lastName.setText(profile.getFirstName());
        firstName.setText(profile.getLastName());
        email.setText(profile.getEmail());
        history.setOnAction( event -> results("history",profile));
        favorites.setOnAction( event -> results("favorites",profile));
        myRatings.setOnAction( event -> results("myRatings",profile));
        addLocation.setOnAction( event -> results("addLocation",profile));
        logOut.setOnAction( event -> results("logOut",profile));
    }

}
