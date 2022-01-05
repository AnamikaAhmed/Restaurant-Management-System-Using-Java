package restaurant.model.payment;

import restaurant.model.utilities.IDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Group 1
 * @author: Dinesh Kumar Baalajee Jothi
 * @description: Parent Interface for payment with debit card
 */

public interface IDebitPaymentModel {

    Map<String,String> debitPayment(IDatabase database, List<String> creditDetails, Float TotalValue);
    boolean addPayment(IDatabase database, List<String> details, int value);
}
