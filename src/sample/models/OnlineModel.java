package sample.models;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import sample.Profile;
import sample.controllers.*;

import java.io.IOException;

public class OnlineModel extends Model {

    public String accessingTo(boolean right, Pane leftPane, Pane rightPane, Profile profile, String fxmlFile, String cssFile, String controller){
        try {
            FXMLLoader loader = new FXMLLoader();

            Pane newLoadedPane = loader.load(getClass().getResourceAsStream(fxmlFile));
            newLoadedPane.getStylesheets().add(cssFile);

            leftPane.getChildren().clear();
            rightPane.getChildren().clear();
            if (right) {
                rightPane.getChildren().add(newLoadedPane);
            } else {
                leftPane.getChildren().add(newLoadedPane);
            }

            switch(controller){
                case "AddLocationController":
                    ((AddLocationController) loader.getController()).init(profile);
                    break;
                case "ApplicationInformationsController":
                    ((ApplicationInformationsController) loader.getController()).init(profile);
                    break;
                case "FavorisController":
                    ((FavorisController) loader.getController()).init(profile);
                    break;
                case "HistoryController":
                    ((HistoryController) loader.getController()).init(profile);
                    break;
                case "LoginController":
                    ((LoginController) loader.getController()).init();
                    break;
                case "ProfileController":
                    ((ProfileController) loader.getController()).init(profile);
                    break;
                case "ResearchRestaurantController":
                    ((ResearchRestaurantController) loader.getController()).init(profile);
                    break;
                case "MyRatingsController":
                    ((MyRatingsController) loader.getController()).init(profile);
                    break;
                case "SignInController":
                    ((SignInController) loader.getController()).init();
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    return "";
    }

}
