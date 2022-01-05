package restaurant.controller.payment;

import restaurant.model.payment.IRestaurantWalletUpdateModel;
import restaurant.model.utilities.IDatabase;

import restaurant.view.payment.IRestaurantWalletView;

/**
 * Group 1
 * @author: Dinesh Kumar Baalajee Jothi
 * @description: Interface for payment with restaurant wallet
 */

public interface IRestaurantWallet {
    boolean confirmPayment(Float balance, IRestaurantWalletView restaurantView,
                           IRestaurantWalletUpdateModel walletModel, IDatabase database, String customerID, Float billValue);
}
