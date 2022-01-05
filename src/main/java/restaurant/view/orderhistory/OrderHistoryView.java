package restaurant.view.orderhistory;

import restaurant.controller.orderhistory.IOrderHistory;

import java.util.Map;
import java.util.Scanner;

/**
 * Group 1
 * @author: Milan Ganesh Acharya
 * @description: View class for accessing the overall food menu
 */
public class OrderHistoryView implements IOrderHistoryView {

    /*
     Method to display the order details
     @params: IOrderHistory: reference to the controller class
     @return: none
     */
    public void displayOrders(IOrderHistory history) {
        // Check if order list is empty
        if(history.getTable().isEmpty())
            return;

        // Display the orders in the list
        System.out.printf("%5s %-10s %-30s %8s\n", "Sl.No", "Status", "Restaurant", "Price");
        System.out.println("---------------------------------------------------------");
        for (Map.Entry<Integer, Map<String, Object>> entry : history.getTable().entrySet()) {
            int orderID = (int) entry.getValue().get("OrderID");
            float tax = (float) entry.getValue().get("Tax");
            float discount = (float) entry.getValue().get("Discount");
            float delivery = (float) entry.getValue().get("Tax");
            float takeaway = (float) entry.getValue().get("Tax");
            float price = 0;    // Variable to calculate the price
            for (Map.Entry<Integer, Map<String, Object>> orderEntry : history.getOrder().entrySet()) {
                if ((int) orderEntry.getValue().get("OrderID") == orderID) {
                    price += (int) orderEntry.getValue().get("Quantity") * (float) orderEntry.getValue().get("Price");
                }
            }
            price *= (1 + tax / 100);
            price += delivery + takeaway - discount;

            Map<String, Object> row = entry.getValue();
            row.put("Price", price);

            // Add price to the table after retrieving it from the controller
            Map<Integer, Map<String, Object>> table = history.getTable();
            table.put(entry.getKey(), row);
            history.setTable(table);

            String name = (String) entry.getValue().get("Restaurant_Name");
            System.out.printf("%-5d %-10s %-30s %8.2f\n", entry.getKey(), entry.getValue().get("Status"),
                    name.substring(0, Math.min(name.length(), 30)), price);
        }
        System.out.println("---------------------------------------------------------");
    }

    /*
     Method to display the text on console
     @params: String: the text to be displayed
     @return: none
     */
    public void display(String text) {
        System.out.println(text);
    }

    /*
     Method to display the total refund charged
     @params: IOrderHistory: reference to the controller class
              int: the serial number of the order
     @return: none
     */
    public void displayCharges(IOrderHistory history, int slNo) {
        float CANCELLATION_CHARGE = (float) 0.50;   // The default cancellation charge

        System.out.printf("%-10s %-20s %8s\n", "Order ID", "Description", "Amount");
        System.out.println("------------------------------------------");
        System.out.printf("%-10s %-20s %8.2f\n", history.getTable().get(slNo).get("OrderID"), "Bill Amount",
                (float) history.getTable().get(slNo).get("Price"));
        System.out.printf("%31s %8.2f\n", "Cancellation Charges", CANCELLATION_CHARGE);
        System.out.println("------------------------------------------");
        float total = (float) history.getTable().get(slNo).get("Price") - CANCELLATION_CHARGE;
        System.out.printf("%31s %8.2f\n", "Total Refund", total);
        System.out.println("------------------------------------------");
    }

    /*
     Method to read data from the console
     @params: String: the message to be displayed on the console
     @return: String: the value read from the console
     */
    public String readFromConsole(String text) {
        System.out.print(text);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
