package restaurant.model.payment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import restaurant.model.utilities.IDatabase;

/**
 * Group 1
 * @author: Dinesh Kumar Baalajee Jothi
 * @description: Parent Interface for payment with credit card
 */

public interface ICreditPaymentModel {

    Map<String,String> creditPayment(IDatabase database, List<String> creditDetails, Float TotalValue);
}
