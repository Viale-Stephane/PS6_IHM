package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.controllers.AddLocationController;
import sample.controllers.Controller;

public class Main extends Application {
    public static AddLocationController addLocationController;
    private Parent root;

    @Override
    public void start(Stage primaryStage) throws Exception{
        //create a loader
        FXMLLoader loader = new FXMLLoader();

        //create a controller
        Controller controller = new Controller();
        addLocationController = new AddLocationController();

        //attach controller
        loader.setController(controller);

        //attach XML file
        root = loader.load(getClass().getResourceAsStream(View.XML_FILE));

        //attach css file
        root.getStylesheets().add(View.CSS_FILE);

        //initialize the controller
        controller.init();

        //create the view
        View.stage.setScene(new Scene(root, View.WIDTH, View.HEIGHT));
        View.stage.setTitle(View.LABEL);

        //show the view
        View.stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
