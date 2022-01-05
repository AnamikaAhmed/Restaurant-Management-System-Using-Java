package restaurant.view.filterrestaurants;

import restaurant.controller.Restaurant;
import java.util.List;

/**
 * Group 1
 * @author: Pavan Abburi
 * @description: Interface for accessing the filter restaurant view
 */
public interface IFilterRestaurantsView {
    String readFromConsole();
    void printFilterRestaurants(List<Restaurant> filteredrestaurantList);
}
