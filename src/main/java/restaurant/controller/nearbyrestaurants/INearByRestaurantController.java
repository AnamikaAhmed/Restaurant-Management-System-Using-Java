package restaurant.controller.nearbyrestaurants;

import restaurant.model.nearbyrestaurants.INearByRestuarantModel;
import restaurant.controller.Restaurant;
import restaurant.view.nearbyrestaurants.INearByRestaurantView;

import java.util.List;

/**
 * Group 1
 * @author: Pavan Abburi
 * @description: Interface for accessing the nearby restaurant controller
 */
public interface INearByRestaurantController
{
    void init(INearByRestuarantModel nearByRestuarantModel, INearByRestaurantView nearByRestaurantView);
    void showNearByRestaurant( List<Restaurant> restaurantList );
    List<Restaurant> getNearbyRestaurantList();
    String getPinCode();
}
