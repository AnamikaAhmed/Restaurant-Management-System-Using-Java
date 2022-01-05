package restaurant.model.filterrestaurants;

import restaurant.controller.Restaurant;
import java.util.ArrayList;
import java.util.List;

/**
 * Group 1
 * @author: Pavan Abburi
 * @description: Model class to fetch filtered list
 *               of restaurants.
 */
public class FilterRestaurantsModel implements IFilterRestaurantsModel
{
    List<Restaurant> restaurantList;            //List of nearby restaurants
    List<Restaurant> filteredRestList;          //List of filtered restaurants

    /*
     Filters the restaurants list from nearby restaurants
     based on user entered option
     @params: String: Rating that entered by the user
     @return: List: Filtered Restaurants
     */
    @Override
    public List<Restaurant> getFilteredRestaurants(String rating) {
        filteredRestList = new ArrayList<>();
        double ratingAsDouble = Double.parseDouble(rating);
        try {
            for(Restaurant restaurant: restaurantList){
                if(restaurant.getRating() >= ratingAsDouble){
                    filteredRestList.add(restaurant);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return filteredRestList;
    }

    /*
     Method to give list of filtered restaurants
     @params: none
     @return: List: Filtered Restaurants
     */
    @Override
    public List<Restaurant> getCurrentFilteredRest() {
        return filteredRestList;
    }

    /*
     Method to give list of nearby restaurants i.e before filtering
     @params: none
     @return: List: Filtered Restaurants
     */
    @Override
    public List<Restaurant> getRestaurantList() {
        return restaurantList;
    }

    /*
     Method to set list of restaurants
     @params: none
     @return: none
     */
    @Override
    public void setRestaurantList(List<Restaurant> restaurantList) {
        this.restaurantList = restaurantList;
    }
}
