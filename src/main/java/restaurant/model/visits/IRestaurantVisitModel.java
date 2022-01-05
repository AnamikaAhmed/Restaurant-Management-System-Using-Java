package restaurant.model.visits;

import restaurant.model.utilities.IDatabase;

/**
 * Group 1
 * @author: Dinesh Kumar Baalajee Jothi
 * @description: Interface for recording the restaurant visited/ordered
 */

public interface IRestaurantVisitModel {
    void insertVisit(IDatabase database, int restaurantID, String userEmail);
}
