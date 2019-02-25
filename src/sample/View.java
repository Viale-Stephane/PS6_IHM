package sample;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class View {
    public static String XML_FILE = "data/FXML/userProfile.fxml";
    public static String CSS_FILE = "sample/data/CSS/temp.css";
    public static String LABEL = "User Profile";
    public static final int WIDTH = 200;
    public static final int HEIGHT = 400;
    public static Stage stage = new Stage();

    public static void setXML_FILE(String file){
        XML_FILE = file;
    }

    public static void changeStage(Scene scene, String label, String xml_file, String css_file){
        XML_FILE = xml_file;
        CSS_FILE = css_file;
        LABEL = label;
        stage.setScene(scene);
        stage.show();
    }
}
