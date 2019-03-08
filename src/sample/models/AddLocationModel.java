package sample.models;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.*;
import sample.controllers.OnlineController;
import sample.controllers.ProfileController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumSet;

public class AddLocationModel extends Model {
    Image FULL_STAR = new Image(View.FULL_STAR);
    Image EMPTY_STAR = new Image(View.EMPTY_STAR);

    public AddLocationModel(){
    }

    public String addLocation(String restaurant, boolean kindRestaurant, String adress, String website, String phoneNumber, String[] schedule, int grade, double price, double distance, ArrayList<Tag> tags, Profile profile, Image image){
        Restaurant newRestaurant = new Restaurant(restaurant, kindRestaurant, adress, website, phoneNumber, schedule, grade, price, distance,tags,image);
        Main.restaurantList.addRestaurant(newRestaurant);
        String answer = "Adding the restaurant to our database..";
        this.accessingTo(profile,View.HOME_ONLINE,View.CSS_FILE, "OnlineController");
        return answer;
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

    public void clickStar(int number, ImageView star1, ImageView star2, ImageView star3, ImageView star4, ImageView star5, Label grade){
        star5.setImage(EMPTY_STAR);
        star4.setImage(EMPTY_STAR);
        star3.setImage(EMPTY_STAR);
        star2.setImage(EMPTY_STAR);
        star1.setImage(EMPTY_STAR);
        switch(number){
            case 5:
                star5.setImage(FULL_STAR);
            case 4:
                star4.setImage(FULL_STAR);
            case 3:
                star3.setImage(FULL_STAR);
            case 2:
                star2.setImage(FULL_STAR);
            case 1:
                star1.setImage(FULL_STAR);
        }
        grade.setText(Integer.toString(number));
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

    public boolean addFiltre(String newFiltre, ArrayList<Tag> researchedTags, TextField textFieldFiltres){
        if(researchedTags.contains(newFiltre)){
            researchedTags.remove(newFiltre);
            textFieldFiltres.setText(researchedTags.toString());
            return false;
        }
        researchedTags.add(Tag.toTag(newFiltre));
        textFieldFiltres.appendText(newFiltre);
        return true;
    }
    public void initSlider(Slider slider, int tickUnit, int blockIncrement){
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setMajorTickUnit(tickUnit);
        slider.setBlockIncrement(blockIncrement);
    }
}
