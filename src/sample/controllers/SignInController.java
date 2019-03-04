package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sample.models.SignInModel;


public class SignInController {

    @FXML
    private PasswordField inputPassword;
    @FXML
    private PasswordField inputPasswordVerification;
    @FXML
    private TextField inputEmail;
    @FXML
    private TextField inputUsername;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;

    @FXML
    private Button button;



    @FXML
    private Hyperlink logInLink;
    @FXML
    private Hyperlink buttonRetour;
    //----------------------------------------
    SignInModel model = new SignInModel();

    public void init(){
        logInLink.setOnAction(event -> model.login());
        buttonRetour.setOnAction(event -> model.offline());
        button.setOnAction(event -> model.creatingProfile(inputUsername.getText(),inputEmail.getText(),inputPassword.getText(),firstName.getText(),lastName.getText()));
    }
}
