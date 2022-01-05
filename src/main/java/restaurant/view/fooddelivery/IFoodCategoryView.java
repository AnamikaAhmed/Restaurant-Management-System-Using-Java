package restaurant.view.fooddelivery;

import restaurant.controller.fooddelivery.IFood;

/**
 * Group 1
 * @author: Milan Ganesh Acharya
 * @description: Interface for accessing the food category view
 */
public interface IFoodCategoryView {
    void displayFoodCategories(IFood food);
    String readFromConsole();
    void display(String text);
}
