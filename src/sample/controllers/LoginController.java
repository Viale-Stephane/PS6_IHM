package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import sample.Profile;
import sample.View;
import sample.models.LoginModel;


public class LoginController {
    @FXML
    private Pane mainPane;

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

    LoginModel model = new LoginModel();

    public void init() {
        button.setOnAction(event -> model.connecting(inputUsername.getText(), inputPassword.getText()));
        signInLink.setOnAction(event -> model.accessingTo(mainPane, new Profile(), View.SIGN_IN,View.CSS_FILE,"SignInController"));
        buttonRetour.setOnAction(event -> model.comeBackToHome(new Profile()));
        passwordForget.setOnAction(event -> {
            passwordIs.setText(model.getPassword(inputUsername.getText()));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText(passwordIs.getText());
            alert.showAndWait();

        });
    }
}
