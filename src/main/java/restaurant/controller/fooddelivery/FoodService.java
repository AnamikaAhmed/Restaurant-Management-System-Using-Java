package restaurant.controller.fooddelivery;

import restaurant.controller.utilities.Parse;
import restaurant.view.fooddelivery.IFoodServiceView;

import java.util.Map;

/**
 * Group 1
 * @author: Milan Ganesh Acharya
 * @description: Controller class to handle the Service type of a restaurant
 */
public class FoodService implements IFoodService {
    int serviceID;                                      // Service ID of the restaurant
    private Map<Integer, Map<String, Object>> table;    // Map to hold the table of the restaurant service

    public FoodService() {
        serviceID = -1;
        table = null;
    }

    public int getServiceID() {
        return serviceID;
    }

    /*
     Method to get the restaurant service table
     @params: none
     @return: Map<Integer, Map<String, Object>>: the table of restaurant service
     */
    public Map<Integer, Map<String, Object>> getTable() {
        return table;
    }

    /*
     Method to get the restaurant service table
     @params: Map<Integer, Map<String, Object>>: the table of restaurant service
     @return: none
     */
    public void setTable(Map<Integer, Map<String, Object>> table) {
        this.table = table;
    }

    /*
     Method to select the restaurant service
     @params: IFoodServiceView: reference to the FoodService view class
     @return: none
     */
    public void selectFoodService(IFoodServiceView foodServiceView) {
        int ERROR_VALUE = -1;   // Error value for incorrect entry

        // Loop continuously until correct value is entered
        while (true) {
            String value = foodServiceView.readFromConsole();
            Integer service = Parse.tryParseInt(value);
            service = service != null ? service : ERROR_VALUE;

            // Check if correct service has been entered
            if (table.containsKey(service)) {
                serviceID = (Integer) table.get(service).get("ServiceID");
                break;
            } else {
                foodServiceView.display("Please select correct service");
            }
        }
    }
}
