package com.finartz.restaurantapp.data;

import com.finartz.restaurantapp.model.Item;

import java.util.ArrayList;
import java.util.List;

public class TestData {

    public static List<Item> createItems(){
        List<Item> items = new ArrayList<>();

        Item firstItem = new Item();
        firstItem.setName("Hamburger");
        firstItem.setUnitType("piece");
        items.add(firstItem);

        Item secondItem = new Item();
        secondItem.setName("Cheeseburger");
        secondItem.setUnitType("piece");
        items.add(secondItem);

        return items;
    }
}
