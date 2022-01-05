package restaurant.model.payment;

import restaurant.model.utilities.IDatabase;

import java.sql.ResultSet;

/**
 * Group 1
 * @author: Dinesh Kumar Baalajee Jothi
 * @description: Model class for payment with restaurant wallet
 */

public class RestaurantWalletModel implements IRestaurantWalletModel {

    /*
     * Method to validate wallet balance from the database
     * @params: customerID: the ID of the customer
                TotalValue: the bill value to be paid
                IDatabase: reference to the database interface
     * @return: Float: Returns the balance of the wallet
     */
    public Float paywallet(IDatabase database, Float TotalValue, String customerID) {



        int balance = -1;

        try {
            // Query to retrieve the customer wallet details
            ResultSet result_set = database.retrieve("Select * from restaurant_wallet where customer_id = '"+customerID+"';");
            //Checking if the details exists
            while (result_set.next()) {
                balance = result_set.getInt("wallet_balance");

                if (balance > TotalValue) {
                    //Displaying the user the wallet balance
                    System.out.println("\n Your wallet balance : $ " + balance + "\n You paid : $ " + TotalValue);
                } else {
                    System.out.println("\n Payment failed due to insufficient Balance" + "\n Please try some other method");
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return (float) balance;

    }
}
