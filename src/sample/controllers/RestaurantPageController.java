package sample.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import sample.*;
import sample.models.RestaurantPageModel;

import java.util.Collection;

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

    @FXML
    private ListView<Pane> commentList;

    @FXML
    private ScrollBar scrollBar;

    RestaurantPageModel model = new RestaurantPageModel();

    private ObservableList<Pane> panes = FXCollections.observableArrayList();
    private ObservableList<Comment> items = FXCollections.observableArrayList();

    private int prefHeight = 100;
    private int prefWidth = 250;

    Image phoneImage = new Image(View.PHONE_IMAGE);
    Image websitePicture = new Image(View.WEBSITE_IMAGE);
    Image backArrow = new Image(View.BACK_ARROW);

    public void initCommentList(Restaurant restaurant) {
        items.addAll( restaurant.getCommentList().getCommentList());
        for(Comment comment : restaurant.getCommentList().getCommentList()){
            model.attributeProfileToComments(comment);
            Pane pane = new Pane();
            Text commentRestaurant = new Text();
            Text username = new Text();
            ImageView profilePicture = new ImageView();
            profilePicture.setImage(comment.getProfile().getProfileImage());
            profilePicture.setPreserveRatio(false);
            profilePicture.setFitWidth(50);
            profilePicture.setFitHeight(50);
            profilePicture.setX(0);
            profilePicture.setY(0);

            Circle circle = new Circle();
            circle.setCenterX(profilePicture.getX()+profilePicture.getFitWidth()/2);
            circle.setCenterY(profilePicture.getY()+profilePicture.getFitHeight()/2);
            circle.setRadius(17);
            profilePicture.setClip(circle);


            commentRestaurant.setText(comment.getComment());
            commentRestaurant.setWrappingWidth(prefWidth/2);// - profilePicture.getFitWidth() - 5);
            commentRestaurant.setX(profilePicture.getFitWidth()+5);
            commentRestaurant.setY(40);
            username.setText(comment.getProfile().getUsername());
            username.setFont(Font.font(null, FontWeight.BOLD, 14));
            username.setX(profilePicture.getFitWidth());
            username.setY(20);
            pane.setPrefWidth(this.prefWidth);
            pane.getChildren().add(username);
            pane.getChildren().add(profilePicture);
            pane.getChildren().add(commentRestaurant);
            panes.add(pane);

        }
        commentList.setItems(panes);
       /* for (int i = 0; i < commentList.getItems().size(); i++) {
            int finalI = i;
            commentList.getItems().get(i).setOnMouseClicked(event -> model.accessRestaurantPage(profile.getFavori(finalI), profile));
        }*/
    }

    public void init(Restaurant restaurant, Profile profile){
        this.initCommentList(restaurant);
        scrollBar.setOnMouseClicked(event -> System.out.println());
        System.out.println(scrollBar.computeAreaInScreen());
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
