package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import sample.View;
import sample.models.OfflineModel;


public class OfflineController {
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

    @FXML
    private Pane rightPane;
    @FXML
    private Pane leftPane;

    //-------------------------------
    Image SEARCH_ICON = new Image(View.SEARCH_ICON);
    Image DROP_DOWN_MENU = new Image(View.DROP_DOWN_MENU);
    Image ORGANISATION_LOGO = new Image(View.ORGANISATION_LOGO);

    OfflineModel offlineModel = new OfflineModel();






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
        researchBar.setOnKeyPressed(event ->{
            if(event.getCode().toString().equals("ENTER"))
                System.out.println(offlineModel.search(researchBar.getText()));
        });
        search.setOnMouseClicked(event -> System.out.println(offlineModel.search(researchBar.getText())));
        filterButton.setOnMouseClicked( event -> System.out.println(offlineModel.compute("filter")));
        connexion.setOnMouseClicked( event -> System.out.println(offlineModel.compute("logIn")));
        register.setOnMouseClicked( event -> System.out.println(offlineModel.compute("signIn")));
        info.setOnMouseClicked( event -> System.out.println(offlineModel.compute("informations")));
    }
}
