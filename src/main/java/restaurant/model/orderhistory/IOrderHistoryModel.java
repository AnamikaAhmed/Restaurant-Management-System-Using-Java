package restaurant.model.orderhistory;

import restaurant.controller.orderhistory.IOrderHistory;
import restaurant.model.utilities.IDatabase;
import restaurant.view.orderhistory.IOrderHistoryView;

/**
 * Group 1
 * @author: Milan Ganesh Acharya
 * @description: Interface for accessing the order history model
 */
public interface IOrderHistoryModel {
    void getHistory(String userID, IOrderHistory history, IOrderHistoryView view, IDatabase database);
    void getOrderDetails(IOrderHistory history, IDatabase database);
    boolean updateOrder(int orderID, IDatabase database);
}
