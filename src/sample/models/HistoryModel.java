package sample.models;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.View;

public class HistoryModel extends Model{

    private Image FULL_STAR = new Image(View.FULL_STAR);
    private Image EMPTY_STAR = new Image(View.EMPTY_STAR);

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


}
