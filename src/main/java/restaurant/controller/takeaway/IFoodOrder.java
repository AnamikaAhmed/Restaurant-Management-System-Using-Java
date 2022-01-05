package restaurant.controller.takeaway;

import restaurant.controller.coupon.ICouponCode;
import restaurant.controller.payment.IPay;
import restaurant.model.fooddelivery.IFoodChargesModel;
import restaurant.model.takeaway.IFoodOrderModel;
import restaurant.model.utilities.IDatabase;
import restaurant.view.takeaway.IFoodOrderView;

import java.util.Map;

/**
 * Group 1
 * @author: Milan Ganesh Acharya
 * @description: Interface for accessing the food order
 */
public interface IFoodOrder {
    int getOrderID();
    void setOrderID(int orderID);
    Map<Integer, Map<String, Object>> getOrderStatus();
    void setOrderStatus(Map<Integer, Map<String, Object>> orderStatus);
    Map<Integer, Map<String, Object>> getOrder();
    void addItem(Map<String, Object> foodItem, IFoodOrderView orderView);
    void removeItem(IFoodOrderView orderView);
    boolean finalizeOrder(int restaurantID, int serviceID, String userID, IFoodChargesModel charges, IFoodOrder orderIfc,
                          IFoodOrderView orderView, IFoodOrderModel orderModel, IDatabase database, ICouponCode code,
                          IPay pay);

}
