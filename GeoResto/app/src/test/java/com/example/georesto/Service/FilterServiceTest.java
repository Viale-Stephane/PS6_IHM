package com.example.georesto.Service;

import com.example.georesto.Activity.MapsActivity;
import com.example.georesto.Model.Restaurant;
import com.example.georesto.Model.RestaurantList;
import com.example.georesto.Model.Tag;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class FilterServiceTest {
    RestaurantList restaurantList;
    FilterService filterService;

    @Before
    public void initialise() {
        restaurantList = MapsActivity.restaurantList;
        filterService = new FilterService(restaurantList);
    }

    @Test
    public void isAMatchTest() {
        assertFalse(filterService.isAMatch(null, null));

        ArrayList<Tag> tags = new ArrayList<>();
        List<Tag> restaurantTags = new ArrayList<>();
        assertTrue(filterService.isAMatch(restaurantTags, tags));

        tags.add(Tag.Crepe);
        assertTrue(filterService.isAMatch(restaurantTags, tags));

        tags.remove(Tag.Crepe);
        restaurantTags.add(Tag.Crepe);
        assertTrue(filterService.isAMatch(restaurantTags, tags));

        tags.add(Tag.Crepe);
        assertTrue(filterService.isAMatch(restaurantTags, tags));

        tags.add(Tag.Fromage);
        tags.add(Tag.Glace);
        assertTrue(filterService.isAMatch(restaurantTags, tags));

        restaurantTags.add(Tag.Salade);
        restaurantTags.add(Tag.Burger);
        assertTrue(filterService.isAMatch(restaurantTags, tags));
    }
}