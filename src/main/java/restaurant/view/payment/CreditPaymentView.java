package restaurant.view.payment;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Group 1
 * @author: Dinesh Kumar Baalajee Jothi
 * @description: View class for payment with credit card
 */

public class CreditPaymentView implements ICreditPaymentView {

    /*
     * View method to get the credit card details from the user
     * @params: none
     * @return: ArrayList of the card details
     */
    public List<String> GetCreditDetails() {

        // Creating the variables needed for getting the credit card details
        String creditCardNumber ;
        String Expiry_date = "";
        String CVV_number ;

        Scanner creditScanner = new Scanner(System.in);

        //Arraylist to save the details
        List<String> creditDetail = new ArrayList<>();

        //Displaying the required information to the customer
        System.out.println("\n Enter the Credit Card Number :");
        creditCardNumber = creditScanner.nextLine();
        System.out.println("\n Enter the expiry date (DD/YY)");
        Expiry_date = creditScanner.nextLine();
        System.out.println("\n Enter the CVV number");
        CVV_number = creditScanner.nextLine();

        //Adding the details
        creditDetail.add(creditCardNumber);
        creditDetail.add(Expiry_date);
        creditDetail.add(CVV_number);

        return creditDetail;
    }

    public void displayInfo(String text) {
        System.out.println(text);
    }


}
