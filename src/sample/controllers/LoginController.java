package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import sample.models.LoginModel;


public class LoginController {
    LoginModel model = new LoginModel();

    @FXML
    private PasswordField inputPassword;
    @FXML
    private TextField inputUsername;


    @FXML
    private Button buttonConnexion;
    @FXML
    private Button buttonRetour;




    @FXML
    private Hyperlink signInLink;
    @FXML
    private Hyperlink passwordForget;

    @FXML
    private Label passwordIs;

    public void init() {
        buttonConnexion.setOnAction(event -> model.connecting(inputUsername.getText(), inputPassword.getText()));
        signInLink.setOnAction(event -> model.signIn());
        buttonRetour.setOnAction(event -> model.offline());
        passwordForget.setOnAction(event -> passwordIs.setText(model.getPassword(inputUsername.getText())));
    }
}
