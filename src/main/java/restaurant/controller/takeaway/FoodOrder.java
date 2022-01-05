package restaurant.controller.takeaway;

import restaurant.controller.coupon.ICouponCode;
import restaurant.controller.payment.IPay;
import restaurant.controller.userrating.IUserRating;
import restaurant.controller.userrating.UserRating;
import restaurant.controller.utilities.Parse;
import restaurant.model.fooddelivery.IFoodChargesModel;
import restaurant.model.takeaway.IFoodOrderModel;
import restaurant.model.utilities.IDatabase;
import restaurant.view.takeaway.IFoodOrderView;

import java.util.*;
import java.util.Map.Entry;

/**
 * Group 1
 * @author: Milan Ganesh Acharya
 * @description: Controller class for handling the food order
 */
public class FoodOrder implements IFoodOrder {
    private int orderID;                                    // The order ID
    private Map<Integer, Map<String, Object>> orderStatus;  // Map of the order Status
    private Map<Integer, Map<String, Object>> order;        // Map of the order list

    public FoodOrder() {
        orderID = -1;
        order = new HashMap<>();
        orderStatus = new HashMap<>();
    }

    /*
     Method to get the order ID
     @params: none
     @return: int: the order ID
     */
    public int getOrderID() {
        return orderID;
    }

    /*
     Method to set the order ID
     @params: int: the order ID
     @return: none
     */
    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    /*
     Method to get the order status map
     @params: none
     @return: Map<Integer, Map<String, Object>>: the order status map
     */
    public Map<Integer, Map<String, Object>> getOrderStatus() {
        return orderStatus;
    }

    /*
     Method to set the order status map
     @params: Map<Integer, Map<String, Object>>: the order status map
     @return: none
     */
    public void setOrderStatus(Map<Integer, Map<String, Object>> orderStatus) {
        this.orderStatus = orderStatus;
    }

    /*
     Method to get the order list
     @params: none
     @return: Map<Integer, Map<String, Object>>: the order list
     */
    public Map<Integer, Map<String, Object>> getOrder() {
        return order;
    }

    /*
     Method to add food items to the order list. If same item is present, add the quantity
     @params: Map<String, Object>: the food item
              IFoodOrderView: reference to the FoodOrder view class
     @return: none
     */
    public void addItem(Map<String, Object> foodItem, IFoodOrderView orderView) {
        int size = order.entrySet().size(); // Variable to be used as index

        for (Entry<Integer, Map<String, Object>> entry : order.entrySet()) {
            // Check if item is already available
            if (foodItem.get("FoodID").equals(entry.getValue().get("FoodID"))) {
                orderView.display("Item already exists in the menu!! Updated the quantity.");

                // Add the new quantity value to the existing
                foodItem.replace("Quantity", (int) entry.getValue().get("Quantity")
                        + (int) foodItem.get("Quantity"));

                // Replace the item, with new quantity value
                order.replace(entry.getKey(), foodItem);
                return;
            }
        }
        // Add item to order list
        order.put(size + 1, foodItem);
    }

    /*
     Method to remove items from the order list
     @params: IFoodOrderView: reference to the FoodOrder view class
     @return: none
     */
    public void removeItem(IFoodOrderView orderView) {
        int ERROR_VALUE = -1; // Variable for incorrect option

        // Check if order list is empty, to prevent deleting
        if (order.entrySet().isEmpty()) {
            orderView.display("There are no items in the order list!!");
            return;
        }

        // Loop continuously until correct item is read
        while (true) {
            String value = orderView.readFromConsole("Enter the item id: ");
            Integer foodID = Parse.tryParseInt(value);
            foodID = foodID != null ? foodID : ERROR_VALUE;

            // Check if the item is in the list, and remove it
            if (order.containsKey(foodID)) {
                order.remove(foodID);

                // After deleting, there is no need to reorder the list
                if (order.entrySet().isEmpty())
                    break;

                // Reorder the list, so the indexes are continuous
                int index = 0;
                Map<Integer, Map<String, Object>> newOrder = new HashMap<>();
                for (Entry<Integer, Map<String, Object>> entry : order.entrySet()) {
                    newOrder.put(++index, entry.getValue());
                }
                order = newOrder;

                break;
            } else {
                orderView.display("Please enter only the available food Id!!");
            }
        }
    }

    /*
     Method to finalize the order
     @params: int: the restaurant ID
              int: the service ID
              String: the user ID
              IFoodChargesModel: reference to the FoodCharges model class
              IFoodOrder: reference to the FoodOrder controller class
              IFoodOrderView: reference to the FoodOrder view class
              IFoodOrderModel: reference to the FoodOrder model class
              IDatabase: reference to the Database class
              ICouponCode: reference to the Coupon class
              IPay: reference to the Pay class
     @return: boolean: returns true if order is submitted successfully, else false
     */
    public boolean finalizeOrder(int restaurantID, int serviceID, String userID, IFoodChargesModel charges, IFoodOrder orderIfc,
                                 IFoodOrderView orderView, IFoodOrderModel orderModel, IDatabase database,
                                 ICouponCode code, IPay pay) {
        float discount = 0;             // Variable to store discount
        boolean couponApplied = false;  // Variable to check the status of applied coupon

        while (true) {
            // Check if order list is empty, to prevent submitting
            if (order.entrySet().isEmpty()) {
                orderView.display("Order list is empty!!");
                return false;
            }
            // Display the order list so user can see the final charge
            float total = orderView.displayCharges(restaurantID, serviceID, discount, charges, orderIfc, database);

            // Choose the option
            orderView.display("1: Add Coupon, 2: Submit Order, 3: Go Back");
            String value = orderView.readFromConsole("Enter the choice: ");
            Integer choice = Parse.tryParseInt(value);
            choice = choice != null ? choice : -1;

            boolean status = false; // Track the status of submitting order
            switch (choice) {
                case 1:
                    // Enter coupon code
                    if (!couponApplied)
                        discount = code.applyCoupon();
                    else
                        orderView.display("Coupon has already been applied.");

                    // Check for invalid coupon code
                    if (discount < 0) {
                        orderView.display("Coupon code is invalid.");
                        discount = 0;
                    } else {
                        couponApplied = true;
                        orderView.display("Coupon of value " + discount + " applied.");
                    }
                    break;
                case 2:
                    // Submit order
                    status = orderModel.submitOrder(restaurantID, serviceID, userID, discount, total, charges, orderIfc,
                            database, pay);
                    if (!status)
                        orderView.display("Order was not submitted!! Try again.");
                    else {
                        // Reset order after successful submit
                        order = new HashMap<>();

                        // Obtain rating of service
                        IUserRating rating = new UserRating();
                        rating.rateRestaurant(restaurantID);

                        // Display the order status
                        orderModel.getOrderStatus(this, database);
                        orderView.displayOrderStatus(this);

                        // Read the choice until correct choice is entered
                        while (true) {
                            orderView.display("1: Return to Home page, 2: Return to Food Menu");
                            value = orderView.readFromConsole("Enter the choice: ");
                            choice = Parse.tryParseInt(value);
                            choice = choice != null ? choice : -1;

                            // Check for valid choice
                            if (choice > 0 && choice < 3)
                                break;
                            else
                                orderView.display("Incorrect choice. Please enter valid choice");
                        }

                        // To handle return to food menu
                        if (choice == 2)
                            return false;
                    }
                    break;
                case 3:
                    // Choice to return to previous menu
                    String yesOrNo = orderView.readFromConsole("You will be taken back to previous menu. "
                            + "Do you want to continue (y/n)? ");
                    if (yesOrNo.equalsIgnoreCase("y"))
                        return false;
                    break;
                default:
                    orderView.display("Enter correct choice!!");
            }

            // Go to previous menu after successful submit
            if (status)
                break;
        }
        return true;
    }
}
