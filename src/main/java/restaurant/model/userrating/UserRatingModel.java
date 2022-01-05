package restaurant.model.userrating;

import restaurant.model.utilities.IDatabase;

import java.sql.ResultSet;

/**
 * Group 1
 * @author: Dinesh Kumar Baalajee Jothi
 * @description: Model class for getting the rating of the restaurant from the database
 */

public class UserRatingModel implements IUserRatingModel {

    public int getRating(IDatabase database, int restaurantID) {

        int restaurantRating = 0;

        try {
            ResultSet result_set = database.retrieve("Select Ratings from restaurants where RestaurantID = '"+restaurantID+"';");

            while (result_set.next()) {
                restaurantRating = result_set.getInt("Ratings");
            }

        } catch (Exception e){
            e.printStackTrace();
        }

        return restaurantRating;
    }
}
