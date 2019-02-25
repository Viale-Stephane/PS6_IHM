package sample;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class View {
    public final static String HOME = "data/FXML/userProfile.fxml" ;
    public final static String NEW_LOCATION = "../data/FXML/addResto.fxml";
    public String CSS_FILE = "sample/data/CSS/temp.css";
    public String LABEL ="User Profile";
    public final int WIDTH = 200;
    public final int HEIGHT = 400;

    public View(){}



    public String getCSS_FILE(){
        return this.CSS_FILE;
    }

    public String getLABEL(){
        return this.LABEL;
    }

    public int getWIDTH(){
        return this.WIDTH;
    }

    public int getHEIGHT(){
        return this.HEIGHT;
    }
}
