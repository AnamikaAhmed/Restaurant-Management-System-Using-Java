package restaurant.model.takeaway;

import restaurant.controller.payment.IPay;
import restaurant.controller.takeaway.IFoodOrder;
import restaurant.model.fooddelivery.IFoodChargesModel;
import restaurant.model.utilities.IDatabase;

/**
 * Group 1
 * @author: Milan Ganesh Acharya
 * @description: Interface for accessing the food order model
 */
public interface IFoodOrderModel {
    boolean submitOrder(int restaurantID, int serviceID, String userID, float discount, float total,
                        IFoodChargesModel charges, IFoodOrder order, IDatabase database, IPay pay);
    void getOrderStatus(IFoodOrder order, IDatabase database);
}
