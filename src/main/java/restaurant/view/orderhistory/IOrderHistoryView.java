package restaurant.view.orderhistory;

import restaurant.controller.orderhistory.IOrderHistory;

/**
 * Group 1
 * @author: Milan Ganesh Acharya
 * @description: Interface for accessing the order history view
 */
public interface IOrderHistoryView {
    void displayOrders(IOrderHistory history);
    void display(String text);
    void displayCharges(IOrderHistory history, int slNo);
    String readFromConsole(String text);
}
