package com.example.foodcompare_vm;

import java.util.ArrayList;

/**
 * Represents a platform that contains restaurants.
 * Each class and non-trivial method should be documented.
 * All documentation should use Javadoc standard tags.
 *
 * @author Fabian Perez
 * @version 1.0
 */
public class Platform {

    private String name;
    private String id;

    public ArrayList<Restaurant> restaurants = new ArrayList<>();

    /**
     * Constructs a new Platform object with the given name and id.
     *
     * @param name the name of the platform
     * @param id   the ID of the platform
     */
    public Platform(String name, String id) {
        this.name = name;
        this.id = id;
    }

    /**
     * Adds a restaurant to the platform.
     *
     * @param restaurant the restaurant to be added
     */
    public void addRestaurant(Restaurant restaurant) {
        restaurants.add(restaurant);
    }

    /**
     * Returns the name of the platform.
     *
     * @return the name of the platform
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the list of restaurants in the platform.
     *
     * @return the list of restaurants
     */
    public ArrayList<Restaurant> getRestaurants() {
        return restaurants;
    }
}
