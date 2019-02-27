package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.View;
import sample.models.ResearchRestaurantModel;

import java.io.InputStream;


public class ResearchRestaurantController {
    @FXML
    private ToggleButton toggleButtonRestaurant;
    @FXML
    private ToggleButton toggleButtonCommerce;
    @FXML
    private SplitMenuButton splitMenuFiltre;
    @FXML
    private TextField textFieldFiltres;
    @FXML
    private Slider slideBarPrice;
    @FXML
    private Label labelPrice;
    @FXML
    private Label priceMax;
    @FXML
    private Label priceMin;
    @FXML
    private Slider slideBarDistance;
    @FXML
    private Label labelDistance;
    @FXML
    private Label distanceMax;
    @FXML
    private Label distanceMin;
    @FXML
    private ImageView filterStar1;
    @FXML
    private ImageView filterStar2;
    @FXML
    private ImageView filterStar3;
    @FXML
    private ImageView filterStar4;
    @FXML
    private ImageView filterStar5;

    @FXML
    private Button buttonFiltre;

    Image FULL_STAR = new Image(View.FULL_STAR);
    Image EMPTY_STAR = new Image(View.EMPTY_STAR);


    ResearchRestaurantModel model = new ResearchRestaurantModel();

    public void filter(){
        model.filter();
    }

    public void click(int number){
        Image FULL_STAR = new Image("sample/data/Images/starFull.png");
        Image EMPTY_STAR = new Image(View.EMPTY_STAR);

        filterStar5.setImage(EMPTY_STAR);
        filterStar4.setImage(EMPTY_STAR);
        filterStar3.setImage(EMPTY_STAR);
        filterStar2.setImage(EMPTY_STAR);
        filterStar1.setImage(EMPTY_STAR);
        switch(number){
            case 5:
                filterStar5.setImage(FULL_STAR);
            case 4:
                filterStar4.setImage(FULL_STAR);
            case 3:
                filterStar3.setImage(FULL_STAR);
            case 2:
                filterStar2.setImage(FULL_STAR);
            case 1:
                filterStar1.setImage(FULL_STAR);
        }
    }

    public void init(){
        filterStar5.setImage(EMPTY_STAR);
        filterStar4.setImage(EMPTY_STAR);
        filterStar3.setImage(EMPTY_STAR);
        filterStar2.setImage(EMPTY_STAR);
        filterStar1.setImage(EMPTY_STAR);
        System.out.println(slideBarPrice.getValue());
        slideBarPrice.adjustValue(slideBarPrice.getValue());
        toggleButtonRestaurant.fire();
        buttonFiltre.setOnAction(event -> filter());
        filterStar1.setOnMouseClicked(event -> click(1));
        filterStar2.setOnMouseClicked(event -> click(2));
        filterStar3.setOnMouseClicked(event -> click(3));
        filterStar4.setOnMouseClicked(event -> click(4));
        filterStar5.setOnMouseClicked(event -> click(5));

    }
}
