package restaurant.model.filterrestaurants;

import restaurant.controller.Restaurant;

import java.util.List;

/**
 * Group 1
 * @author: Pavan Abburi
 * @description: Interface for accessing the filter restaurant model
 */
public interface IFilterRestaurantsModel {
    List<Restaurant> getFilteredRestaurants(String rating);
    List<Restaurant> getCurrentFilteredRest();
    List<Restaurant> getRestaurantList();
    void setRestaurantList(List<Restaurant> restaurantList);
}
