package sample.models;

import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
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
            star.setY(-2);
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

    public void newComment(ObservableList<Pane> panes, String comment, Profile profile, Restaurant restaurant,int prefWidth) {
        if(!comment.isEmpty()) {
            Comment newComment = new Comment(comment, profile, restaurant);
            restaurant.getCommentList().addComment(newComment);
            profile.getUserComments().addComment(newComment);
            this.add(panes, newComment, prefWidth);
        }
    }

    public void add(ObservableList<Pane> panes, Comment comment, int prefWidth){
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
        pane.setPrefWidth(200);
        pane.getChildren().add(username);
        pane.getChildren().add(profilePicture);
        pane.getChildren().add(commentRestaurant);
        panes.add(pane);
    }
}
