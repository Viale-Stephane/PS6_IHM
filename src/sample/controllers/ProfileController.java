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
    private Button goBack;

    @FXML
    private Label lastName;
    @FXML
    private Label firstName;
    @FXML
    private Label email;

    //----------------------------------
    ProfileModel model =new ProfileModel();



    public void init(Profile profile) {
        lastName.setText(profile.getFirstName());
        firstName.setText(profile.getLastName());
        email.setText(profile.getEmail());
        history.setOnMouseClicked( event -> model.accessingTo("history",profile));
        favorites.setOnMouseClicked( event -> model.accessingTo("favorites",profile));
        myRatings.setOnMouseClicked( event -> model.accessingTo("myRatings",profile));
        addLocation.setOnMouseClicked( event -> model.accessingTo("addLocation",profile));
        logOut.setOnMouseClicked( event -> model.accessingTo("logOut",profile));
        goBack.setOnMouseClicked(event -> model.goBack(profile));
    }

}
