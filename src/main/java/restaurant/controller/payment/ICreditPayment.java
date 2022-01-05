package restaurant.controller.payment;

import restaurant.model.payment.ICreditPaymentModel;
import restaurant.model.utilities.IDatabase;
import restaurant.view.payment.ICreditPaymentView;

/**
 * Group 1
 * @author: Dinesh Kumar Baalajee Jothi
 * @description: Interface for Credit card payment
 */

public interface ICreditPayment {
    void getCardInfo(ICreditPaymentView creditPayView);
    boolean validate(IDatabase database, ICreditPaymentView creditPayView, Float billValue,
                  ICreditPaymentModel creditPayModel);
}
