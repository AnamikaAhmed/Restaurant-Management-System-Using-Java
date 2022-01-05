package restaurant.view.fooddelivery;

import restaurant.controller.fooddelivery.IFood;

/**
 * Group 1
 * @author: Milan Ganesh Acharya
 * @description: Interface for accessing the food service view
 */
public interface IFoodServiceView {
    void displayFoodService(IFood food);
    String readFromConsole();
    void display(String text);
}
