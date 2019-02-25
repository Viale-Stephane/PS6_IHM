package sample;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.controllers.Controller;

public class Main extends Application {
    public static View view;
    public static Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        //create a loader
        FXMLLoader loader = new FXMLLoader();

        View view = new View();
        //create a controller

        //attach controller
        //attach XML file
        Parent root = loader.load(getClass().getResourceAsStream(View.HOME));

        //attach css file
        root.getStylesheets().add(view.getCSS_FILE());

        //initialize the controller
        ((Controller) loader.getController()).init();

        stage = new Stage();

        //create the view
        stage.setScene(new Scene(root, view.getWIDTH(), view.getHEIGHT()));
        stage.setTitle(view.getLABEL());

        //show the view
        stage.show();



    }


    public static void main(String[] args) {
        launch(args);
    }
}
