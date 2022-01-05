package restaurant.view.recommendrestaurants;

import restaurant.controller.Restaurant;

import java.util.List;

/**
 * Group 1
 * @author: Pavan Abburi
 * @description: Interface for accessing the recommend restaurant view
 */
public interface IRecommendRestaurantView {
    void printRecommendations(List<Restaurant> recommendationList);
}
