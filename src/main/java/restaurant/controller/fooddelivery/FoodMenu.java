package restaurant.controller.fooddelivery;

import restaurant.controller.utilities.Parse;
import restaurant.model.fooddelivery.IFoodMenuModel;
import restaurant.model.utilities.IDatabase;
import restaurant.view.fooddelivery.IFoodMenuView;

import java.util.*;

/**
 * Group 1
 * @author: Milan Ganesh Acharya
 * @description: Controller class for handling the food menu
 */
public class FoodMenu implements IFoodMenu {
    private Map<Integer, Map<String, Object>> table;    // Map to hold the table of food items

    public FoodMenu() {
        table = null;
    }

    /*
     Method to get the food items table
     @params: none
     @return: Map<Integer, Map<String, Object>>: the table of food items
     */
    public Map<Integer, Map<String, Object>> getTable() {
        return table;
    }

    /*
     Method to set the food items table
     @params: Map<Integer, Map<String, Object>>: the table of food items
     @return: none
     */
    public void setTable(Map<Integer, Map<String, Object>> table) {
        this.table = table;
    }

    /*
     Method to search an item in the menu
     @params: int: the ID of the restaurant
              IFood: reference to the Food Menu controller class
              IDatabase: reference to the database class
              IFoodMenuView: reference to the Food Menu view class
              IFoodMenuModel: reference to the Food Menu model class
     @return: none
     */
    public void searchMenu(int restaurantID, IFood food, IDatabase database, IFoodMenuView menuView,
                           IFoodMenuModel menuModel) {
        // Loop continuously until correct value is entered
        while (true) {
            // Read item id from the console
            String searchItem = menuView.readFromConsole("search item");

            // Get the list of matching items
            menuModel.getSearchedMenu(restaurantID, searchItem, food, database);

            // Check if there are no matches
            if (table.entrySet().isEmpty())
                menuView.display("No results found. Please re-enter the search term");
            else
                break;
        }
    }

    /*
     Method to select the food item in the menu
     @params: IFoodMenuView: reference to the Food Menu view class
     @return: none
     */
    public Map<String, Object> selectFoodItem(IFoodMenuView menuView) {
        int foodID;             // ID of the food item
        int quantity;           // Quantity of the food item
        int ERROR_VALUE = -1;   // Error value for incorrect entry
        int MAX_ITEMS = 10;     // Maximum number of items in orders

        // Loop continuously until correct item is selected
        while (true) {
            Integer value = Parse.tryParseInt(menuView.readFromConsole("item id"));
            value = value != null ? value : ERROR_VALUE;

            // Check if item is available in the menu
            if (table.containsKey(value)) {
                foodID = value;
                break;
            } else {
                menuView.display("Please select correct food item");
            }
        }

        // Loop continuously until valid quantity is entered
        while (true) {
            Integer value = Parse.tryParseInt(menuView.readFromConsole("quantity"));
            value = value != null ? value : ERROR_VALUE;

            // Check if valid quantity is entered
            if (value > 0 && value < MAX_ITEMS) {
                quantity = value;
                break;
            } else
                menuView.display("Please enter the correct quantity");
        }

        // Extract only the required parameters of the food item. A new Map was created due to an issue when adding
        // items to the order list, rather than directly referencing the item in the table
        Map<String, Object> foodItem = new HashMap<>();
        foodItem.put("FoodID", table.get(foodID).get("FoodID"));
        foodItem.put("Name", table.get(foodID).get("Name"));
        foodItem.put("Price", table.get(foodID).get("Price"));
        foodItem.put("Quantity", quantity);

        return foodItem;
    }
}
