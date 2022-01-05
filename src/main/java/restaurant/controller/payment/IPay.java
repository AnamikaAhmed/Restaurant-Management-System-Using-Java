package restaurant.controller.payment;

/**
 * Group 1
 * @author: Dinesh Kumar Baalajee Jothi
 * @description: Interface for Pay class
 */

public interface IPay {

    boolean finishPayment(Float TotalCost, String customerID);
    void addPayment();
}
