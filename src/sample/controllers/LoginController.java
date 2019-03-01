package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import sample.models.LoginModel;


public class LoginController {
    LoginModel model = new LoginModel();

    @FXML
    private TextField inputPassword;
    @FXML
    private TextField inputUsername;
    @FXML
    private Button buttonConnexion;
    @FXML
    private Hyperlink signInLink;

    public void init(){
        buttonConnexion.setOnAction(event -> model.connecting(inputUsername.getText(),inputPassword.getText()));
        signInLink.setOnAction(event -> model.signIn());
    }
}
