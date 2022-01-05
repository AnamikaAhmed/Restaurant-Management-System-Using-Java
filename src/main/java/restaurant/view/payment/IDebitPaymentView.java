package restaurant.view.payment;

import java.util.ArrayList;
import java.util.List;

/**
 * Group 1
 * @author: Dinesh Kumar Baalajee Jothi
 * @description: Interface view for debit card payment
 */

public interface IDebitPaymentView {
    List<String> GetDebitDetails();
    void displayInfo(String text);
}
