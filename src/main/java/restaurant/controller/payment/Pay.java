package restaurant.controller.payment;

import restaurant.model.payment.*;
import restaurant.model.utilities.IDatabase;
import restaurant.model.utilities.SQLDatabase;
import restaurant.view.payment.*;

import java.util.List;
import java.util.Scanner;

/**
 * Class is responsible for displaying the different types
 * of payment and call the views of different types of payment and
 * the database operations of the different types of payment.
 */

public class Pay implements IPay {

    private final IDatabase database;
    private final ICashOnDelivery cashPay;
    private final ICashOnDeliveryView cashPayView;
    private final ICreditPaymentView creditPayView;
    private final ICreditPaymentModel creditPayModel;
    private final ICreditPayment creditPay;
    private final IDebitPayment debitPay;
    private final IDebitPaymentView debitPayView;
    private final IDebitPaymentModel debitPayModel;
    private final IRestaurantWallet RestaurantWallet;
    private final IRestaurantWalletView walletView;
    private final IRestaurantWalletModel walletModel;
    private final IRestaurantWalletUpdateModel walletUpdateModel;
    private final ICashOnDelivery cashOnDelivery;


    public Pay() {
        database = new SQLDatabase();
        database.connect(System.getenv("HOST"), System.getenv("DATABASE"), System.getenv("USER_NAME"), System.getenv("PASSWORD"));
        cashPay = new CashOnDelivery();
        cashPayView = new CashOnDeliveryView();
        creditPay = new CreditPayment();
        debitPay = new DebitPayment();
        creditPayView = new CreditPaymentView();
        creditPayModel = new CreditPaymentModel();
        debitPayView =  new DebitPaymentView();
        debitPayModel = new DebitPaymentModel();
        RestaurantWallet = new RestaurantWallet();
        walletView = new RestaurantWalletView();
        walletModel = new RestaurantWalletModel();
        walletUpdateModel = new RestaurantWalletUpdateModel();
        cashOnDelivery = new CashOnDelivery();
    }

    private boolean CashOnDelivery(Float BillValue) {
        cashPay.displayCOD(BillValue,cashPayView);
        return true;
    }

    private boolean CreditCardPayment(Float billValue) {
        creditPay.getCardInfo(creditPayView);
        return creditPay.validate(database, creditPayView ,billValue, creditPayModel);
    }

    private boolean DebitCardPayment(Float billValue) {
        debitPay.getCardInfo(debitPayView);
        return debitPay.validate(database, debitPayView ,billValue, debitPayModel);
    }

    private boolean RestaurantWalletPayment(Float billValue, String customerID) {
        return RestaurantWallet.confirmPayment(walletModel.paywallet(database,billValue,"ABCD1234"),
                walletView, walletUpdateModel, database, customerID, billValue);
    }

    public boolean finishPayment(Float TotalCost, String customerID) {

        boolean paymentStatus = false;

        Scanner sc = new Scanner(System.in);

        IPaymentView IP = new PaymentView();
        IP.DisplayPaymentMethod();

        // Getting the payment choice from the customer
        int choice = sc.nextInt();

        switch(choice) {
            case 1:
                paymentStatus = CreditCardPayment(TotalCost);
                break;
            case 2:
                paymentStatus = DebitCardPayment(TotalCost);
                break;
            case 3:
                paymentStatus = RestaurantWalletPayment(TotalCost, customerID);
                break;
            case 4:
                paymentStatus = CashOnDelivery(TotalCost);
                break;
            default:
                System.out.println("\n Please choose a correct option");
            }

            return paymentStatus;
        }

        /*
         Method to store the payment details of the user
         @params: none
         @return: none
         */
        public void addPayment() {
            int DEFAULT_VALUE = 1000;   // The default value to be stored for the user

            // Read the details of the credit/debit card
            List<String> details = debitPayView.GetDebitDetails();

            // Store the data
            boolean status = debitPayModel.addPayment(database, details, DEFAULT_VALUE);
            if (status) {
                System.out.println("Successfully added payment method");
            } else {
                System.out.println("Failed to add payment method");
            }
        }

        public  static void main(String args[]) {

            Pay pay = new Pay();
            if(pay.finishPayment(120.00F,"ABCD1234")){
                System.out.println("Payment successssss");
            }

        }
}
