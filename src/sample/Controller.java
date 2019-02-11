package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class Controller {
    @FXML
    private Button history;
    @FXML
    private Button favorites;
    @FXML
    private Button myRatings;
    @FXML
    private Button addLocation;

    @FXML
    private Button logOut;

    public void results(String instruction){
        Model model = new Model();
        System.out.println(model.compute(instruction));
    }

    public void init() {
        history.setOnAction( event -> results("history"));
        favorites.setOnAction( event -> results("favorites"));
        myRatings.setOnAction( event -> results("myRatings"));
        addLocation.setOnAction( event -> results("addLocation"));
        logOut.setOnAction( event -> results("logOut"));
    }

}
