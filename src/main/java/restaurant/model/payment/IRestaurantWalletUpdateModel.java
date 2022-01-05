package restaurant.model.payment;

import restaurant.model.utilities.IDatabase;

/**
 * Group 1
 * @author: Dinesh Kumar Baalajee Jothi
 * @description: Parent Interface for updating the wallet balance
 */

public interface IRestaurantWalletUpdateModel {
    boolean update(IDatabase database, Float value, Float ordervalue, String customer_id);
}
