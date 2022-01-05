package restaurant.view.payment;

/**
 * Group 1
 * @author: Dinesh Kumar Baalajee Jothi
 * @description: View class for displaying the different types of payment methods
 */

public class PaymentView implements IPaymentView {

    /*
      * View method to display the different types of payment methods
      * @params: none
      * @return: none
      */

    public void DisplayPaymentMethod() {
        //Getting the payment mode from the user
        System.out.println("Payment Mode: Choose your payment mode " +
                "\n 1.Credit card " +
                "\n 2.Debit Card " +
                "\n 3.Restaurant Wallet" +
                "\n 4.Cash on Delivery");
    }
}
