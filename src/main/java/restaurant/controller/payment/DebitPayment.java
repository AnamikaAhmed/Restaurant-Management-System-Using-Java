package restaurant.controller.payment;

import restaurant.model.payment.IDebitPaymentModel;

import restaurant.model.utilities.IDatabase;

import restaurant.view.payment.IDebitPaymentView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Group 1
 * @author: Dinesh Kumar Baalajee Jothi
 * @description: Controller class for Debit Card Payment
 */

public class DebitPayment implements IDebitPayment {

    protected List<String> customerEnteredInfo;

    /*
     * Method to get the debit card information from the customer
     * @params:   IDebitPaymentView: reference to the DebitPaymentView class
     * @return: none
     */

    public DebitPayment() {
        customerEnteredInfo = new ArrayList<>();
    }

    public void getCardInfo(IDebitPaymentView debitPayView) {
        customerEnteredInfo = debitPayView.GetDebitDetails();
    }

    /*
     * Method to validate debit card details and balance from the database
     * @params: debitPayView: view of the debit card payment
                billValue: the bill value to be paid
                database: reference to the database interface
                debitPayModel: model of debit card payment
     * @return: boolean: Returning whether the payment was success or not
     */

    public boolean validate(IDatabase database, IDebitPaymentView debitPayView, Float billValue,
                            IDebitPaymentModel debitPayModel) {

        boolean paymentStatus = false;

        String Expiry_Date = customerEnteredInfo.get(1);
        String CVV_number = customerEnteredInfo.get(2);

        Map<String,String> dBCardInfo = debitPayModel.debitPayment(database,customerEnteredInfo,billValue);
        try {

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

                debitPayView.displayInfo("\n Payment success :D");
                paymentStatus = true;

            } else {
                debitPayView.displayInfo("\n Payment not success");
            }

        } catch (Exception exception){
            System.out.println("Exception caught");
        }

        return paymentStatus;
    }
}
