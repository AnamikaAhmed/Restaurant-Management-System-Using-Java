package restaurant.model.takeaway;

import restaurant.controller.payment.IPay;
import restaurant.controller.takeaway.IFoodOrder;
import restaurant.controller.utilities.ColumnType;
import restaurant.model.fooddelivery.IFoodChargesModel;
import restaurant.model.utilities.IDatabase;

import java.util.HashMap;
import java.util.Map;

/**
 * Group 1
 * @author: Milan Ganesh Acharya
 * @description: Model class for storing the order in the database
 */
public class FoodOrderModel implements IFoodOrderModel {
    /*
     Method to insert the order to database
     @params: int: the restaurant ID
              int: the service ID
              String: the user ID
              float: the discount for the order
              float: the total amount of order
              IFoodChargesModel: reference to the FoodCharges model class
              IFoodOrder: reference to the FoodOrder controller class
              IDatabase: reference to the Database class
              IPay: reference to the Pay class
     @return: boolean: returns true if order is submitted successfully, else false
     */
    public boolean submitOrder(int restaurantID, int serviceID, String userID, float discount, float total,
                               IFoodChargesModel charges, IFoodOrder order, IDatabase database, IPay pay) {
        // Retrieve the charges for the restaurant
        Map<Integer, Map<String, Object>> tableCharges = charges.getTable();
        float tax = (float) tableCharges.get(1).get("Tax");
        float delivery = (float) tableCharges.get(1).get("Delivery");
        float takeaway = (float) tableCharges.get(1).get("Takeaway");

        // Calculate charges based on service. No charges for dine-in
        switch (serviceID) {
            case 1:
                delivery = 0;
                break;
            case 3:
                takeaway = 0;
                delivery = 0;
                break;
        }

        String orderStatus = "Scheduled";   // Status for Dine-In order
        if (serviceID != 3) {
            orderStatus = "Pending";        // Status for Takeaway and Delivery

            boolean status = pay.finishPayment(total, userID);
            if (!status)
                return false;
        }

        // Insert data to Order History table, and retrieve the order ID
        int orderID = database.insertAndGetId("insert into food_order_history values(0,now(),'"
                + orderStatus + "',"
                + tax + ","
                + discount + ","
                + delivery + ","
                + takeaway + ",1,'"
                + userID + "',"
                + restaurantID + ")");
        if (orderID == -1)
            return false;
        else
            order.setOrderID(orderID);

        // Create query for insertion
        StringBuilder insertQuery = new StringBuilder("insert into food_order values");
        int size = order.getOrder().size(); // Size of the order
        int index = 1;                      // Index variable
        for (Map.Entry<Integer, Map<String, Object>> entry : order.getOrder().entrySet()) {
            insertQuery.append("(").append(orderID).append(",").
                    append(entry.getValue().get("FoodID")).append(",").
                    append(entry.getValue().get("Quantity")).append(",").
                    append(entry.getValue().get("Price")).append(")");
            if (index++ != size)
                insertQuery.append(",");
        }

        // Insert order list to the Order Table
        return database.insert(insertQuery.toString()) > 0;
    }

    /*
     Method to retrieve the order status from the database
     @params: IFoodOrder: reference to the FoodOrder controller class
              IDatabase: reference to the database class
     @return: none
     */
    public void getOrderStatus(IFoodOrder order, IDatabase database) {
        int orderID = order.getOrderID();   // The order ID of the order which has to be retrieved

        Map<String, ColumnType> tableStructure = new HashMap<>();
        tableStructure.put("OrderID", ColumnType.Integer);
        tableStructure.put("Status", ColumnType.String);

        // Retrieve the order status from database
        order.setOrderStatus(database.retrieve("select OrderID, Status from food_order_history where OrderID=" +
                orderID, tableStructure));
    }
}
