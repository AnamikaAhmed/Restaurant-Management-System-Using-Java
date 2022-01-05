package restaurant.model.fooddelivery;

import restaurant.controller.fooddelivery.IFood;
import restaurant.controller.utilities.ColumnType;
import restaurant.model.utilities.IDatabase;

import java.util.HashMap;
import java.util.Map;

/**
 * Group 1
 * @author: Milan Ganesh Acharya
 * @description: Model class to retrieve the Services from the database
 */
public class FoodServiceModel implements IFoodServiceModel {
    /*
     Method to retrieve the services from the database
     @params: int: the restaurant ID
              IFood: reference to the FoodService controller class
              IDatabase: reference to the database class
     @return: none
     */
    public void getFoodService(int restaurantID, IFood food, IDatabase database) {
        // Query to select the available services for a restaurant
        String query = "select a.ServiceID, a.Type from available_service a, restaurant_service s " +
                "where s.restaurantID=" + restaurantID + " and s.ServiceID=a.ServiceID and s.Available=1";

        // Table structure of the Restaurant_Service table
        Map<String, ColumnType> tableStructure = new HashMap<>();
        tableStructure.put("ServiceID", ColumnType.Integer);
        tableStructure.put("Type", ColumnType.String);

        // Set the table in the controller class
        food.setTable(database.retrieve(query, tableStructure));
    }
}
