package sample.models;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.*;
import sample.controllers.RestaurantPageController;

import java.io.IOException;

public class HistoryModel {

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

    public void accessRestaurantPage(Restaurant restaurant, Profile profile) {
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
}
