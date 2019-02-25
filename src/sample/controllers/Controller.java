package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import sample.models.Model;

public class Controller {
    @FXML
    private Button filterButton;
    @FXML
    private TextField researchBar;
    @FXML
    private Button connexion;
    @FXML
    private Button register;
    @FXML
    private Button info;

    public void computeModel(String instruction){
        Model model = new Model();
        System.out.println(model.compute(instruction));
    }



    public void init() {
        filterButton.setOnAction( event -> computeModel("filter"));
        connexion.setOnAction( event -> computeModel("logIn"));
        register.setOnAction( event -> computeModel("signIn"));
        info.setOnAction( event -> computeModel("informations"));
    }
}
