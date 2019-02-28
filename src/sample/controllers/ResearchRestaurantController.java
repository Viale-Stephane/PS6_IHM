package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.View;
import sample.models.ResearchRestaurantModel;


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

    //-------------------------------------------------------------------

    private int minStar;
    Image FULL_STAR = new Image(View.FULL_STAR);
    Image EMPTY_STAR = new Image(View.EMPTY_STAR);


    ResearchRestaurantModel model = new ResearchRestaurantModel();

    public void filter(int minStar, boolean restaurant, double maxPrice, double maxDistance){
        model.filter(minStar, restaurant, maxPrice, maxDistance);
    }

    public void clickStar(int number){
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
        this.minStar = number;
    }

    public void initSlider(Slider slider, int tickUnit, int blockIncrement){
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setMajorTickUnit(tickUnit);
        slider.setBlockIncrement(blockIncrement);
    }

    public void init(){
        filterStar5.setImage(EMPTY_STAR);
        filterStar4.setImage(EMPTY_STAR);
        filterStar3.setImage(EMPTY_STAR);
        filterStar2.setImage(EMPTY_STAR);
        filterStar1.setImage(EMPTY_STAR);
        toggleButtonRestaurant.fire();
        buttonFiltre.setOnAction(event -> filter(this.minStar,toggleButtonRestaurant.isSelected(),slideBarPrice.getValue(),slideBarDistance.getValue()));
        filterStar1.setOnMouseClicked(event -> clickStar(1));
        filterStar2.setOnMouseClicked(event -> clickStar(2));
        filterStar3.setOnMouseClicked(event -> clickStar(3));
        filterStar4.setOnMouseClicked(event -> clickStar(4));
        filterStar5.setOnMouseClicked(event -> clickStar(5));

        initSlider(slideBarDistance, 20,10);
        initSlider(slideBarPrice,100,50);

        slideBarDistance.setOnMouseClicked(event -> System.out.println(slideBarDistance.getValue()));
    }
}
