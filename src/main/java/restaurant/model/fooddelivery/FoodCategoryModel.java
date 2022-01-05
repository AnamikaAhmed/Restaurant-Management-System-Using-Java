package restaurant.model.fooddelivery;

import restaurant.controller.fooddelivery.IFood;
import restaurant.controller.utilities.ColumnType;
import restaurant.model.utilities.IDatabase;

import java.util.HashMap;
import java.util.Map;

/**
 * Group 1
 * @author: Milan Ganesh Acharya
 * @description: Model class to retrieving the food categories from the database
 */
public class FoodCategoryModel implements IFoodCategoryModel {
    /*
     Method to read the food categories from the database
     @params: int: the restaurant ID
              IFood: reference to the controller interface
              IDatabase: reference to the database interface
     @return: none
     */
    public void getFoodCategories(int restaurantID, IFood food, IDatabase database) {
        // Query to retrieve the categories form the database
        String query = "select distinct c.CategoryID, c.Name from food_menu m, food_category c where m.restaurantID=" +
                restaurantID + " and m.CategoryID=c.CategoryID";

        // Hashmap of the table structure to be passed to the database retrieve method
        Map<String, ColumnType> tableStructure = new HashMap<>();
        tableStructure.put("CategoryID", ColumnType.Integer);
        tableStructure.put("Name", ColumnType.String);

        // Store the table in the controller class
        food.setTable(database.retrieve(query, tableStructure));
    }
}
