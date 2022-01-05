package restaurant.controller.recommendrestaurants;

import restaurant.controller.nearbyrestaurants.INearByRestaurantController;
import restaurant.model.recommendrestaurants.IRecommendRestaurantModel;
import restaurant.view.recommendrestaurants.IRecommendRestaurantView;

/**
 * Group 1
 * @author: Pavan Abburi
 * @description: Interface for accessing the recommend restaurant controller
 */
public interface IRecommendRestaurantController {
    void init(INearByRestaurantController nearByRestaurantController, IRecommendRestaurantModel recommendRestaurantModel, IRecommendRestaurantView recommendRestaurantView, String emailID);
}
