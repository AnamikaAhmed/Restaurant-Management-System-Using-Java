package restaurant.view;

import java.util.Scanner;

/**
 * Group 1
 * @author: Milan Ganesh Acharya
 * @description: View class for reading and writing data from and to the console
 */
public class OrderView implements IOrderView {
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
     Method to display the text on the console
     @params: String: the text to display on the console
     @return: none
     */
    public void display(String text) {
        System.out.println(text);
    }
}
