package restaurant.model.orderhistory;

import restaurant.controller.orderhistory.IOrderHistory;
import restaurant.controller.utilities.ColumnType;
import restaurant.model.utilities.IDatabase;
import restaurant.view.orderhistory.IOrderHistoryView;

import java.util.HashMap;
import java.util.Map;

/**
 * Group 1
 * @author: Milan Ganesh Acharya
 * @description: Model class for reading and writing history to the database
 */
public class OrderHistoryModel implements IOrderHistoryModel {

    /*
     Method to get the order history
     @params: String: the ID of the user
              IOrderHistory: reference to the controller class
              IOrderHistoryView: reference to the view class
              IDatabase: reference to the database class
     @return: none
     */
    public void getHistory(String userID, IOrderHistory history, IOrderHistoryView view, IDatabase database) {
        Map<String, ColumnType> tableStructure = new HashMap<>();
        tableStructure.put("OrderID", ColumnType.Integer);
        tableStructure.put("Status", ColumnType.String);
        tableStructure.put("Tax", ColumnType.Float);
        tableStructure.put("Discount", ColumnType.Float);
        tableStructure.put("DeliveryCharge", ColumnType.Float);
        tableStructure.put("TakeAwayCharge", ColumnType.Float);
        tableStructure.put("Eligibility", ColumnType.Integer);
        tableStructure.put("Restaurant_Name", ColumnType.String);

        Map<Integer, Map<String, Object>> table;    // Variable to hold order items
        table = database.retrieve("select * from food_order_history f, restaurants r where f.userID='" + userID +
                "' and f.Eligibility=1 and f.RestaurantID=r.RestaurantID", tableStructure);

        // Store the values in the controller
        history.setTable(table);

        // Get details only if order table is not empty
        if (table.isEmpty())
            view.display("No records found");
        else
            getOrderDetails(history, database);
    }

    /*
     Method to get the order items details
     @params: IOrderHistory: reference to the controller class
              IDatabase: reference to the database class
     @return: none
     */
    public void getOrderDetails(IOrderHistory history, IDatabase database) {
        StringBuilder ids = new StringBuilder();    // Query string variable
        for(Map.Entry<Integer,Map<String,Object>> entry: history.getTable().entrySet()) {
            ids.append(entry.getValue().get("OrderID")).append(",");
        }
        ids = new StringBuilder(ids.substring(0, ids.length() - 1));

        Map<String, ColumnType> tableStructure = new HashMap<>();
        tableStructure.put("OrderID", ColumnType.Integer);
        tableStructure.put("FoodID", ColumnType.Integer);
        tableStructure.put("Quantity", ColumnType.Integer);
        tableStructure.put("Price", ColumnType.Float);

        // Retrieve values
        Map<Integer, Map<String, Object>> order;
        order = database.retrieve("select * from food_order where OrderID in (" + ids + ")", tableStructure);

        // Store it in controller
        history.setOrder(order);
    }

    /*
     Method to update the order status
     @params: int: the order ID
              IDatabase: reference to the database class
     @return: boolean: return true if order is updated successfully else returns false
     */
    public boolean updateOrder(int orderID, IDatabase database) {
        return database.insert("update food_order_history set Status='Cancelled' where orderID=" + orderID) > 0;
    }
}
