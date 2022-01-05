package restaurant.controller.payment;

import org.junit.jupiter.api.Test;

import restaurant.model.payment.CreditPaymentModel;
import restaurant.model.payment.ICreditPaymentModel;

import restaurant.model.utilities.IDatabase;
import restaurant.model.utilities.SQLDatabase;

import restaurant.view.payment.CreditPaymentView;
import restaurant.view.payment.ICreditPaymentView;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Group 1
 * @author: Dinesh Kumar Baalajee Jothi
 * @description: Test class for testing the controller methods for credit card payment
 */

import static org.junit.jupiter.api.Assertions.*;

public class CreditPaymentTest {

    CreditPayment creditPay;
    ICreditPaymentModel creditModel;
    ICreditPaymentView creditView;
    IDatabase database;
    HashMap<String,String> customerEnteredInfo;

    public CreditPaymentTest() {
        creditPay = new CreditPayment();
        creditModel = new CreditPaymentModel();
        creditView = new CreditPaymentView();
        database = new SQLDatabase();
        database.connect("localhost", "payment_csci5308", "root", "dinesh");
        customerEnteredInfo = new HashMap<>();
    }

    @Test
    void creditPaymentValidateTrueTest() {

        Float billValue = 200f;
        ArrayList<String> inputCardDetails = new ArrayList<>();
        inputCardDetails.add("1234 1234 1234 1234");
        inputCardDetails.add("01/21");
        inputCardDetails.add("123");

        customerEnteredInfo.put("ExpiryDate","01/21");
        customerEnteredInfo.put("CVV","123");
        customerEnteredInfo.put("Balance","9900");
        customerEnteredInfo.put("AccountNumber","1234 1234 1234 1234");
        creditPay.customerEnteredInfo = inputCardDetails;

        assertEquals(true,creditPay.validate(database,creditView,billValue,creditModel));
    }

    @Test
    void creditPaymentValidateFalseTest() {

        Float billValue = 200f;
        ArrayList<String> inputCardDetails = new ArrayList<>();
        inputCardDetails.add("1234 1234 2222 1234");
        inputCardDetails.add("01/19");
        inputCardDetails.add("123");

        customerEnteredInfo.put("ExpiryDate","01/21");
        customerEnteredInfo.put("CVV","123");
        customerEnteredInfo.put("Balance","9900");
        customerEnteredInfo.put("AccountNumber","1234 1234 1234 1234");
        creditPay.customerEnteredInfo = inputCardDetails;

        assertEquals(false,creditPay.validate(database,creditView,billValue,creditModel));
    }


}