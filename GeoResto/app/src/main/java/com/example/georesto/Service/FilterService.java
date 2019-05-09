package com.example.georesto.Service;

import com.example.georesto.Model.Profile;
import com.example.georesto.Model.Restaurant;
import com.example.georesto.Model.RestaurantList;
import com.example.georesto.Model.Tag;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FilterService {
    private final ArrayList<Restaurant> restaurantListFull;

    public FilterService(RestaurantList restaurantList) {
        this.restaurantListFull = new ArrayList<>(restaurantList.getRestaurants());
    }

    public void filter(CharSequence query, boolean isRestaurant, ArrayList<Tag> tags, int maxPrice, int maxDistance, int minMark, Profile profile) {
    }

    public List<Restaurant> filter(CharSequence query, boolean isRestaurant, ArrayList<Tag> tags, int maxPrice, int maxDistance, int minGrade) {
        String filterPattern = query.toString().toLowerCase().trim();

        return restaurantListFull.stream()
                .filter(restaurant ->
                        restaurant.getName().toLowerCase().contains(filterPattern)
                                && restaurant.isKindRestaurant() == isRestaurant
                                && isAMatch(tags, restaurant.getTags())
                                && restaurant.getPrice() < maxPrice
                                && restaurant.getDistance() < maxDistance
                                && restaurant.getGrade() < minGrade)
                .collect(Collectors.toList());
    }

    private boolean isAMatch(ArrayList<Tag> filterTags, List<Tag> restaurantTags) {
        for (Tag tag : restaurantTags) {
            if (filterTags.contains(tag)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Restaurant> filter(CharSequence query) {
        ArrayList<Restaurant> filteredList = new ArrayList<>();

        if (query == null || query.length() == 0) {
            filteredList.addAll(restaurantListFull);
        } else {
            String filterPattern = query.toString().toLowerCase().trim();

            for (Restaurant restaurant : restaurantListFull) {
                if (restaurant.getName().toLowerCase().contains(filterPattern)) {
                    filteredList.add(restaurant);
                }
            }
        }

        return filteredList;
    }
}
