package sample;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class View {
    public final static String HOME_OFFLINE = "data/FXML/mainPageOffline.fxml";
    public final static String HOME_ONLINE = "../data/FXML/mainPageOnline.fxml";
    public final static String SIGN_IN = "../data/FXML/SignIn.fxml";
    public final static String LOG_IN = "../data/FXML/logIn.fxml";
    public final static String INFORMATIONS = "../data/FXML/";
    public final static String MENU_FILTER = "../data/FXML/MenuFIlter.fxml";
    public final static String PROFILE = "../data/FXML/userProfile.fxml" ;
    public final static String NEW_LOCATION = "../data/FXML/addResto.fxml";
    public final static String FAVORITES = "../data/FXML/";
    public final static String HISTORY = "../data/FXML/";
    public final static String MY_RATINGS = "../data/FXML/";
    public final static String RESTORANT_PAGE = "../data/FXML/restopage.fxml";
    public final static String LOG_OUT = "../data/FXML/";

    //IMAGES//

    public final static String FULL_STAR = "sample/data/Images/starFull.png";
    public final static String EMPTY_STAR = "sample/data/Images/starEmpty.png";

    public static String CSS_FILE = "sample/data/CSS/temp.css";
    public String LABEL ="BROUPE G";
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
