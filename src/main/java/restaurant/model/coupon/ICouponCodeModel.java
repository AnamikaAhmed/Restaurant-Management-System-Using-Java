package restaurant.model.coupon;

import restaurant.model.utilities.IDatabase;

/**
 * Group 1
 * @author: Dinesh Kumar Baalajee Jothi
 * @description: Interface for getting the coupon code discount
 */

public interface ICouponCodeModel {

    int checkCoupon(IDatabase database, String Coupon);
}