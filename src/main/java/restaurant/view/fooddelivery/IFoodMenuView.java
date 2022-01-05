package restaurant.view.fooddelivery;

import restaurant.controller.fooddelivery.IFood;

/**
 * Group 1
 * @author: Milan Ganesh Acharya
 * @description: Interface for accessing the food menu view
 */
public interface IFoodMenuView {
    void displayFoodMenu(IFood food);
    String readFromConsole(String item);
    void display(String text);
}
