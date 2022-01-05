package restaurant.controller.payment;

import restaurant.view.payment.ICashOnDeliveryView;
/**
 * Group 1
 * @author: Dinesh Kumar Baalajee Jothi
 * @description: Controller class for Cash on Delivery
 */

public class CashOnDelivery implements ICashOnDelivery {

    /*
     * Method to display the coupon code CLI message
     * @params:   Float: The bill value of the order
                  ICashOnDeliveryView: reference to the CashOnDeliveryView class
     * @return: none
     */

    public void displayCOD(Float billValue, ICashOnDeliveryView cashPayView) {

        cashPayView.DisplayCOD(billValue);
    }
}
