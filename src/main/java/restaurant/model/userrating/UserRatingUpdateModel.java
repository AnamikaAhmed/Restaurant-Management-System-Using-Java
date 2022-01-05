package restaurant.model.userrating;

import restaurant.model.utilities.IDatabase;

/**
 * Group 1
 * @author: Dinesh Kumar Baalajee Jothi
 * @description: Model class for updating the rating of the restaurant
 */

public class UserRatingUpdateModel implements IUserRatingUpdateModel {

    /*
     * Method to update the rating of the restaurant
     * @params: restaurantID: The ID of the restaurant
                database:     Reference to the database interface
     * @return: none
     */

    public void update(IDatabase database,int Rating, int RestaurantID) {

        database.update("update restaurants set Ratings = " + Rating + " where RestaurantID ='" + RestaurantID + "';");

    }
}
