package restaurant.controller.orderhistory;

import restaurant.controller.utilities.Parse;
import restaurant.model.orderhistory.IOrderHistoryModel;
import restaurant.model.orderhistory.OrderHistoryModel;
import restaurant.model.utilities.IDatabase;
import restaurant.model.utilities.SQLDatabase;
import restaurant.view.orderhistory.IOrderHistoryView;
import restaurant.view.orderhistory.OrderHistoryView;

import java.util.HashMap;
import java.util.Map;

/**
 * Group 1
 * @author: Milan Ganesh Acharya
 * @description: Controller class for accessing the overall order history
 */
public class OrderHistory implements IOrderHistory {
    private final IDatabase database;                   // Reference to the database object
    private Map<Integer, Map<String, Object>> table;    // The table of the order items
    private Map<Integer, Map<String, Object>> order;    // The map of items in the order
    private final IOrderHistoryModel historyModel;      // Reference to the model class
    private final IOrderHistoryView historyView;        // Reference to the view class

    public OrderHistory() {
        table = new HashMap<>();
        order = new HashMap<>();
        historyModel = new OrderHistoryModel();
        historyView = new OrderHistoryView();
        database = new SQLDatabase();
        database.connect(System.getenv("HOST"), System.getenv("DATABASE"), System.getenv("USER_NAME"), System.getenv("PASSWORD"));
    }

    /*
     Method to set the table value
     @params: Map<Integer, Map<String, Object>>: the table value to set with
     @return: none
     */
    public void setTable(Map<Integer, Map<String, Object>> table) {
        this.table = table;
    }

    /*
     Method to get the table value
     @params: none
     @return: Map<Integer, Map<String, Object>>: the table value
     */
    public Map<Integer, Map<String, Object>> getTable() {
        return table;
    }

    /*
     Method to set the order value
     @params: Map<Integer, Map<String, Object>>: the order value to set with
     @return: none
     */
    public void setOrder(Map<Integer, Map<String, Object>> order) {
        this.order = order;
    }

    /*
     Method to get the order value
     @params: none
     @return: Map<Integer, Map<String, Object>>: the order value
     */
    public Map<Integer, Map<String, Object>> getOrder() {
        return order;
    }

    /*
     Method to display the history menu
     @params: String: the ID of the user
     @return: none
     */
    public void historyMenu(String userID) {
        boolean exit = false;   // Variable to control loop exit
        do {
            historyView.display("1: View Order History, 2: Cancel Order, 3: Go Back");
            String line = historyView.readFromConsole("Enter Choice: ");
            Integer choice = Parse.tryParseInt(line);
            choice = choice !=null ? choice : -1;

            switch(choice) {
                case 1:
                    // Display the order history
                    historyModel.getHistory(userID, this, historyView, database);
                    historyView.displayOrders(this);
                    break;
                case 2:
                    // Cancel the order
                    cancelOrder(userID);
                    break;
                case 3:
                    // Return to previous menu
                    exit = true;
                    break;
                default:
                    historyView.display("Please enter valid choice");
            }
        } while (!exit);
    }

    /*
     Method to cancel the order
     @params: String: the ID of the user
     @return: boolean: true if order is cancelled successfully
     */
    public boolean cancelOrder(String userID) {
        historyModel.getHistory(userID, this, historyView, database);
        historyView.displayOrders(this);
        if (table.isEmpty())
            return false;

        Integer slNo;   // The serial number of the order in the history list
        while (true) {
            String line = historyView.readFromConsole("Enter Sl.No: ");
            slNo = Parse.tryParseInt(line);
            slNo = slNo != null ? slNo : -1;

            // Check if order is already cancelled
            if (table.containsKey(slNo)) {
                if (!table.get(slNo).get("Status").equals("Cancelled")) {
                    break;
                } else {
                    historyView.display("This order is already cancelled.");
                    return false;
                }
            } else {
                historyView.display("Please enter available Sl. No");
            }
        }

        // Display the charges associated with cancellation
        historyView.displayCharges(this, slNo);

        // Loop until correct choice is entered
        while (true) {
            historyView.display("Cancellation charges will apply. Do you want to cancel? 1: Yes, 2: No");
            String line = historyView.readFromConsole("Enter Choice: ");
            Integer option = Parse.tryParseInt(line);
            option = option != null ? option : -1;

            if (option != 1 && option != 2) {
                historyView.display("Please enter correct choice.");
                continue;
            }

            if (option == 1)
                break;
            else
                return false;
        }

        // Update the database with the values
        boolean status = historyModel.updateOrder((int) table.get(slNo).get("OrderID"), database);
        if (status)
            historyView.display("Your cancellation request has been accepted. The amount will be refunded in a few days.");
        else
            historyView.display("Cancellation request failed. Please try again.");

        return status;
    }
}
