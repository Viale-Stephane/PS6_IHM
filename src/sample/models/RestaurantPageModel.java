package sample.models;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Orientation;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import sample.*;

public class RestaurantPageModel extends Model {

    Image FULL_STAR = new Image(View.FULL_STAR);
    Image EMPTY_STAR = new Image(View.EMPTY_STAR);
    ScrollBar scrollBar = new ScrollBar();
    Pane mainPage = new Pane();
    public void setSizeAndPosition(ImageView[] stars, double grade){
        int i=0;
        for(ImageView star: stars){
            star.setFitHeight(20);
            star.setFitWidth(20);
            star.setX(20*i);
            star.setY(-2);
            i++;
            if(i<=grade){
                star.setImage(FULL_STAR);
            }else{
                star.setImage(EMPTY_STAR);
            }
        }
    }

    public void setScrollBar() {
        scrollBar.setLayoutX(250.0-scrollBar.getWidth());
        scrollBar.setMin(0);
        scrollBar.setOrientation(Orientation.VERTICAL);
        scrollBar.setPrefHeight(255);
        scrollBar.setMax(800);
        scrollBar.valueProperty().addListener((ov, old_val, new_val) -> mainPage.setLayoutY(-new_val.doubleValue()));

    }

    public void setSchedule(Label[] schedule, Restaurant restaurant) {
        int i=0;
        for(Label day: schedule){
            day.setText(restaurant.getSchedule(i));
            day.setFont(Font.font(10));
            day.setLayoutY(-30+10*i);
            i++;
        }
    }

    public void attributeProfileToComments(Comment comment){
        for(Profile profile : Main.profileList.getProfiles()){
            for(Comment commentUser : profile.getUserComments().getCommentList()){
                if(commentUser.getComment().equals(comment.getComment())){
                    comment.setProfile(profile);
                }
            }
        }
    }
}
