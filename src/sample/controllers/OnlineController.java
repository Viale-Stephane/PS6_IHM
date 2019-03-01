package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import sample.Profile;
import sample.models.OnlineModel;

public class OnlineController {
    OnlineModel model = new OnlineModel();

    @FXML
    private Button filterButton;
    @FXML
    private Button profile;
    @FXML
    private Button info;

    @FXML
    private TextField researchBar;

    public void init(Profile profile){
        this.profile.setOnAction(event -> this.model.moveToProfile(profile));
        filterButton.setOnAction(event -> this.model.filter(profile));

    }
}
