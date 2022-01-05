package restaurant.view.nearbyrestaurants;

import restaurant.controller.Restaurant;
import java.util.List;

/**
 * Group 1
 * @author: Pavan Abburi
 * @description: Interface for accessing the nearby restaurant view
 */
public interface INearByRestaurantView {
    String readFromConsole();
    void printRestaurants(List<Restaurant> nearbyRestaurantList);
}
