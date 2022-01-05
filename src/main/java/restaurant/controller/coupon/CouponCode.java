package restaurant.controller.coupon;

import restaurant.model.coupon.CouponCodeModel;
import restaurant.model.coupon.ICouponCodeModel;
import restaurant.model.utilities.IDatabase;
import restaurant.model.utilities.SQLDatabase;
import restaurant.view.coupon.CouponCodeView;
import restaurant.view.coupon.ICouponCodeView;

import java.util.Scanner;

/**
 * Group 1
 * @author: Dinesh Kumar Baalajee Jothi
 * @description: Controller class for returning the coupon code discount
 */

public class CouponCode implements ICouponCode {

    /*
     * Method to return the coupon discount code
     * @params: none
     * @return: int: the discount value
     */

    public int applyCoupon() {

        IDatabase database = new SQLDatabase();
        database.connect(System.getenv("HOST"), System.getenv("DATABASE"), System.getenv("USER_NAME"), System.getenv("PASSWORD"));

        Scanner couponScanner = new Scanner(System.in);

        /* This shows in the console for the customer to enter the coupon code */
        ICouponCodeView CouponView = new CouponCodeView();
        CouponView.DisplayCoupon();

        String coupon = couponScanner.next();

        ICouponCodeModel couponModel = new CouponCodeModel();

        return couponModel.checkCoupon(database,coupon);
    }

    public static void main(String args[]) {
        CouponCode CC = new CouponCode();
        CC.applyCoupon();
    }
}
