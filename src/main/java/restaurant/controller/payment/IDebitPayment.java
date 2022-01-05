package restaurant.controller.payment;

import restaurant.model.payment.IDebitPaymentModel;
import restaurant.model.utilities.IDatabase;

import restaurant.view.payment.IDebitPaymentView;

/**
 * Group 1
 * @author: Dinesh Kumar Baalajee Jothi
 * @description: Interface for debit card payment
 */

public interface IDebitPayment {
    void getCardInfo(IDebitPaymentView debitPayView);
    boolean validate(IDatabase database, IDebitPaymentView debitPayView, Float billValue,
                     IDebitPaymentModel debitPayModel);
}
