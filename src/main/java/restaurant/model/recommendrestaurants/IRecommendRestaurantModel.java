package restaurant.model.recommendrestaurants;

import restaurant.controller.Restaurant;

import java.util.List;

/**
 * Group 1
 * @author: Pavan Abburi
 * @description: Interface for accessing the recommend restaurant model
 */
public interface IRecommendRestaurantModel {
    List<Restaurant> getRecommendationList(String pincode, String emailID);
}
