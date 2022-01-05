package restaurant.model.payment;

import org.junit.jupiter.api.Test;
import restaurant.controller.payment.IRestaurantWallet;
import restaurant.controller.payment.RestaurantWallet;
import restaurant.model.utilities.IDatabase;
import restaurant.model.utilities.SQLDatabase;
import restaurant.view.payment.IRestaurantWalletView;
import restaurant.view.payment.RestaurantWalletView;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Group 1
 * @author: Dinesh Kumar Baalajee Jothi
 * @description: Test class for testing the model of the restaurant wallet payment
 */

class RestaurantWalletModelTest {

    IRestaurantWalletModel restaurantWalletModel;
    IRestaurantWallet RestaurantWallet;
    IRestaurantWalletView walletView;
    IRestaurantWalletUpdateModel walletUpdate;
    IDatabase database;

    public RestaurantWalletModelTest() {
        restaurantWalletModel = new RestaurantWalletModel();
        RestaurantWallet = new RestaurantWallet();
        walletView = new RestaurantWalletView();
        walletUpdate = new RestaurantWalletUpdateModel();
        database = new SQLDatabase();
        database.connect("localhost", "payment_csci5308", "root", "dinesh");
    }

    /*
     *  Test for payment with the restaurant wallet (Note the value is updated at the end of the test
     *  so, it will fail the second time when the test is run without changing expected value)
    */
    @Test
    void payWalletTest() {
        String customerID = "ABCD1234";
        Float billValue = 52f;
        assertEquals(17937,restaurantWalletModel.paywallet(database, billValue, customerID));
    }

    // Test for updating the balance of the restaurant wallet when balance is greater than bill value
    @Test
    void payWalletUpdateTest() {
        Float balance = 20000f;
        Float TotalValue = 124f;
        String customerID = "ABCD1234";
        assertTrue(walletUpdate.update(database, balance, TotalValue, customerID));
    }

    // Test for updating the balance of the restaurant wallet when balance is greater than bill value
    @Test
    void payWalletUpdateFailTest() {
        Float balance = 20f;
        Float TotalValue = 124f;
        String customerID = "ABCD1234";
        assertFalse(walletUpdate.update(database, balance, TotalValue, customerID));
    }
}