package restaurant.model.payment;

import restaurant.model.utilities.IDatabase;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Group 1
 * @author: Dinesh Kumar Baalajee Jothi
 * @description: Model class for payment with debit card
 */

public class DebitPaymentModel implements  IDebitPaymentModel {

    /*
     * Method to validate debit card details and balance from the database
     * @params: debitDetails: arraylist containing the card details
                TotalValue: the bill value to be paid
                IDatabase: reference to the database interface
     * @return: boolean: Returning whether the payment was success or not
     */
    public Map<String,String> debitPayment(IDatabase database, List<String> debitDetails, Float TotalValue) {


        Map<String,String> dBCardInfo = new HashMap<>();

        // Getting the account number of the card details from the ArrayList
        String Account_number = debitDetails.get(0);

        // Query to retrieve the credit card information from the database
        ResultSet result_set = database.retrieve("Select * from payment_details where card_number='"+Account_number+"';");

        int balance = 0;
        String accountNumber = "";
        String expiryDate = "";
        String cvvNumber = "";

        try {
            while (result_set.next()) {

                balance = result_set.getInt("card_balance");
                accountNumber = result_set.getString("card_number");
                expiryDate = result_set.getString("card_expiry");
                cvvNumber = result_set.getString("card_cvv");

            }

            dBCardInfo.put("Balance",String.valueOf(balance));
            dBCardInfo.put("AccountNumber",accountNumber);
            dBCardInfo.put("ExpiryDate",expiryDate);
            dBCardInfo.put("CVV",cvvNumber);

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return dBCardInfo;
    }

    /*
     * Method to add card details to the database
     * @params: IDatabase: reference to the database interface
     *          List: list containing the details of the card
     *          int: the balance amount in the card
     * @return: boolean: Returns true if payment was successful else returns false
     */
    public boolean addPayment(IDatabase database, List<String> details, int value) {
        int rows = database.insert("insert into payment_details values ('" +
                details.get(0) + "'," +
                details.get(2) + ",'" +
                details.get(1) + "'," +
                value + ")");
        return rows > 0;
    }
 }

