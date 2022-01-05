package restaurant.model.payment;

import restaurant.model.utilities.IDatabase;

/**
 * Group 1
 * @author: Dinesh Kumar Baalajee Jothi
 * @description: Model class for payment with restaurant wallet
 */


public class RestaurantWalletUpdateModel implements IRestaurantWalletUpdateModel {

    /*
     * Method to update the wallet balance
     * @params: customerID: the ID of the customer
                TotalValue: the bill value to be paid
                IDatabase: reference to the database interface
     * @return: no return
     */
    public boolean update(IDatabase database, Float balance, Float TotalValue, String customerID) {

        boolean updateStatus = false;

        if ( balance > TotalValue) {

            database.update("update restaurant_wallet set wallet_balance = wallet_balance - " + TotalValue + " where customer_id ='" + customerID + "';");
            updateStatus = true;

        }

        return updateStatus;
    }
}
