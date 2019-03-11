package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import sample.Profile;
import sample.View;
import sample.models.SignInModel;


public class SignInController {
    @FXML
    private Pane mainPane;

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
    private Text result;

    @FXML
    private Hyperlink logInLink;
    @FXML
    private Hyperlink buttonRetour;
    //----------------------------------------
    SignInModel model = new SignInModel();

    public void init(){
        logInLink.setOnAction(event -> model.accessingTo(mainPane, new Profile(), View.LOG_IN,View.CSS_FILE,"LoginController"));
        buttonRetour.setOnAction(event -> model.comeBackToHome(new Profile()));
        result.setWrappingWidth(185);
        button.setOnAction(event -> result.setText(model.creatingProfile(inputUsername.getText(),inputEmail.getText(),inputPassword.getText(),inputPasswordVerification.getText(),firstName.getText(),lastName.getText())));

    }
}
