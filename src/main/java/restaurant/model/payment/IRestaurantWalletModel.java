package restaurant.model.payment;

import restaurant.model.utilities.IDatabase;

/**
 * Group 1
 * @author: Dinesh Kumar Baalajee Jothi
 * @description: Parent Interface for payment with restaurant wallet
 */

public interface IRestaurantWalletModel {

    Float paywallet(IDatabase database, Float TotalValue, String customerID);

}
