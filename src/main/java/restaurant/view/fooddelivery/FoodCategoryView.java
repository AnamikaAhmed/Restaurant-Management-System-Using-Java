package restaurant.view.fooddelivery;

import restaurant.controller.fooddelivery.IFood;

import java.util.Map;
import java.util.Scanner;

/**
 * Group 1
 * @author: Milan Ganesh Acharya
 * @description: View class to display/read contents on/from the console
 */
public class FoodCategoryView implements IFoodCategoryView {
    /*
     Displays the name of all categories in the table on the console
     @params: reference to the food controller interface
     @return: none
     */
    public void displayFoodCategories(IFood food) {
        System.out.println("-----------------------------------------------------------------------------------------");
        for (Map.Entry<Integer, Map<String, Object>> entry : food.getTable().entrySet()) {
            System.out.print(entry.getKey() + ": " + entry.getValue().get("Name") + ", ");
        }
        System.out.println();
        System.out.println("-----------------------------------------------------------------------------------------");
    }

    /*
     Reads the category from the console
     @params: none
     @return: String: value entered on the console
     */
    public String readFromConsole() {
        System.out.print("Enter category: ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    /*
     Displays the text on the console
     @params: String: the text to print on the console
     @return: none
     */
    public void display(String text) {
        System.out.println(text);
    }
}
