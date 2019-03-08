package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.controllers.OfflineController;

public class Main extends Application {
    public static View view;
    public static Stage stage;
    public static RestaurantList restaurantList = new RestaurantList();
    public static ProfileList profileList = new ProfileList();

    @Override
    public void start(Stage primaryStage) throws Exception{
        restaurantList.sampleRestaurant();
        profileList.profileSample();
        //create a loader
        FXMLLoader loader = new FXMLLoader();

        view = new View();
        //create a controller

        //attach controller
        //attach XML file
        Parent root = loader.load(getClass().getResourceAsStream(View.HOME_OFFLINE));

        //attach css file
        root.getStylesheets().add(View.CSS_FILE);

        //initialize the controller
        ((OfflineController) loader.getController()).init();

        stage = new Stage();
        stage.setResizable(false);
        //create the view
        stage.setScene(new Scene(root));
        stage.setTitle(View.LABEL);

        //show the view
        stage.show();


    }


    public static void main(String[] args) {
        launch(args);
    }
}
