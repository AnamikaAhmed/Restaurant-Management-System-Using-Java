package restaurant.controller.payment;

import org.junit.jupiter.api.Test;

import restaurant.model.payment.DebitPaymentModel;
import restaurant.model.payment.IDebitPaymentModel;
import restaurant.model.utilities.IDatabase;
import restaurant.model.utilities.SQLDatabase;

import restaurant.view.payment.DebitPaymentView;
import restaurant.view.payment.IDebitPaymentView;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Group 1
 * @author: Dinesh Kumar Baalajee Jothi
 * @description: Test class for testing the controller methods for debit card payment
 */

public class DebitPaymentTest {

    DebitPayment debitPay;
    IDebitPaymentModel debitModel;
    IDebitPaymentView debitView;
    IDatabase database;
    HashMap<String,String> customerEnteredInfo;

    public DebitPaymentTest() {
        debitPay = new DebitPayment();
        debitModel = new DebitPaymentModel();
        debitView = new DebitPaymentView();
        database = new SQLDatabase();
        database.connect("localhost", "payment_csci5308", "root", "dinesh");
        customerEnteredInfo = new HashMap<>();
    }

    @Test
    void creditPaymentValidateTrueTest() {

        ArrayList<String> inputCardDetails = new ArrayList<>();
        inputCardDetails.add("1234 1234 1234 1234");
        inputCardDetails.add("01/21");
        inputCardDetails.add("123");

        customerEnteredInfo.put("ExpiryDate","01/21");
        customerEnteredInfo.put("CVV","123");
        customerEnteredInfo.put("Balance","9900");
        customerEnteredInfo.put("AccountNumber","1234 1234 1234 1234");
        debitPay.customerEnteredInfo = inputCardDetails;
        Float billValue = 200f;
        assertEquals(true,debitPay.validate(database,debitView,billValue,debitModel));
    }

    @Test
    void creditPaymentValidateFalseTest() {

        ArrayList<String> inputCardDetails = new ArrayList<>();
        inputCardDetails.add("1234 1234 2222 1234");
        inputCardDetails.add("01/19");
        inputCardDetails.add("123");

        customerEnteredInfo.put("ExpiryDate","01/21");
        customerEnteredInfo.put("CVV","123");
        customerEnteredInfo.put("Balance","9900");
        customerEnteredInfo.put("AccountNumber","1234 1234 1234 1234");
        debitPay.customerEnteredInfo = inputCardDetails;
        Float billValue = 200f;
        assertEquals(false,debitPay.validate(database,debitView,billValue,debitModel));
    }


}