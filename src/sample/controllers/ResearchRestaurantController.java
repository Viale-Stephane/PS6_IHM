package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import sample.Profile;
import sample.Tag;
import sample.models.ResearchRestaurantModel;

import java.util.ArrayList;


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

    private int minStar = 0;



    ResearchRestaurantModel model = new ResearchRestaurantModel();
    ArrayList<String> researchedTags = new ArrayList<>();


    public void init(Profile profile){
        this.minStar = model.clickStar(0,filterStar1,filterStar2,filterStar3,filterStar4,filterStar5);
        toggleButtonRestaurant.setSelected(true);
        textFieldFiltres.setEditable(false);
        model.initFilters(Tag.getFullList(),splitMenuFiltre);

        toggleButtonRestaurant.setOnMouseClicked(event -> model.swapKindOfLocation("restaurant",toggleButtonCommerce,toggleButtonRestaurant));
        toggleButtonCommerce.setOnMouseClicked(event -> model.swapKindOfLocation("commerce",toggleButtonCommerce,toggleButtonRestaurant));
        filterStar1.setOnMouseClicked(event -> this.minStar = model.clickStar(1,filterStar1,filterStar2,filterStar3,filterStar4,filterStar5));
        filterStar2.setOnMouseClicked(event -> this.minStar = model.clickStar(2, filterStar1, filterStar2, filterStar3, filterStar4, filterStar5));
        filterStar3.setOnMouseClicked(event -> this.minStar = model.clickStar(3, filterStar1, filterStar2, filterStar3, filterStar4, filterStar5));
        filterStar4.setOnMouseClicked(event -> this.minStar = model.clickStar(4, filterStar1, filterStar2, filterStar3, filterStar4, filterStar5));
        filterStar5.setOnMouseClicked(event -> this.minStar = model.clickStar(5, filterStar1, filterStar2, filterStar3, filterStar4, filterStar5));
        model.initSlider(slideBarDistance, 20,10);
        model.initSlider(slideBarPrice,100,50);
        slideBarPrice.setOnMouseClicked(event -> labelPrice.setText("Prix : "+((int)slideBarPrice.getValue())+"€"));
        slideBarPrice.setOnMouseDragged(event -> labelPrice.setText("Prix : "+((int)slideBarPrice.getValue())+"€"));
        slideBarDistance.setOnMouseClicked(event -> labelDistance.setText("Distance : "+((int)slideBarDistance.getValue())+"km"));
        slideBarDistance.setOnMouseDragged(event -> labelDistance.setText("Distance : "+((int)slideBarDistance.getValue())+"km"));
        for(MenuItem menuItem : splitMenuFiltre.getItems()){
            menuItem.setOnAction(event -> model.addFiltre(menuItem.getText(),researchedTags,textFieldFiltres));
        }
        buttonFiltre.setOnAction(event -> model.filter(this.minStar,toggleButtonRestaurant.isSelected(),slideBarPrice.getValue(),slideBarDistance.getValue(),this.researchedTags,profile));

    }
}
