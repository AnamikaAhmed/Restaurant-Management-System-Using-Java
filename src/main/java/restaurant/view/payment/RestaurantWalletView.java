package restaurant.view.payment;

import java.util.Scanner;

/**
 * Group 1
 * @author: Dinesh Kumar Baalajee Jothi
 * @description: View class to get confirmation from the user for the payment
 */

public class RestaurantWalletView implements IRestaurantWalletView {

    /*
      * View method to display the confirmation message
      * @params: none
      * @return: int: integer depicting if the payment is success or not
      */

    public int displayConfirmation(Float billValue) {

        int correctChoice = 1;
        int incorrectChoice = -1;
        System.out.println("\n Do you wish to pay $"+billValue+" - 1.Yes  2.No ");
        Scanner scanner = new Scanner(System.in);

        int paymentChoice = scanner.nextInt();

        if (paymentChoice == 1) {
            System.out.println(" ***** Payment Success *****");
            return correctChoice;
        } else if(paymentChoice == 2) {
            System.out.println(" ***** Payment Failure *****");
            return incorrectChoice;
        } else {
            return incorrectChoice;
        }
    }
}
