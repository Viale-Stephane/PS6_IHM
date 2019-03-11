package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import sample.*;
import sample.models.AddLocationModel;

import java.util.ArrayList;


public class AddLocationController {
    @FXML
    private TextField restaurant;
    @FXML
    private TextField adress;
    @FXML
    private TextField website;
    @FXML
    private TextField phoneNumber;
    @FXML
    private TextField monday;
    @FXML
    private TextField thuesday;
    @FXML
    private TextField wednesday;
    @FXML
    private TextField thursday;
    @FXML
    private TextField friday;
    @FXML
    private TextField saturday;
    @FXML
    private TextField sunday;
    @FXML
    private TextField textFieldFiltres;

    @FXML
    private SplitMenuButton splitMenuFiltre;

    @FXML
    private Slider slideBarPrice;
    @FXML
    private  Slider slideBarDistance;

    @FXML
    private Label grade;
    @FXML
    private Label labelPrice;
    @FXML
    private Label labelDistance;

    @FXML
    private Button cancel;
    @FXML
    private Button save;

    @FXML
    private ToggleButton toggleButtonRestaurant;
    @FXML
    private ToggleButton toggleButtonCommerce;

    @FXML
    private ImageView star1;
    @FXML
    private ImageView star2;
    @FXML
    private ImageView star3;
    @FXML
    private ImageView star4;
    @FXML
    private ImageView star5;
    @FXML
    private ImageView image;

    @FXML
    private Pane testDrop;

    //--------------------------------------
    AddLocationModel model = new AddLocationModel();
    ArrayList<Tag> researchedTags = new ArrayList<>();
    private Image picture;

    public void init(Profile profile) {

        image.setPreserveRatio(false);
        image.setX(0);
        image.setY(0);

        testDrop.setOnDragExited(event -> {
            Dragboard db = event.getDragboard();
            if (db.hasFiles()) {
                db.getFiles().forEach(file -> {
                    try {
                        picture = new Image(file.toURI().toURL().toExternalForm(), 100, 100, false, true);
                        image.setImage(picture);
                    } catch (Exception exc) {
                        System.out.println("Could not load image "+file);
                    }
                });
        }});


        toggleButtonRestaurant.setSelected(true);

        toggleButtonRestaurant.setOnMouseClicked(event -> model.swapKindOfLocation("restaurant",toggleButtonCommerce,toggleButtonRestaurant));
        toggleButtonCommerce.setOnMouseClicked(event -> model.swapKindOfLocation("commerce",toggleButtonCommerce,toggleButtonRestaurant));
        textFieldFiltres.setEditable(false);
        model.initFilters(Tag.getFullList(),splitMenuFiltre);
        for(MenuItem menuItem : splitMenuFiltre.getItems()){
            menuItem.setOnAction(event -> model.addFiltre("#"+menuItem.getText()+" ",researchedTags,textFieldFiltres));
        }

        model.initSlider(slideBarDistance, 20,10);
        model.initSlider(slideBarPrice,100,50);
        slideBarPrice.setOnMouseClicked(event -> labelPrice.setText("Prix : "+((int)slideBarPrice.getValue())+"€"));
        slideBarPrice.setOnMouseDragged(event -> labelPrice.setText("Prix : "+((int)slideBarPrice.getValue())+"€"));
        slideBarDistance.setOnMouseClicked(event -> labelDistance.setText("Distance : "+((int)slideBarDistance.getValue())+"km"));
        slideBarDistance.setOnMouseDragged(event -> labelDistance.setText("Distance : "+((int)slideBarDistance.getValue())+"km"));

        model.clickStar(3,star1,star2,star3,star4,star5, grade);
        star1.setOnMouseClicked(event -> model.clickStar(1, star1, star2, star3, star4, star5, grade));
        star2.setOnMouseClicked(event -> model.clickStar(2, star1, star2, star3, star4, star5, grade));
        star3.setOnMouseClicked(event -> model.clickStar(3, star1, star2, star3, star4, star5, grade));
        star4.setOnMouseClicked(event -> model.clickStar(4, star1, star2, star3, star4, star5, grade));
        star5.setOnMouseClicked(event -> model.clickStar(5, star1, star2, star3, star4, star5, grade));


        save.setOnAction( event -> model.addLocation(restaurant.getText(),toggleButtonRestaurant.isSelected(),adress.getText(),website.getText(),phoneNumber.getText(), new String[]{monday.getText(),thuesday.getText(),wednesday.getText(),thursday.getText(),friday.getText(),saturday.getText(),sunday.getText()},Integer.parseInt(grade.getText()),slideBarPrice.getValue(), slideBarDistance.getValue(),researchedTags, profile,picture));
        cancel.setOnAction( event -> model.comeBackToHome(profile));
    }
}
