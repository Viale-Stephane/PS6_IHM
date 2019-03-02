package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.Profile;
import sample.View;
import sample.models.ApplicationInformationsModel;

public class ApplicationInformationsController {

    @FXML
    private Button goBack;

    @FXML
    private ImageView logo;

    ApplicationInformationsModel model = new ApplicationInformationsModel();
    Image logoImage = new Image(View.ORGANISATION_LOGO);

    public void init(Profile profile){
        logo.setImage(logoImage);
        goBack.setOnMouseClicked(event -> model.goBack(profile));

    }
}
