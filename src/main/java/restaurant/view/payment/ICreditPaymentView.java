package restaurant.view.payment;

import java.util.ArrayList;
import java.util.List;

/**
 * Group 1
 * @author: Dinesh Kumar Baalajee Jothi
 * @description: Interface view for credit card payment
 */

public interface ICreditPaymentView {
    List<String> GetCreditDetails();
    void displayInfo(String text);
}
