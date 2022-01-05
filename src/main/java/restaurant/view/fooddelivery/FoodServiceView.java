package restaurant.view.fooddelivery;

import restaurant.controller.fooddelivery.IFood;

import java.util.Map;
import java.util.Scanner;

/**
 * Group 1
 * @author: Milan Ganesh Acharya
 * @description: View class to display the Services on the console, and read values from the console
 */
public class FoodServiceView implements IFoodServiceView {
    /*
     Method to display the services on the console
     @params: IFood: reference to the FoodService controller class
     @return: none
     */
    public void displayFoodService(IFood food) {
        System.out.println("-----------------------------------------------------------------------------------------");
        for (Map.Entry<Integer, Map<String, Object>> entry : food.getTable().entrySet()) {
            System.out.print(entry.getKey() + ": " + entry.getValue().get("Type") + ", ");
        }
        System.out.println();
        System.out.println("-----------------------------------------------------------------------------------------");
    }

    /*
     Method to read the type of service from the console
     @params: none
     @return: String: the value read from the console
     */
    public String readFromConsole() {
        System.out.print("Select the type of service: ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    /*
     Method to display the text on the console
     @params: String: the text to display on the console
     @return: none
     */
    public void display(String text) {
        System.out.println(text);
    }
}
