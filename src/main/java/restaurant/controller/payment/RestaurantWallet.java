package restaurant.controller.payment;

import restaurant.model.payment.IRestaurantWalletUpdateModel;
import restaurant.model.utilities.IDatabase;
import restaurant.view.payment.IRestaurantWalletView;

/**
 * Group 1
 * @author: Dinesh Kumar Baalajee Jothi
 * @description: Class for payment with restaurant wallet
 */

public class RestaurantWallet implements IRestaurantWallet {

    protected int confirmation;

    public RestaurantWallet(){
        confirmation = 0;
    }

    /*
     * Method to validate payment with restaurant wallet
     * @params: balance:     the balance of the wallet
                walletView:  reference to the RestaurantWalletView class
                database:    reference to the database interface
                walletModel: model of restaurant wallet
                totalValue:  total bill value
     * @return: boolean: Returning whether the payment was success or not
     */

    public boolean confirmPayment(Float balance, IRestaurantWalletView walletView, IRestaurantWalletUpdateModel
                                  walletModel, IDatabase database, String customerID, Float totalValue) {

        int confirmation = walletView.displayConfirmation(totalValue);
        if (confirmation == 1) {
            walletModel.update(database,balance, totalValue, customerID);
            return true;
        } else {
            return false;
        }
    }
}
