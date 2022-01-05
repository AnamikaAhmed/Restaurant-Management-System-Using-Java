package restaurant.controller.payment;

import restaurant.view.payment.ICashOnDeliveryView;

/**
 * Group 1
 * @author: Dinesh Kumar Baalajee Jothi
 * @description: Interface for cash on delivery
 */

public interface ICashOnDelivery {
    void displayCOD(Float billValue, ICashOnDeliveryView cashPayView);
}
