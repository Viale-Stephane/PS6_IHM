package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.Tag;
import sample.View;
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

    @FXML
    private MenuItem filtre1;

    @FXML
    private MenuItem filtre2;

    //-------------------------------------------------------------------

    private int minStar;
    Image FULL_STAR = new Image(View.FULL_STAR);
    Image EMPTY_STAR = new Image(View.EMPTY_STAR);


    ResearchRestaurantModel model = new ResearchRestaurantModel();
    ArrayList<String> researchedTags = new ArrayList<>();

    public void filter(int minStar, boolean restaurant, double maxPrice, double maxDistance, ArrayList<String> researchedTags){
        model.filter(minStar, restaurant, maxPrice, maxDistance, researchedTags);
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

    public void swapKindOfLocation(String location){
        switch(location){
            case "restaurant":
                toggleButtonCommerce.setSelected(false);
                toggleButtonRestaurant.setSelected(true);
                break;
            case "commerce":
                toggleButtonRestaurant.setSelected(false);
                toggleButtonCommerce.setSelected(true);
                break;
        }
    }

    public boolean addFiltre(String newFiltre){
            if(researchedTags.contains("#"+newFiltre+" ")){
                researchedTags.remove("#"+newFiltre+" ");
                textFieldFiltres.setText(researchedTags.toString());
                return false;
            }
        researchedTags.add("#"+newFiltre+" ");
        textFieldFiltres.setText(researchedTags.toString());
        return true;
    }

    public void initFilters(){
        int i = 0;
        for(Tag tag : Tag.getFullList())
        splitMenuFiltre.getItems().add(new MenuItem(tag.toString()));
        for(MenuItem menuItem : splitMenuFiltre.getItems()){
            menuItem.setId("filtre"+i);
            i++;
        }
    }

    public void init(){
        filterStar5.setImage(EMPTY_STAR);
        filterStar4.setImage(EMPTY_STAR);
        filterStar3.setImage(EMPTY_STAR);
        filterStar2.setImage(EMPTY_STAR);
        filterStar1.setImage(EMPTY_STAR);
        toggleButtonRestaurant.setSelected(true);
        textFieldFiltres.setEditable(false);
        initFilters();

        toggleButtonRestaurant.setOnMouseClicked(event -> swapKindOfLocation("restaurant"));
        toggleButtonCommerce.setOnMouseClicked(event -> swapKindOfLocation("commerce"));
        filterStar1.setOnMouseClicked(event -> clickStar(1));
        filterStar2.setOnMouseClicked(event -> clickStar(2));
        filterStar3.setOnMouseClicked(event -> clickStar(3));
        filterStar4.setOnMouseClicked(event -> clickStar(4));
        filterStar5.setOnMouseClicked(event -> clickStar(5));
        initSlider(slideBarDistance, 20,10);
        initSlider(slideBarPrice,100,50);
        slideBarPrice.setOnMouseClicked(event -> labelPrice.setText("Prix : "+((int)slideBarPrice.getValue())+"€"));
        slideBarDistance.setOnMouseClicked(event -> labelDistance.setText("Distance : "+((int)slideBarDistance.getValue())+"km"));
        for(MenuItem menuItem : splitMenuFiltre.getItems()){
            menuItem.setOnAction(event -> addFiltre(menuItem.getText()));
        }
        //filtre1.setText(Tag.Burger.toString());
        //filtre1.setOnAction(event -> addFiltre((filtre1.getText())));
        //filtre2.setOnAction(event -> addFiltre(filtre2.getText()));

        buttonFiltre.setOnAction(event -> filter(this.minStar,toggleButtonRestaurant.isSelected(),slideBarPrice.getValue(),slideBarDistance.getValue(),this.researchedTags));

    }
}
