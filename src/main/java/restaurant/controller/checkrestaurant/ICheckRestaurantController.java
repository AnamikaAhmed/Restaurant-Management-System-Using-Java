package restaurant.controller.checkrestaurant;

import restaurant.controller.Restaurant;
import restaurant.model.checkrestaurant.ICheckRestaurantModel;
import restaurant.view.checkrestaurant.ICheckRestaurantView;

import java.sql.SQLException;
import java.util.List;

/**
 * Group 1
 * @author: Pavan Abburi
 * @description: Interface for accessing the check restaurant controller
 */
public interface ICheckRestaurantController {
    void init(String restaurantId, List<Restaurant> restaurantList, ICheckRestaurantModel checkRestaurantModel, ICheckRestaurantView checkRestaurantView,String emailID) throws SQLException;
}
