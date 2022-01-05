package restaurant.controller.visits;

import restaurant.model.utilities.IDatabase;
import restaurant.model.utilities.SQLDatabase;
import restaurant.model.visits.IRestaurantVisitModel;
import restaurant.model.visits.RestaurantVisitModel;

/**
 * Group 1
 * @author: Dinesh Kumar Baalajee Jothi
 * @description: Class to update the restaurants visited/ordered
 */

public class RestaurantVisit implements IRestaurantVisit {

    private final IDatabase database;
    private final IRestaurantVisitModel restaurantVisitModel;

    public RestaurantVisit() {
        database = new SQLDatabase();
        database.connect(System.getenv("HOST"), System.getenv("DATABASE"), System.getenv("USER_NAME"), System.getenv("PASSWORD"));
        restaurantVisitModel = new RestaurantVisitModel();
    }

    /*
     * Method to insert the restaurant visited by the user to the database
     * @params: restaurantID: The ID of the restaurant
                userID: The ID of the user
     * @return: none
     */

    public void rateRestaurant(int restaurantID, String userID) {
        restaurantVisitModel.insertVisit(database,restaurantID,userID);
    }

    public static void main(String args[]) {
        RestaurantVisit visit = new RestaurantVisit();
        visit.rateRestaurant(2341,"test@4321");
    }
}
