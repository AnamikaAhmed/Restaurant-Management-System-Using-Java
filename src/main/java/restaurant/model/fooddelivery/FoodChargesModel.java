package restaurant.model.fooddelivery;

import restaurant.controller.utilities.ColumnType;
import restaurant.model.utilities.IDatabase;

import java.util.HashMap;
import java.util.Map;

/**
 * Group 1
 * @author: Milan Ganesh Acharya
 * @description: Model class for reading the costs/charges associated with a restaurant from the database
 */
public class FoodChargesModel implements IFoodChargesModel {
    private Map<Integer, Map<String, Object>> table;    // Table to hold the charges of a particular restaurant

    public FoodChargesModel() {
        table = null;
    }

    /*
     Method to get the table of the charges
     @params: none
     @return: Map<Integer, Map<String, Object>>: the table of charges
     */
    public Map<Integer, Map<String, Object>> getTable() {
        return table;
    }

    /*
     Method to retrieve the charges from the database
     @params: int: the ID of the restaurant
     @return: none
     */
    public void getCharges(int restaurantID, IDatabase database) {
        // Query for retrieving the charges from database
        String query = "select * from restaurant_charges where restaurantID=" + restaurantID;

        // Table structure for the Restaurant_Charges table
        Map<String, ColumnType> tableStructure = new HashMap<>();
        tableStructure.put("Takeaway", ColumnType.Float);
        tableStructure.put("Delivery", ColumnType.Float);
        tableStructure.put("Tax", ColumnType.Float);

        // Set the value of the table
        table = database.retrieve(query, tableStructure);
    }
}
