package sample.models;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.*;
import sample.controllers.OnlineController;
import sample.controllers.RestaurantListController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumSet;

public class ResearchRestaurantModel extends Model {
    Image FULL_STAR = new Image(View.FULL_STAR);
    Image EMPTY_STAR = new Image(View.EMPTY_STAR);

    public ResearchRestaurantModel(){}

    public Restaurant lookingForARestaurant(int minStar, boolean isItARestaurant, Restaurant restaurant, double maxPrice, double maxDistance, ArrayList<String> tags){
        if(isItARestaurant == restaurant.isKindRestaurant()) {
            if (!tags.isEmpty()) {
                for (Tag restaurantTag : restaurant.getTags()) {
                    for (String tag : tags) {
                        if ((restaurantTag.getName()).equals(tag) && restaurant.getPrice() <= maxPrice && restaurant.getDistance() <= maxDistance && restaurant.getGrade() >= minStar) {
                            return restaurant;
                        }
                    }
                }
            } else {
                if (restaurant.getPrice() <= maxPrice && restaurant.getDistance() <= maxDistance && restaurant.getGrade() >= minStar) {
                    return restaurant;
                }
            }
        }
        return null;
    }

    public void accessingTo(int minStar, boolean isItARestaurant, double maxPrice, double maxDistance, ArrayList<String> tags, Profile profile){
        if(profile.isNull()){
            profile = new Profile();
        }
        RestaurantList whiteListedRestaurant = new RestaurantList();
        ArrayList<Restaurant> restaurants = Main.restaurantList.getRestaurants();
        for (Restaurant restaurant : restaurants) {
            Restaurant choosenOne = this.lookingForARestaurant(minStar, isItARestaurant, restaurant, maxPrice, maxDistance, tags);
            if(choosenOne!= null)
                whiteListedRestaurant.addRestaurant(choosenOne);
        }
        String fxmlFile= View.RESTAURANT_LIST;
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.getClass().getResource(fxmlFile);
            Parent root = loader.load(getClass().getResourceAsStream(fxmlFile));
            root.getStylesheets().add(View.CSS_FILE);
            Scene scene = new Scene(root);
            Main.stage.setScene(scene);
            ((RestaurantListController) loader.getController()).init(profile,whiteListedRestaurant);
            Main.stage.show();

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void initFilters(EnumSet<Tag> fullList, SplitMenuButton splitMenuFiltre){
        int i = 0;
        for(Tag tag : fullList)
            splitMenuFiltre.getItems().add(new MenuItem(tag.toString()));
        for(MenuItem menuItem : splitMenuFiltre.getItems()){
            menuItem.setId("filtre"+i);
            i++;
        }
    }

    public boolean addFiltre(String newFiltre, ArrayList<String> researchedTags, TextField textFieldFiltres){
        if(researchedTags.contains("#"+newFiltre+" ")){
            researchedTags.remove("#"+newFiltre+" ");
            textFieldFiltres.setText(researchedTags.toString());
            return false;
        }
        researchedTags.add("#"+newFiltre+" ");
        textFieldFiltres.setText(researchedTags.toString());
        return true;
    }

    public void initSlider(Slider slider, int tickUnit, int blockIncrement){
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setMajorTickUnit(tickUnit);
        slider.setBlockIncrement(blockIncrement);
    }

    public void swapKindOfLocation(String location, ToggleButton toggleButtonCommerce, ToggleButton toggleButtonRestaurant){
        switch(location){
            case "restaurant":
                toggleButtonCommerce.setSelected(false);
                toggleButtonRestaurant.setSelected(true);
                break;
            case "commerce":
                toggleButtonRestaurant.setSelected(false);
                toggleButtonCommerce.setSelected(true);
                break;
        }
    }

    public int clickStar(int number, ImageView filterStar1, ImageView filterStar2, ImageView filterStar3, ImageView filterStar4, ImageView filterStar5){
        filterStar5.setImage(EMPTY_STAR);
        filterStar4.setImage(EMPTY_STAR);
        filterStar3.setImage(EMPTY_STAR);
        filterStar2.setImage(EMPTY_STAR);
        filterStar1.setImage(EMPTY_STAR);
        switch(number){
            case 5:
                filterStar5.setImage(FULL_STAR);
            case 4:
                filterStar4.setImage(FULL_STAR);
            case 3:
                filterStar3.setImage(FULL_STAR);
            case 2:
                filterStar2.setImage(FULL_STAR);
            case 1:
                filterStar1.setImage(FULL_STAR);
        }
        return number;
    }


}
