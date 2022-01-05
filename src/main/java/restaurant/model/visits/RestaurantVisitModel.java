package restaurant.model.visits;

import restaurant.model.utilities.IDatabase;

/**
 * Group 1
 * @author: Dinesh Kumar Baalajee Jothi
 * @description: Model Class for inserting the record of restaurant visited/ordered
 */

public class RestaurantVisitModel implements IRestaurantVisitModel {

    /*
     * Method to insert the restaurant visited/ordered in the database
     * @params: userID:       the ID of the customer
                restaurantID: the ID of the restaurant
                IDatabase: reference to the database interface
     * @return: Float: Returns the balance of the wallet
     */

    public void insertVisit(IDatabase database, int restaurantID, String userID) {

        try {
            String query = "INSERT INTO visitinginformation (RestaurantID,userEmail) "
                    +"VALUES ('"+restaurantID+"', '"+userID+"')";
            database.insert(query);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
