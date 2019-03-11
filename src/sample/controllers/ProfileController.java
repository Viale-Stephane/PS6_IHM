package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import sample.Profile;
import sample.View;
import sample.models.ProfileModel;


public class ProfileController {
    @FXML
    private Pane mainPane;
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

    @FXML
    private ImageView idPicture;

    //----------------------------------
    ProfileModel model =new ProfileModel();

    public void init(Profile profile) {
        Circle circle = new Circle();
        circle.setCenterX(idPicture.getX()+idPicture.getFitWidth()/2);
        circle.setCenterY(idPicture.getFitHeight()/4);
        circle.setRadius(17);
        idPicture.setClip(circle);
        idPicture.setImage(profile.getProfileImage());
        lastName.setText(profile.getFirstName());
        firstName.setText(profile.getLastName());
        email.setText(profile.getEmail());
        history.setOnMouseClicked( event -> model.accessingTo(mainPane, profile, View.HISTORY,View.CSS_FILE,"HistoryController"));
        favorites.setOnMouseClicked( event -> model.accessingTo(mainPane, profile,View.FAVORITES,View.CSS_FILE,"FavorisController"));
        myRatings.setOnMouseClicked( event -> model.accessingTo(mainPane, profile,View.MY_RATINGS,View.CSS_FILE,"MyRatingsController"));
        addLocation.setOnMouseClicked( event -> model.accessingTo(mainPane, profile,View.NEW_LOCATION,View.CSS_FILE,"AddLocationController"));
        logOut.setOnMouseClicked( event -> model.comeBackToHome(new Profile()));
        goBack.setOnMouseClicked(event -> model.comeBackToHome(profile));
    }

}
