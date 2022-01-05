package restaurant.model.payment;

import org.junit.jupiter.api.Test;
import restaurant.model.utilities.IDatabase;
import restaurant.model.utilities.SQLDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Group 1
 * @author: Dinesh Kumar Baalajee Jothi
 * @description: Test class for testing the model of the debit card payment methods
 */

import static org.junit.jupiter.api.Assertions.*;

public class DebitPaymentModelTest {
    IDebitPaymentModel debitModel;
    IDatabase database;

    public DebitPaymentModelTest() {
        debitModel = new DebitPaymentModel();
        database = new SQLDatabase();
        database.connect("localhost", "payment_csci5308", "root", "dinesh");
    }

    @Test
    void debitPaymentTrueTest() {
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
        assertEquals(testMap,debitModel.debitPayment(database, creditCardDetails, billValue));
    }

    @Test
    void debitPaymentFalseTest() {
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
        assertEquals(testMap,debitModel.debitPayment(database, creditCardDetails, billValue));
    }
}