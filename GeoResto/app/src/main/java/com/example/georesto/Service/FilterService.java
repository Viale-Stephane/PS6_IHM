package com.example.georesto.Service;

import com.example.georesto.Model.Profile;
import com.example.georesto.Model.Restaurant;
import com.example.georesto.Model.Tag;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.georesto.Activity.MapsActivity.restaurantList;

public class FilterService {


    public FilterService() {

    }

    public List<Restaurant> filter(CharSequence query, boolean isRestaurant, ArrayList<Tag> tags, int maxPrice, int maxDistance, int minGrade, Profile profile) {
        String filterPattern = query.toString().toLowerCase().trim();

        return restaurantList.getRestaurants().stream()
                .filter(restaurant ->
                        restaurant.getName().toLowerCase().contains(filterPattern)
                                && restaurant.isKindRestaurant() == isRestaurant
                                && isAMatch(restaurant.getTags(), tags)
                                && restaurant.getPrice() <= maxPrice
                                && (restaurant.getDistance()/1000) <= maxDistance
                                && restaurant.getGrade() >= minGrade
                )
                .collect(Collectors.toList());
    }

    public List<Restaurant> filter(CharSequence query, boolean isRestaurant, ArrayList<Tag> tags, int maxPrice, int maxDistance, int minGrade) {
        String filterPattern = query.toString().toLowerCase().trim();

        return restaurantList.getRestaurants().stream()
                .filter(restaurant ->
                        restaurant.getName().toLowerCase().contains(filterPattern)
                                && restaurant.isKindRestaurant() == isRestaurant
                                && isAMatch(restaurant.getTags(), tags)
                                && restaurant.getPrice() < maxPrice
                                && (restaurant.getDistance()/1000) < maxDistance
                                && restaurant.getGrade() > minGrade
                        )
                .collect(Collectors.toList());
    }

    boolean isAMatch(List<Tag> restaurantTags, List<Tag> filterTags) {
        if (filterTags == null || restaurantTags == null) {
            return false;
        } else {
            if (filterTags.isEmpty() || restaurantTags.isEmpty()) {
                return true;
            } else {
                for (Tag tag : restaurantTags) {
                    if (filterTags.contains(tag)) {
                        return true;
                    }
                }
                return false;
            }
        }
    }

   /* public ArrayList<Restaurant> filter(CharSequence query) {
        filteredList = new ArrayList<>();
        if (query == null || query.length() == 0) {
            filteredList.setRestaurants(filteredList.getRestaurants());
        } else {
            String filterPattern = query.toString().toLowerCase().trim();

            for (Restaurant restaurant : filteredList) {
                if (restaurant.getName().toLowerCase().contains(filterPattern)) {
                    filteredList.add(restaurant);
                }
            }
        }

        return filteredList;
    } */
}
