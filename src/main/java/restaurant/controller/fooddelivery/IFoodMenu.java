package restaurant.controller.fooddelivery;

import restaurant.model.fooddelivery.IFoodMenuModel;
import restaurant.model.utilities.IDatabase;
import restaurant.view.fooddelivery.IFoodMenuView;

import java.util.Map;

/**
 * Group 1
 * @author: Milan Ganesh Acharya
 * @description: Interface for accessing the food menu
 */
public interface IFoodMenu extends IFood {
    void searchMenu(int restaurantID, IFood food, IDatabase database, IFoodMenuView menuView, IFoodMenuModel menuModel);
    Map<String, Object> selectFoodItem(IFoodMenuView menuView);
}
