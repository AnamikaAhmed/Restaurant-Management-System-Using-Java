package restaurant.controller.payment;

import restaurant.model.payment.ICreditPaymentModel;

import restaurant.model.utilities.IDatabase;

import restaurant.view.payment.ICreditPaymentView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Group 1
 * @author: Dinesh Kumar Baalajee Jothi
 * @description: Controller class for credit payment
 */

public class CreditPayment implements ICreditPayment {

    protected List<String> customerEnteredInfo;

    public CreditPayment() {
        customerEnteredInfo = new ArrayList<>();
    }

    /*
     * Method to get credit the card information from the customer
     * @params:   ICreditPaymentView: reference to the CreditPaymentView class
     * @return: none
     */

    public void getCardInfo(ICreditPaymentView creditPayView) {
        customerEnteredInfo = creditPayView.GetCreditDetails();
    }

    /*
     * Method to validate credit card details and balance from the database
     * @params: creditPayView: view of the credit card payment
                billValue: the bill value to be paid
                database: reference to the database interface
                creditPayModel: model of credit card payment
     * @return: boolean: Returning whether the payment was success or not
     */

    public boolean validate(IDatabase database, ICreditPaymentView creditPayView, Float billValue,
                         ICreditPaymentModel creditPayModel) {

        boolean paymentStatus = false;

        try{
            String Expiry_Date = customerEnteredInfo.get(1);
            String CVV_number = customerEnteredInfo.get(2);

            Map<String,String> dBCardInfo = creditPayModel.creditPayment(database,customerEnteredInfo,billValue);

            int balance = 0;
            String cardNumber = "";
            String CVV = "";
            String expiryDate = "";

            for (HashMap.Entry mapElement : dBCardInfo.entrySet()) {

                String key = (String)mapElement.getKey();
                String value = ((String)mapElement.getValue());

                switch (key) {
                    case "Balance":
                        balance = Integer.parseInt(value);
                        break;
                    case "AccountNumber":
                        cardNumber = value;
                        break;
                    case "ExpiryDate":
                        expiryDate = value;
                        break;
                    case "CVV":
                        CVV = value;
                        break;
                }
            }

            if (balance > billValue && expiryDate.equals(Expiry_Date) && CVV.equals(CVV_number)) {

                creditPayView.displayInfo("\n Payment success :D");
                paymentStatus = true;

            } else {
                creditPayView.displayInfo("\n Payment not success");
            }

        } catch (Exception exception) {
            System.out.println("Exception caught");
        }

        return paymentStatus;
    }
}
