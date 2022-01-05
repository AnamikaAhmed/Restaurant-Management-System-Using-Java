package restaurant.model.payment;

import org.junit.jupiter.api.Test;
import restaurant.model.utilities.IDatabase;
import restaurant.model.utilities.SQLDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Group 1
 * @author: Dinesh Kumar Baalajee Jothi
 * @description: Test class for testing the model of the credit card payment methods
 */

public class CreditPaymentModelTest {

    ICreditPaymentModel creditModel;
    IDatabase database;

    public CreditPaymentModelTest() {
        creditModel = new CreditPaymentModel();
        database = new SQLDatabase();
        database.connect("localhost", "payment_csci5308", "root", "dinesh");
    }

    @Test
    void creditPaymentTrueTest() {
        ArrayList<String> creditCardDetails = new ArrayList<>();
        String cardNumber = "1234 1234 1234 1234";
        String expiryDate = "01/21";
        String cvvNumber = "123";
        Float billValue = 200f;
        creditCardDetails.add(cardNumber);
        creditCardDetails.add(expiryDate);
        creditCardDetails.add(cvvNumber);
        Map<String, String> testMap = new HashMap<>();
        testMap.put("ExpiryDate","01/21");
        testMap.put("CVV","123");
        testMap.put("Balance","9900");
        testMap.put("AccountNumber","1234 1234 1234 1234");
        assertEquals(testMap,creditModel.creditPayment(database, creditCardDetails, billValue));
    }

    @Test
    void creditPaymentFalseTest() {
        ArrayList<String> creditCardDetails = new ArrayList<>();
        String cardNumber = "1234 1111 1234 1234";
        String expiryDate = "01/21";
        String cvvNumber = "122";
        Float billValue = 200f;
        creditCardDetails.add(cardNumber);
        creditCardDetails.add(expiryDate);
        creditCardDetails.add(cvvNumber);
        Map<String, String> testMap = new HashMap<>();
        testMap.put("ExpiryDate","");
        testMap.put("CVV","");
        testMap.put("Balance","0");
        testMap.put("AccountNumber","");
        assertEquals(testMap,creditModel.creditPayment(database, creditCardDetails, billValue));
    }

}