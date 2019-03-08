package sample.models;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import sample.Main;
import sample.Profile;
import sample.View;
import sample.controllers.OfflineController;
import sample.controllers.OnlineController;

import java.io.IOException;

public class ApplicationInformationsModel extends Model {

    public void accessingTo(Profile profile) {
        if (profile.isNull()) {
            profile = new Profile(null, null, null, null, null);
            super.accessingTo(profile,"../"+View.HOME_OFFLINE,View.CSS_FILE,"OfflineController");
        } else
            super.accessingTo(profile,View.HOME_ONLINE,View.CSS_FILE,"OnlineController");

    }
}
