package restaurant.model.fooddelivery;

import restaurant.controller.fooddelivery.IFood;
import restaurant.controller.utilities.ColumnType;
import restaurant.model.utilities.IDatabase;

import java.util.HashMap;
import java.util.Map;

/**
 * Group 1
 * @author: Milan Ganesh Acharya
 * @description: Model class for reading food items from the database
 */
public class FoodMenuModel implements IFoodMenuModel {
    /*
     Method to get the food items from the database
     @params: int: the restaurant ID
              IDatabase: reference to the database class
              IFood: reference to the Food Menu controller class
     @return: none
     */
    public void getFoodMenu(int restaurantID, IDatabase database, IFood food) {
        String query = "select FoodID, Name, Description, Price from food_menu where restaurantID=" +
                restaurantID + " and Status=1";

        getData(query, food, database);
    }

    /*
     Method to get the food items based on category from the database
     @params: int: the restaurant ID
              int: the category ID
              IDatabase: reference to the database class
              IFood: reference to the Food Menu controller class
     @return: none
     */
    public void getFoodCategoryMenu(int restaurantID, int categoryID, IDatabase database, IFood food) {
        String query = "select FoodID, Name, Description, Price from food_menu where restaurantID=" +
                restaurantID + " and CategoryID=" + categoryID + " and Status=1";

        getData(query, food, database);
    }

    /*
     Method to get the food items based on the search term from the database
     @params: int: the restaurant ID
              String: the search item
              IFood: reference to the Food Menu controller class
              IDatabase: reference to the database class
     @return: none
     */
    public void getSearchedMenu(int restaurantID, String searchItem, IFood food, IDatabase database) {
        String query = "select FoodID, Name, Description, Price from food_menu where restaurantID=" +
                restaurantID + " and Status=1 and Name like '%" + searchItem + "%'";

        getData(query, food, database);
    }

    /*
     Method to get the food items based on the query from the database
     @params: String: the query string
              IFood: reference to the Food Menu controller class
              IDatabase: reference to the database class
     @return: none
     */
    private void getData(String query, IFood food, IDatabase database) {
        // Table structure of the Food_Menu table
        Map<String, ColumnType> tableStructure = new HashMap<>();
        tableStructure.put("FoodID", ColumnType.Integer);
        tableStructure.put("Name", ColumnType.String);
        tableStructure.put("Description", ColumnType.String);
        tableStructure.put("Price", ColumnType.Float);

        // Set the table in the controller class, after receiving the table from the database
        food.setTable(database.retrieve(query, tableStructure));
    }
}
