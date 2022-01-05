package restaurant.controller.fooddelivery;

import restaurant.view.fooddelivery.IFoodCategoryView;

/**
 * Group 1
 * @author: Milan Ganesh Acharya
 * @description: Interface for accessing the food category
 */
public interface IFoodCategory extends IFood {
    int getCategoryID();
    void selectFoodCategory(IFoodCategoryView categoryView);
}
