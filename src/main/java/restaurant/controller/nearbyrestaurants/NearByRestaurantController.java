package restaurant.controller.nearbyrestaurants;

import restaurant.model.nearbyrestaurants.INearByRestuarantModel;
import restaurant.controller.Restaurant;
import restaurant.view.nearbyrestaurants.INearByRestaurantView;
import java.util.List;

/**
 * Group 1
 * @author: Pavan Abburi
 * @description: Controller class to show nearby restaurants.
 */
public class NearByRestaurantController implements INearByRestaurantController {

    INearByRestuarantModel nearByRestuarantModel;     //reference for model class
    INearByRestaurantView nearByRestaurantView;       //reference for view class

    /*
     Method to take input from View class and
     fetch restaurants based on user input
     @params: INearByRestuarantModel: reference to the model interface
              INearByRestaurantView: reference to the view interface
     @return: none
     */
    public void init(INearByRestuarantModel nearByRestuarantModel, INearByRestaurantView nearByRestaurantView){
        this.nearByRestuarantModel = nearByRestuarantModel;
        this.nearByRestaurantView = nearByRestaurantView;
        String pincode = nearByRestaurantView.readFromConsole();
        List<Restaurant> restaurantList = nearByRestuarantModel.getNearByRestaurant(pincode);
        showNearByRestaurant(restaurantList);
    }

    /*
     Method to send input to View class which
     displays restaurants list based on user input
     @params: List: list of restaurants
     @return: none
     */
    @Override
    public void showNearByRestaurant(List<Restaurant> restaurantList) {
        while(restaurantList.isEmpty()) {
            nearByRestaurantView.printRestaurants(restaurantList);
            String pincode = nearByRestaurantView.readFromConsole();
            restaurantList = nearByRestuarantModel.getNearByRestaurant(pincode);
        }
        nearByRestaurantView.printRestaurants(restaurantList);
    }

    /*
     Method to get list of nearby restaurants
     @params: none
     @return: List: Nearby Restaurants
     */
    @Override
    public List<Restaurant> getNearbyRestaurantList() {
        return nearByRestuarantModel.getCurrentNearByRestaurant();
    }

    /*
     Method to get pincode entered by user
     @params: none
     @return: String: Pincode
     */
    public String getPinCode() {
        String pincode = nearByRestuarantModel.getPincode();
        return pincode;
    }

}
