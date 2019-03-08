package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.Profile;
import sample.Restaurant;
import sample.View;
import sample.models.RestaurantPageModel;

public class RestaurantPageController {
    @FXML
    private ImageView image;
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
    private ImageView phone;
    @FXML
    private ImageView websiteImage;
    @FXML
    private ImageView returnToHome;

    @FXML
    private Label grade;
    @FXML
    private Label restaurantName;
    @FXML
    private Label adress;
    @FXML
    private Label monday;
    @FXML
    private Label tuesday;
    @FXML
    private Label wednesday;
    @FXML
    private Label thursday;
    @FXML
    private Label friday;
    @FXML
    private Label saturday;
    @FXML
    private Label sunday;

    @FXML
    private Hyperlink website;
    @FXML
    private Hyperlink phoneNumber;

    @FXML
    private RadioButton fav;

    RestaurantPageModel model = new RestaurantPageModel();

    Image phoneImage = new Image(View.PHONE_IMAGE);
    Image websitePicture = new Image(View.WEBSITE_IMAGE);
    Image backArrow = new Image(View.BACK_ARROW);

    public void init(Restaurant restaurant, Profile profile){
        if (profile.isNull()){
            fav.visibleProperty().setValue(false);
            returnToHome.setOnMouseClicked(event -> model.accessingTo(profile,"../"+View.HOME_OFFLINE,View.CSS_FILE,"OfflineController"));
        }
        else{
            fav.visibleProperty().setValue(true);
            returnToHome.setOnMouseClicked(event -> model.accessingTo(profile,View.HOME_ONLINE,View.CSS_FILE,"OnlineController"));
        }

        if (profile.isFavori(restaurant)) fav.setSelected(true);

        image.setFitHeight(175.0);
        image.setFitWidth(270.0);
        image.setPreserveRatio(false);
        image.setImage(restaurant.getRestaurantPicture());
        phone.setY(phone.getY()-25);
        phone.setImage(phoneImage);
        websiteImage.setY(websiteImage.getY()-25);
        websiteImage.setImage(websitePicture);

        returnToHome.setY(70);
        returnToHome.setX(200);
        returnToHome.setImage(backArrow);


        grade.setText(Double.toString(restaurant.getGrade()));
        restaurantName.setText(restaurant.getName());
        adress.setText(restaurant.getAdress());
        website.setText(restaurant.getWebsite());
        phoneNumber.setText(restaurant.getPhoneNumber());

        model.setSchedule(new Label[]{monday,tuesday,wednesday,thursday,friday,saturday,sunday},restaurant);
        model.setSizeAndPosition(new ImageView[]{star1,star2,star3,star4,star5},restaurant.getGrade());



        fav.setOnMouseClicked(event -> {if (fav.isSelected()) profile.addFavori(restaurant); else profile.removeFavori(restaurant);});
    }
}
