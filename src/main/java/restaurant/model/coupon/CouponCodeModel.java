package restaurant.model.coupon;

import restaurant.model.utilities.IDatabase;

import java.sql.ResultSet;

/**
 * Group 1
 * @author: Dinesh Kumar Baalajee Jothi
 * @description: Model class for getting the coupon discount value
 */


public class CouponCodeModel implements ICouponCodeModel {

    private static int ERROR_VALUE = -1;

    /*
     * Method to check the coupon code from the database
     * @params: Coupon: the coupon code used
                IDatabase: reference to the database interface
     * @return: integer value: Coupon value if valid else -1
     */
    public int checkCoupon(IDatabase database, String Coupon) {

        // Query to retrieve the coupon code
        ResultSet result_set = database.retrieve("select * from coupon where Coupon_ID ='"+Coupon+"';");

        try {
            while (result_set.next()) {
                int value = result_set.getInt("Coupon_Value");
                if(value > 0){
                    return value;
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return ERROR_VALUE;
    }
}
