package restaurant.model.checkrestaurant;

import restaurant.controller.Restaurant;
import java.util.List;

/**
 * Group 1
 * @author: Pavan Abburi
 * @description: Model class to check if user entered
 *               restaurant ID is valid or not.
 */
public class CheckRestaurantModel implements ICheckRestaurantModel {

    List<Restaurant> restaurantList;        //List to store all nearby restaurants

    /*
     Method to read the to check if valid id is entered or not
     @params: String: restaurant ID entered by user
     @return: Boolean: True, if valid restaurant
                       False, if invalid restaurant is selected.
     */
    public boolean isRestaurantValid(String restaurantId) {
        boolean isValidId = false;
        try {
            for (Restaurant restaurant : restaurantList) {
                if (restaurant.getId() == Integer.parseInt(restaurantId)) {
                    isValidId = true;
                    break;
                }
            }
            } catch(Exception e) {
                e.printStackTrace();
            }
        return isValidId;
        }

    /*
     Method to set the restaurant List
     @params: List: List of restaurants
     @return: none
     */
    public void setRestaurantList(List<Restaurant> restaurantList) {
        this.restaurantList = restaurantList;
    }
    }
