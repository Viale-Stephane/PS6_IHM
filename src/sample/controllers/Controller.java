package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.Profile;
import sample.View;
import sample.models.Model;


public class Controller {
    @FXML
    private TextField researchBar;


    @FXML
    private Button connexion;
    @FXML
    private Button register;


    @FXML
    private ImageView search;
    @FXML
    private ImageView filterButton;
    @FXML
    private ImageView info;

    //-------------------------------
    Image SEARCH_ICON = new Image(View.SEARCH_ICON);
    Image DROP_DOWN_MENU = new Image(View.DROP_DOWN_MENU);
    Image ORGANISATION_LOGO = new Image(View.ORGANISATION_LOGO);


    public void computeModel(String instruction){
        Model model = new Model();
        System.out.println(model.compute(instruction));
    }



    public void init() {
        search.setX(researchBar.getPrefWidth());
        search.setY(-search.getFitHeight()/2);
        search.setImage(SEARCH_ICON);

        filterButton.setFitHeight(40);
        filterButton.setFitWidth(40);
        filterButton.setImage(DROP_DOWN_MENU);

        info.setFitHeight(40);
        info.setFitWidth(40);
        info.setX(550);
        info.setY(-info.getFitHeight()/2);
        info.setImage(ORGANISATION_LOGO);

        search.setOnMouseClicked(event -> System.out.println("test"));
        filterButton.setOnMouseClicked( event -> computeModel("filter"));
        connexion.setOnAction( event -> computeModel("logIn"));
        register.setOnAction( event -> computeModel("signIn"));
        info.setOnMouseClicked( event -> computeModel("informations"));
        researchBar.setOnAction( event -> computeModel("researchBar"));
    }
}
