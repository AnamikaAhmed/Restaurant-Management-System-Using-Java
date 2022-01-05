package restaurant.view.takeaway;

import restaurant.controller.takeaway.IFoodOrder;
import restaurant.model.fooddelivery.IFoodChargesModel;
import restaurant.model.utilities.IDatabase;

/**
 * Group 1
 * @author: Milan Ganesh Acharya
 * @description: Interface for accessing the food order view
 */
public interface IFoodOrderView {
    void display(String text);
    String readFromConsole(String text);
    boolean displayOrder(IFoodOrder order);
    float displayCharges(int restaurantID, int serviceID, float discount, IFoodChargesModel charges, IFoodOrder order,
                        IDatabase database);
    void displayOrderStatus(IFoodOrder order);
}
