package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import sample.Profile;
import sample.View;
import sample.models.OfflineModel;


public class OfflineController {
    @FXML
    private TextField researchBar;


    @FXML
    private Button button;
    @FXML
    private Button button2;

    @FXML
    private ImageView search;
    @FXML
    private ImageView filterButton;
    @FXML
    private ImageView info;

    @FXML
    public Pane leftPane;
    @FXML
    private Pane midPane;
    @FXML
    private Pane rightPane;

    @FXML
    private Text errorDisplay;
    //-------------------------------
    private Image SEARCH_ICON = new Image(View.SEARCH_ICON);
    private Image DROP_DOWN_MENU = new Image(View.DROP_DOWN_MENU);
    private Image ORGANISATION_LOGO = new Image(View.ORGANISATION_LOGO);

    private OfflineModel offlineModel = new OfflineModel();

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

        offlineModel.clearPanes(leftPane, rightPane);

        researchBar.setOnKeyPressed(event ->{
            if(event.getCode().toString().equals("ENTER")) {
                rightPane.getChildren().clear();
                errorDisplay.setText(offlineModel.search(leftPane, researchBar.getText(), new Profile()));
            }
        });

        search.setOnMouseClicked(event ->{
            rightPane.getChildren().clear();
            errorDisplay.setText(offlineModel.search(leftPane, researchBar.getText(),new Profile()));
        });
        filterButton.setOnMouseClicked( event -> researchBar.setText(offlineModel.accessingTo(false, leftPane, rightPane, new Profile(),View.MENU_FILTER,View.CSS_FILE,"ResearchRestaurantController")));
        button2.setOnMouseClicked( event -> researchBar.setText(offlineModel.accessingTo(true, leftPane, rightPane, new Profile(),View.LOG_IN,View.CSS_FILE,"LoginController")));
        button.setOnMouseClicked( event -> researchBar.setText(offlineModel.accessingTo(true, leftPane, rightPane, new Profile(),View.SIGN_IN,View.CSS_FILE,"SignInController")));
        info.setOnMouseClicked( event -> researchBar.setText(offlineModel.accessingTo(true, leftPane, rightPane, new Profile(),View.INFORMATIONS,View.CSS_FILE,"ApplicationInformationsController")));

        midPane.setOnMouseClicked(event -> offlineModel.clearPanes(leftPane, rightPane));
        leftPane.setOnMouseClicked(event -> offlineModel.clearPanes(rightPane));
        rightPane.setOnMouseClicked(event -> offlineModel.clearPanes(leftPane));
    }
}
