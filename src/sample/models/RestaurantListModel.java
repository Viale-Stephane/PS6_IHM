package sample.models;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import sample.Main;
import sample.Profile;
import sample.Restaurant;
import sample.View;
import sample.controllers.ProfileController;
import sample.controllers.ResearchRestaurantController;
import sample.controllers.RestaurantPageController;

import java.io.IOException;

public class RestaurantListModel {

    Image FULL_STAR = new Image(View.FULL_STAR);
    Image EMPTY_STAR = new Image(View.EMPTY_STAR);

    public void setSizeAndPosition(ImageView[] stars, double grade){
        int i=0;
        for(ImageView star: stars){
            star.setFitHeight(15);
            star.setFitWidth(15);
            star.setX(15*i);
            star.setY(15);
            i++;
            if(i<grade){
                star.setImage(FULL_STAR);
            }else{
                star.setImage(EMPTY_STAR);
            }
        }
    }

    public void setSchedule(Label[] schedule, Restaurant restaurant) {
        int i=0;
        for(Label day: schedule){
            day.setText(restaurant.getSchedule(i));
            day.setFont(Font.font(10));
            day.setLayoutY(10*(i+3));
            i++;
        }
    }

    public void accessRestaurantPage(Restaurant restaurant, Profile profile){
        String fxmlFile = View.RESTAURANT_PAGE;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.getClass().getResource(fxmlFile);
            Parent root = loader.load(getClass().getResourceAsStream(fxmlFile));
            root.getStylesheets().add(View.CSS_FILE);
            Scene scene = new Scene(root);
            Main.stage.setScene(scene);
            ((RestaurantPageController) loader.getController()).init(restaurant,profile);
            Main.stage.show();

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void goBack(Profile profile) {
        String fxmlFile = View.MENU_FILTER;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.getClass().getResource(fxmlFile);
            Parent root = loader.load(getClass().getResourceAsStream(fxmlFile));
            root.getStylesheets().add(View.CSS_FILE);
            Scene scene = new Scene(root);
            Main.stage.setScene(scene);
            ((ResearchRestaurantController) loader.getController()).init(profile);
            Main.stage.show();

        }catch (IOException e){
            e.printStackTrace();
        }
    }

}