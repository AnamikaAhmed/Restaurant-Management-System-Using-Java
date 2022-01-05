package restaurant.model.userrating;

import restaurant.model.utilities.IDatabase;

/**
 * Group 1
 * @author: Dinesh Kumar Baalajee Jothi
 * @description: Interface for updating the rating of the restaurant
 */

public interface IUserRatingUpdateModel {
    void update(IDatabase database, int Rating, int RestaurantID);
}
