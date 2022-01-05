package restaurant.view.payment;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Group 1
 * @author: Dinesh Kumar Baalajee Jothi
 * @description: View class for payment with debit card
 */

public class DebitPaymentView implements IDebitPaymentView {

    /*
      * View method to get the debit card details from the user
      * @params: none
      * @return: ArrayList of the card details
      */
    public List<String> GetDebitDetails() {
        //Creating the variables needed for the program
        String debitCardNumber = "";
        String Expiry_date = "";
        String CVV_number = "";

        Scanner debitScanner = new Scanner(System.in);

        //Arraylist to save the details
        List<String> debitDetail = new ArrayList<>();

        //Displaying the required information to the customer
        System.out.println("\n Enter the Debit Card Number :");
        debitCardNumber = debitScanner.nextLine();
        System.out.println("\n Enter the expiry date (DD/YY)");
        Expiry_date = debitScanner.nextLine();
        System.out.println("\n Enter the CVV number");
        CVV_number = debitScanner.nextLine();

        //Adding the details
        debitDetail.add(debitCardNumber);
        debitDetail.add(Expiry_date);
        debitDetail.add(CVV_number);

        return debitDetail;
    }

    public void displayInfo(String text) {
        System.out.println(text);
    }
}
