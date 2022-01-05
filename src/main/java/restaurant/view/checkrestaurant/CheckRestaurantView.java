package restaurant.view.checkrestaurant;

import java.util.Scanner;

/**
 * Group 1
 * @author: Pavan Abburi
 * @description: View class to display/read contents on/from the console
 */
public class CheckRestaurantView implements ICheckRestaurantView{

    /*
     Reads the category from the console
     @params: none
     @return: String: value entered on the console
     */
    public String readFromConsole() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    /*
     Displays options to user for select on console
     @params: none
     @return: none
     */
    public void showValidRestaurant(){
        System.out.println("Please select your option:");
        System.out.println("1. Order Online  2.Table Reservation");
    }

    /*
     Displays to enter valid input on the console
     @params: none
     @return: none
     */
    public void showInvalidRestaurant()
    {
        System.out.println("Please enter valid input");
    }
}
