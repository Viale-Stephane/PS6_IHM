package sample.models;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import sample.Restaurant;
import sample.View;

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
            day.setPrefHeight(1);
            day.setFont(Font.font(10));
            day.setLayoutY(10*(i+3));
            i++;
        }
    }
}
