package restaurant.model.coupon;

import org.junit.jupiter.api.Test;

import restaurant.model.utilities.IDatabase;
import restaurant.model.utilities.SQLDatabase;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Group 1
 * @author: Dinesh Kumar Baalajee Jothi
 * @description: Test class for testing the controller methods for applying coupon code discount to the bill amount
 */

public class CouponCodeModelTest {

    ICouponCodeModel couponModel;
    IDatabase database;


    public CouponCodeModelTest() {
        couponModel = new CouponCodeModel();
        database = new SQLDatabase();
        database.connect("localhost", "coupon_csci5308", "root", "dinesh");
    }

    @Test
    public void validCouponApplyTest() {
        String couponCode = "Sale1234";
        assertEquals(5,couponModel.checkCoupon(database,couponCode));
    }

    @Test
    public void invalidCouponApplyTest() {
        String couponCode = "InvalidCoupon";
        assertEquals(-1,couponModel.checkCoupon(database,couponCode));
    }

}
