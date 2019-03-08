package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.Profile;
import sample.View;
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

    @FXML
    private ImageView idPicture;
    @FXML
    private ImageView gearButton;

    //----------------------------------
    ProfileModel model =new ProfileModel();

    Image gearButtonPicture = new Image("sample/data/Images/gear_button.png");

    public void init(Profile profile) {
        idPicture.setImage(profile.getProfileImage());
        gearButton.setImage(this.gearButtonPicture);
        lastName.setText(profile.getFirstName());
        firstName.setText(profile.getLastName());
        email.setText(profile.getEmail());
        history.setOnMouseClicked( event -> model.accessingTo(profile, View.HISTORY,View.CSS_FILE,"HistoryController"));
        favorites.setOnMouseClicked( event -> model.accessingTo(profile,View.FAVORITES,View.CSS_FILE,"FavorisController"));
        myRatings.setOnMouseClicked( event -> model.accessingTo(profile,View.MY_RATINGS,View.CSS_FILE,"MyRatingsController"));
        addLocation.setOnMouseClicked( event -> model.accessingTo(profile,View.NEW_LOCATION,View.CSS_FILE,"AddLocationController"));
        logOut.setOnMouseClicked( event -> model.accessingTo(profile,"../"+View.HOME_OFFLINE, View.CSS_FILE,"OfflineController"));
        goBack.setOnMouseClicked(event -> model.accessingTo(profile,View.HOME_ONLINE,View.CSS_FILE,"OnlineController"));
    }

}
