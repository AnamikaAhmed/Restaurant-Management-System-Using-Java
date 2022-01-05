package restaurant.model.fooddelivery;

import restaurant.model.utilities.IDatabase;

import java.util.Map;

/**
 * Group 1
 * @author: Milan Ganesh Acharya
 * @description: Interface for accessing the food charges model
 */
public interface IFoodChargesModel {
    Map<Integer, Map<String, Object>> getTable();
    void getCharges(int restaurantID, IDatabase database);
}
