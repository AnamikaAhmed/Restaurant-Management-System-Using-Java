package restaurant.model.userrating;

import restaurant.model.utilities.IDatabase;

/**
 * Group 1
 * @author: Dinesh Kumar Baalajee Jothi
 * @description: Interface for getting the rating of the restaurants
 */

public interface IUserRatingModel {
    int getRating(IDatabase database, int restaurantID);
}
