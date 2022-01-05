package restaurant.model.fooddelivery;

import restaurant.controller.fooddelivery.IFood;
import restaurant.model.utilities.IDatabase;

/**
 * Group 1
 * @author: Milan Ganesh Acharya
 * @description: Interface for accessing the food service model
 */
public interface IFoodServiceModel {
    void getFoodService(int restaurantID, IFood food, IDatabase database);
}
