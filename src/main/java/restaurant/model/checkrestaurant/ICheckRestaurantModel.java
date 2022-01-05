package restaurant.model.checkrestaurant;

import restaurant.controller.Restaurant;

import java.util.List;

/**
 * Group 1
 * @author: Pavan Abburi
 * @description: Interface for accessing the check restaurant model
 */
public interface ICheckRestaurantModel {
    boolean isRestaurantValid(String restaurantId);
    void setRestaurantList(List<Restaurant> restaurantList);
}
