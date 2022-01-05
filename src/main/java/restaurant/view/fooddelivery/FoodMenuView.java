package restaurant.view.fooddelivery;

import restaurant.controller.fooddelivery.IFood;

import java.util.Map;
import java.util.Scanner;

/**
 * Group 1
 * @author: Milan Ganesh Acharya
 * @description: View class for displaying the food items on the console, and reading the data from console
 */
public class FoodMenuView implements IFoodMenuView {
    /*
     Display the food menu on the console
     @params: IFood: reference to the Food Menu controller class
     @return: none
     */
    public void displayFoodMenu(IFood food) {
        System.out.printf("%-4s %-40s %-70s %8s\n","Id", "Name", "Description", "Price");
        System.out.println("-----------------------------------------------------------------------------------------"
        + "------------------------------------");

        for (Map.Entry<Integer, Map<String, Object>> entry : food.getTable().entrySet()) {
            String name = (String) entry.getValue().get("Name");
            String description = (String) entry.getValue().get("Description");
            System.out.printf("%-4d %-40s %-70s %8.2f\n", entry.getKey(),
                    name.substring(0, Math.min(name.length(), 40)),
                    !description.equals("") ? description.substring(0, Math.min(description.length(), 70)) : "",
                    (float) entry.getValue().get("Price"));
        }

        System.out.println("-----------------------------------------------------------------------------------------"
                + "------------------------------------");
    }

    /*
     Read the item from the console
     @params: String: name of the item to read from the console
     @return: String: value read from the console
     */
    public String readFromConsole(String item) {
        System.out.print("Enter the " + item + ": ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    /*
     Read the item from the console
     @params: String: text to display the console
     @return: none
     */
    public void display(String text) {
        System.out.println(text);
    }
}
