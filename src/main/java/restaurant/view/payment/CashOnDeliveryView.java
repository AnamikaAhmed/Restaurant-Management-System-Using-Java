package restaurant.view.payment;

/**
 * Group 1
 * @author: Dinesh Kumar Baalajee Jothi
 * @description: View class for cash on delivery
 */


public class CashOnDeliveryView implements ICashOnDeliveryView {

    /*
     * View method for cash on delivery
     * @params: BillValue: The total bill value
     * @return: none
     */
    public void DisplayCOD(Float BillValue) {
        System.out.println("\n You have opted for cash on Delivery! \n Your Bill Amount is : $"+BillValue+"  Please Keep Change :)");
    }
}
