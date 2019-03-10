package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import sample.Profile;
import sample.View;
import sample.models.ApplicationInformationsModel;

public class ApplicationInformationsController {
    @FXML
    private Button goBack;
    @FXML
    private ImageView logo;

    private ApplicationInformationsModel model = new ApplicationInformationsModel();
    private Image logoImage = new Image(View.ORGANISATION_LOGO);

    public void init(Profile profile){
        logo.setImage(logoImage);
        goBack.setOnMouseClicked(event -> model.comeBackToHome(profile));
    }
}
