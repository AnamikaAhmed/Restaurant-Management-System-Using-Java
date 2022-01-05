package restaurant.model.fooddelivery;

import restaurant.controller.fooddelivery.IFood;
import restaurant.model.utilities.IDatabase;

/**
 * Group 1
 * @author: Milan Ganesh Acharya
 * @description: Interface for accessing the food category model
 */
public interface IFoodCategoryModel {
    void getFoodCategories(int restaurantID, IFood food, IDatabase database);
}
