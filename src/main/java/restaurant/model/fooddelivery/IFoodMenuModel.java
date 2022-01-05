package restaurant.model.fooddelivery;

import restaurant.controller.fooddelivery.IFood;
import restaurant.model.utilities.IDatabase;

/**
 * Group 1
 * @author: Milan Ganesh Acharya
 * @description: Interface for accessing the food menu model
 */
public interface IFoodMenuModel {
    void getFoodMenu(int restaurantID, IDatabase database, IFood food);
    void getFoodCategoryMenu(int restaurantID, int categoryID, IDatabase database, IFood food);
    void getSearchedMenu(int restaurantID, String searchItem, IFood food, IDatabase database);
}
