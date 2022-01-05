package restaurant.controller.filterrestaurants;

import restaurant.model.filterrestaurants.IFilterRestaurantsModel;
import restaurant.controller.Restaurant;
import restaurant.view.filterrestaurants.IFilterRestaurantsView;

import java.util.List;

/**
 * Group 1
 * @author: Pavan Abburi
 * @description: Interface for accessing the filter restaurant controller
 */
public interface IFilterRestaurantsController {
    List<Restaurant> getFilteredRestaurantList();
    void init(List<Restaurant> restaurantList, IFilterRestaurantsView filterRestaurantsView, IFilterRestaurantsModel filterRestaurantsModel);
    void showFilteredRestaurant(List<Restaurant> filteredrestaurantList);
}
