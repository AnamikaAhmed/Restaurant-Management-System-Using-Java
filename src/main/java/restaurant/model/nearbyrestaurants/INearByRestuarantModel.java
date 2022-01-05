package restaurant.model.nearbyrestaurants;

import restaurant.controller.Restaurant;

import java.util.List;

/**
 * Group 1
 * @author: Pavan Abburi
 * @description: Interface for accessing the nearby restaurant model
 */
public interface INearByRestuarantModel {
    List<Restaurant> getNearByRestaurant(String pincode);
    List<Restaurant> getCurrentNearByRestaurant();
    String getPincode();
}
