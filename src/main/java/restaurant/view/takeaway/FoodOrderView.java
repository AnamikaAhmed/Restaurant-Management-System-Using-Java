package restaurant.view.takeaway;

import restaurant.controller.takeaway.IFoodOrder;
import restaurant.model.fooddelivery.IFoodChargesModel;
import restaurant.model.utilities.IDatabase;

import java.util.Map;
import java.util.Scanner;

/**
 * Group 1
 * @author: Milan Ganesh Acharya
 * @description: View class for reading and writing data from and to the console
 */
public class FoodOrderView implements IFoodOrderView {
    /*
     Method to display text on the console
     @params: String: the text to display on console
     @return: none
     */
    public void display(String text) {
        System.out.println(text);
    }

    /*
     Method to read value from the console
     @params: String: message for the user
     @return: String: the value read from console
     */
    public String readFromConsole(String text) {
        System.out.print(text);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    /*
     Method to display the order on the console
     @params: IFoodOrder: reference to the FoodOrder controller class
     @return: boolean: returns true if order is not empty, else false
     */
    public boolean displayOrder(IFoodOrder order) {
        if (order.getOrder().entrySet().isEmpty()) {
            System.out.println("There are no items in the order list!!");
            return false;
        }

        // Display the table with total cost
        displayOrderTable(order);
        return true;
    }

    /*
     Common method to display the order, with total price on the console
     @params: IFoodOrder: reference to the FoodOrder controller class
     @return: float: the total cost of all items in the order list
     */
    private float displayOrderTable(IFoodOrder order) {
        System.out.printf("%-4s %-40s %8s %10s %8s\n","Id", "Name", "Price", "Quantity", "Cost");
        System.out.println("----------------------------------------------------------------------------");

        float total = 0;    // Total price
        for (Map.Entry<Integer, Map<String, Object>> entry : order.getOrder().entrySet()) {
            int quantity = (int) entry.getValue().get("Quantity");
            float price = (float) entry.getValue().get("Price");
            total += price * quantity;
            String name = (String) entry.getValue().get("Name");
            System.out.printf("%-4d %-40s %8.2f %10d %8.2f\n", entry.getKey(),
                    name.substring(0, Math.min(name.length(), 40)),
                    price,
                    quantity,
                    price * quantity);
        }
        System.out.println("----------------------------------------------------------------------------");
        System.out.printf("%65s %8.2f\n", "Sub Total:", total);

        return total;
    }

    /*
     Method to display the order with totals, taxes, etc on the console
     @params: int: the restaurant ID
              int: the service ID
              float: the discount amount
              IFoodChargesModel: reference to the FoodCharges model class
              IFoodOrder: reference to the FoodOrder controller class
              IDatabase: reference to the database class
     @return: float: the total cost of all items in the order list
     */
    public float displayCharges(int restaurantID, int serviceID, float discount, IFoodChargesModel charges,
                               IFoodOrder order, IDatabase database) {
        // Retrieve the charges from database
        charges.getCharges(restaurantID, database);

        // Retrieve the charges table
        Map<Integer, Map<String, Object>> table = charges.getTable();

        float tax = (float) table.get(1).get("Tax");
        float delivery = (float) table.get(1).get("Delivery");
        float takeaway = (float) table.get(1).get("Takeaway");

        // Display the table with total cost
        float total = displayOrderTable(order);
        System.out.printf("%65s %8.2f\n", "Tax " + tax + "%:", total * (1 + tax / 100));

        total *= (1 + tax / 100);
        // Display charges based on the service type
        switch (serviceID) {
            case 2: // Delivery includes only delivery charges
                System.out.printf("%65s %8.2f\n", "Delivery Charges:", delivery);
                total += delivery;
            case 1: // Takeaway includes both delivery and takeaway charges
                System.out.printf("%65s %8.2f\n", "Takeaway Charges:", takeaway);
                total += takeaway;
        }
        if (discount != 0) {
            System.out.printf("%65s %8.2f\n", "Discount:", discount);
            total -= discount;
        }
        System.out.println("----------------------------------------------------------------------------");
        System.out.printf("%65s %8.2f\n", "Total:", total);
        System.out.println("----------------------------------------------------------------------------");

        return total;
    }

    /*
     Method to display the order status
     @params: IFoodOrder: reference to the FoodOrder controller class
     @return: none
     */
    public void displayOrderStatus(IFoodOrder order) {
        int DEFAULT_WAIT_TIME = 45; // The default wait time

        System.out.printf("%10s %10s %15s\n", "Order ID", "Status", "Estimated Time");
        System.out.println("--------------------------------------");

        // Display the contents of order status map
        Map<Integer, Map<String, Object>> orderStatus = order.getOrderStatus();
        System.out.printf("%10d %10s %15s\n", (int) orderStatus.get(1).get("OrderID"), orderStatus.get(1).get("Status"),
                DEFAULT_WAIT_TIME + " mins.");

        System.out.println("--------------------------------------");
    }
}
