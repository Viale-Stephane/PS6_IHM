package sample.models;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import sample.*;

public class RestaurantPageModel extends Model {

    Image FULL_STAR = new Image(View.FULL_STAR);
    Image EMPTY_STAR = new Image(View.EMPTY_STAR);

    public void setSizeAndPosition(ImageView[] stars, double grade){
        int i=0;
        for(ImageView star: stars){
            star.setFitHeight(20);
            star.setFitWidth(20);
            star.setX(20*i);
            star.setY(0);
            i++;
            if(i<=grade){
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
