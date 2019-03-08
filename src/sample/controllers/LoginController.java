package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import sample.Profile;
import sample.View;
import sample.models.LoginModel;


public class LoginController {
    LoginModel model = new LoginModel();

    @FXML
    private PasswordField inputPassword;
    @FXML
    private TextField inputUsername;


    @FXML
    private Button button;




    @FXML
    private Hyperlink buttonRetour;
    @FXML
    private Hyperlink signInLink;
    @FXML
    private Hyperlink passwordForget;

    @FXML
    private Label passwordIs;

    public void init() {
        button.setOnAction(event -> model.connecting(inputUsername.getText(), inputPassword.getText()));
        signInLink.setOnAction(event -> model.accessingTo(new Profile(), View.SIGN_IN,View.CSS_FILE,"SignInController"));
        buttonRetour.setOnAction(event -> model.accessingTo(new Profile(),"../"+View.HOME_OFFLINE,View.CSS_FILE,"OfflineController"));
        passwordForget.setOnAction(event -> passwordIs.setText(model.getPassword(inputUsername.getText())));
    }
}
