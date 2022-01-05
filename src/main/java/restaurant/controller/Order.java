package restaurant.controller;

import restaurant.controller.coupon.CouponCode;
import restaurant.controller.coupon.ICouponCode;
import restaurant.controller.fooddelivery.*;
import restaurant.controller.orderhistory.OrderHistory;
import restaurant.controller.payment.IPay;
import restaurant.controller.payment.Pay;
import restaurant.controller.takeaway.FoodOrder;
import restaurant.controller.takeaway.IFoodOrder;
import restaurant.controller.utilities.Parse;
import restaurant.model.fooddelivery.*;
import restaurant.model.takeaway.FoodOrderModel;
import restaurant.model.takeaway.IFoodOrderModel;
import restaurant.model.utilities.IDatabase;
import restaurant.model.utilities.SQLDatabase;
import restaurant.view.IOrderView;
import restaurant.view.OrderView;
import restaurant.view.fooddelivery.*;
import restaurant.view.takeaway.FoodOrderView;
import restaurant.view.takeaway.IFoodOrderView;

/**
 * Group 1
 * @author: Milan Ganesh Acharya
 * @description: Controller class for accessing the overall food menu
 */
public class Order implements IOrder {
    private final IOrderView view;
    private final IFoodMenu menu;
    private final IFoodMenuModel menuModel;
    private final IFoodMenuView menuView;
    private final IDatabase database;
    private final IFoodCategory category;
    private final IFoodCategoryModel categoryModel;
    private final IFoodCategoryView categoryView;
    private final IFoodService service;
    private final IFoodServiceModel serviceModel;
    private final IFoodServiceView serviceView;
    private final IFoodOrder order;
    private final IFoodOrderModel orderModel;
    private final IFoodOrderView orderView;
    private final IFoodChargesModel charges;
    private final ICouponCode code;
    private final IPay pay;

    public Order() {
        database = new SQLDatabase();
        database.connect(System.getenv("HOST"), System.getenv("DATABASE"), System.getenv("USER_NAME"), System.getenv("PASSWORD"));
        view = new OrderView();
        menu = new FoodMenu();
        menuModel = new FoodMenuModel();
        menuView = new FoodMenuView();
        category = new FoodCategory();
        categoryModel = new FoodCategoryModel();
        categoryView = new FoodCategoryView();
        service = new FoodService();
        serviceModel = new FoodServiceModel();
        serviceView = new FoodServiceView();
        order = new FoodOrder();
        orderModel = new FoodOrderModel();
        orderView = new FoodOrderView();
        charges = new FoodChargesModel();
        code = new CouponCode();
        pay = new Pay();
    }

    private void showServices(int restaurantID) {
        serviceModel.getFoodService(restaurantID, service, database);
        serviceView.displayFoodService(service);
        service.selectFoodService(serviceView);
    }

    private void showMenu(int restaurantID) {
        menuModel.getFoodMenu(restaurantID, database, menu);
        menuView.displayFoodMenu(menu);
    }

    private void showCategories(int restaurantID) {
        categoryModel.getFoodCategories(restaurantID, category, database);
        categoryView.displayFoodCategories(category);
        category.selectFoodCategory(categoryView);
        menuModel.getFoodCategoryMenu(restaurantID, category.getCategoryID(), database, menu);
        menuView.displayFoodMenu(menu);
    }

    private void searchMenu(int restaurantID) {
        menu.searchMenu(restaurantID, menu, database, menuView, menuModel);
        menuView.displayFoodMenu(menu);
    }

    private void addItem() {
        order.addItem(menu.selectFoodItem(menuView), orderView);
        menuView.displayFoodMenu(menu);
    }

    private void viewOrder() {
        orderView.displayOrder(order);
    }

    private void removeItem() {
        order.removeItem(orderView);
    }

    private boolean finalizeOrder(int restaurantID, String userID, int serviceID) {
        return order.finalizeOrder(restaurantID, serviceID, userID, charges, order, orderView, orderModel, database,
                code, pay);
    }

    /*
     Method to display the overall menu
     @params: int: the ID of the restaurant
              String: the ID of the user
     @return: none
     */
    public void displayMenu(int restaurantID, String userID) {
        int ERROR_VALUE = 99;   // Error value for incorrect entry

        showServices(restaurantID); // Show the choice of service, and accept the type from the user

        showMenu(restaurantID);     // Show the food menu initially

        // Code needs to run in a loop, for accepting multiple inputs from the user
        while (true) {
            view.display("1: Add Item, 2: Search Item, 3: Show Menu, 4: Show Categories, " +
                    "5: Remove Item, 6: View Order, 7: Finalize Order. Enter * or # to return to previous menu");

            // Read the choice from console
            String line = view.readFromConsole("Enter the option: ");
            if (line.equals("#") || line.equals("*")) {
                // If user want to go to previous menu
                String yesOrNo = view.readFromConsole("You will be taken back to previous menu. " +
                        "Do you want to continue (y/n)? ");
                if (yesOrNo.equalsIgnoreCase("y"))
                    break;
            }

            boolean exit = false;   // Variable to determine the exit condition of the loop
            // Parse the input value from the console
            Integer value = Parse.tryParseInt(line);
            // For successful parse use the input value, else use a random high value
            int option = value != null ? value : ERROR_VALUE;
            switch (option) {
                case 1: // Add items to order
                    addItem();
                    break;
                case 2: // Search the menu for an item
                    searchMenu(restaurantID);
                    break;
                case 3: // Show the food menu
                    showMenu(restaurantID);
                    break;
                case 4: // Show the categories of food items
                    showCategories(restaurantID);
                    break;
                case 5: // Remove items from order
                    removeItem();
                    break;
                case 6: // View the order
                    viewOrder();
                    break;
                case 7: // Finalize order. Show the total amount, charges, and taxes before submitting
                    exit = finalizeOrder(restaurantID, userID, service.getServiceID());
                    break;
                default: // For any incorrect choice
                    view.display("Please enter valid option");
            }

            // When order is successfully submitted, exit from the loop
            if (exit)
                break;
        }
    }
}
