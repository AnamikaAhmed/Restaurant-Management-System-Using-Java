package restaurant.controller.fooddelivery;

import restaurant.controller.utilities.Parse;
import restaurant.view.fooddelivery.IFoodCategoryView;
import java.util.Map;

/**
 * Group 1
 * @author: Milan Ganesh Acharya
 * @description: Controller class for handling the food category
 */
public class FoodCategory implements IFoodCategory {
    private int categoryID;                             // Category ID of the selected category
    private Map<Integer, Map<String, Object>> table;    // Map to hold the table of food category

    public FoodCategory() {
        categoryID = -1;
        table = null;
    }

    /*
     Method to retrieve the category ID
     @params: none
     @return: int: the category ID
     */
    public int getCategoryID() {
        return categoryID;
    }

    /*
     Method to retrieve the food category table
     @params: none
     @return: Map<Integer, Map<String, Object>>: the table of food category
     */
    public Map<Integer, Map<String, Object>> getTable() {
        return table;
    }

    /*
     Method to set the food category table
     @params: Map<Integer, Map<String, Object>>: the table of food category
     @return: none
     */
    public void setTable(Map<Integer, Map<String, Object>> table) {
        this.table = table;
    }

    /*
     Method to select the food category
     @params: IFoodCategoryView: reference to the view interface
     @return: none
     */
    public void selectFoodCategory(IFoodCategoryView categoryView) {
        int ERROR_VALUE = -1;   // Error value for incorrect entry

        // Loop continuously until correct value is read
        while (true) {
            String value = categoryView.readFromConsole();
            Integer category = Parse.tryParseInt(value);
            category = category != null ? category : ERROR_VALUE;

            // Check if the entered value is available in the category table
            if (table.containsKey(category)) {
                categoryID = (Integer) table.get(category).get("CategoryID");
                break;
            } else {
                categoryView.display("Please select correct category");
            }
        }
    }
}
