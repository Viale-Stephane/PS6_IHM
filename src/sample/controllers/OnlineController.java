package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import sample.Profile;
import sample.View;
import sample.models.OnlineModel;


public class OnlineController {
    @FXML
    private TextField researchBar;

    @FXML
    private ImageView search;
    @FXML
    private ImageView profile;
    @FXML
    private ImageView filterButton;
    @FXML
    private ImageView info;

    @FXML
    private Pane rightPane;
    @FXML
    private Pane midPane;
    @FXML
    private Pane leftPane;

    //-------------------------------
    private Image SEARCH_ICON = new Image(View.SEARCH_ICON);
    private Image DROP_DOWN_MENU = new Image(View.DROP_DOWN_MENU);
    private Image ORGANISATION_LOGO = new Image(View.ORGANISATION_LOGO);

    OnlineModel model = new OnlineModel();

    public void init(Profile profile){
        search.setX(researchBar.getPrefWidth());
        search.setY(-search.getFitHeight()/2);
        search.setImage(SEARCH_ICON);

        filterButton.setFitWidth(40);
        filterButton.setFitHeight(40);
        filterButton.setImage(DROP_DOWN_MENU);

        info.setFitHeight(40);
        info.setFitWidth(40);
        info.setX(550);
        info.setY(-info.getFitHeight()/2);
        info.setImage(ORGANISATION_LOGO);

        this.profile.setImage(profile.getProfileImage());
        this.profile.setFitWidth(40);
        this.profile.setFitHeight(40);
        this.profile.setX(550);
        this.profile.setY(-this.profile.getFitHeight()/2);
        Circle circle = new Circle();
        circle.setCenterX(this.profile.getX()+this.profile.getFitWidth()/2);
        circle.setRadius(17);
        this.profile.setClip(circle);

        this.profile.setOnMouseClicked(event -> this.model.accessingTo(true, leftPane, rightPane, profile,View.PROFILE,View.CSS_FILE,"ProfileController"));
        filterButton.setOnMouseClicked(event -> this.model.accessingTo(false, leftPane, rightPane, profile,View.MENU_FILTER,View.CSS_FILE,"ResearchRestaurantController"));
        info.setOnMouseClicked(event -> this.model.accessingTo(true, leftPane, rightPane, profile,View.INFORMATIONS,View.CSS_FILE,"ApplicationInformationsController"));

        researchBar.setOnKeyPressed(event ->{
            if(event.getCode().toString().equals("ENTER"))
                System.out.println(model.search(leftPane, researchBar.getText(),profile));
        });

        search.setOnMouseClicked(event -> System.out.println(model.search(leftPane, researchBar.getText(),profile)));

        midPane.setOnMouseClicked(event -> model.clearPanes(leftPane, rightPane));
        leftPane.setOnMouseClicked(event -> model.clearPanes(rightPane));
        rightPane.setOnMouseClicked(event -> model.clearPanes(leftPane));
    }
}
