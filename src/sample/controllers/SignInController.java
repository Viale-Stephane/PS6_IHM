package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sample.Profile;
import sample.View;
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
        logInLink.setOnAction(event -> model.accessingTo(new Profile(null,null,null,null,null), View.LOG_IN,View.CSS_FILE,"LoginController"));
        buttonRetour.setOnAction(event -> model.accessingTo(new Profile(null,null,null,null,null), "../"+View.HOME_OFFLINE,View.CSS_FILE,"OfflineController"));
        button.setOnAction(event -> model.creatingProfile(inputUsername.getText(),inputEmail.getText(),inputPassword.getText(),firstName.getText(),lastName.getText()));
    }
}
