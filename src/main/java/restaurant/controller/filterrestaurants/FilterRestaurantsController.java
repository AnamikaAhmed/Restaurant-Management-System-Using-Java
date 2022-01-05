package restaurant.controller.filterrestaurants;

import restaurant.model.filterrestaurants.IFilterRestaurantsModel;
import restaurant.controller.Restaurant;
import restaurant.view.filterrestaurants.IFilterRestaurantsView;
import java.util.List;

/**
 * Group 1
 * @author: Pavan Abburi
 * @description: Controller class to filter restaurants
 *               list from total list.
 */
public class FilterRestaurantsController implements IFilterRestaurantsController
{
    IFilterRestaurantsView filterRestaurantsView;      //reference to view interface
    IFilterRestaurantsModel filterRestaurantsModel;    //reference to model interface

    /*
     Method to get list of filtered restaurants
     @params: none
     @return: List: Filtered Restaurants
     */
    @Override
    public List<Restaurant> getFilteredRestaurantList() {
        return filterRestaurantsModel.getCurrentFilteredRest();
    }

    /*
     Method to take input from View class and
     filter restaurants based on user input
     @params: List: list of restaurants
              IFilterRestaurantsView: reference to the view interface
              IFilterRestaurantsModel: reference to the model interface
     @return: none
     */
    @Override
    public void init(List<Restaurant> restaurantList, IFilterRestaurantsView filterRestaurantsView, IFilterRestaurantsModel filterRestaurantsModel) {
        this.filterRestaurantsView = filterRestaurantsView;
        this.filterRestaurantsModel = filterRestaurantsModel;
        filterRestaurantsModel.setRestaurantList(restaurantList);
        String rating = filterRestaurantsView.readFromConsole();
        List<Restaurant> filteredrestaurantList = filterRestaurantsModel.getFilteredRestaurants(rating);
        showFilteredRestaurant(filteredrestaurantList);
    }

    /*
     Method to send input to View class which
     displays filtered list of restaurants based on user input
     @params: List: list of filteredrestaurants
     @return: none
     */
    @Override
    public void showFilteredRestaurant(List<Restaurant> filteredrestaurantList) {
        while(filteredrestaurantList.isEmpty()){
            filterRestaurantsView.printFilterRestaurants(filteredrestaurantList);
            String rating = filterRestaurantsView.readFromConsole();
            filteredrestaurantList = filterRestaurantsModel.getFilteredRestaurants(rating);
        }
        filterRestaurantsView.printFilterRestaurants(filteredrestaurantList);
    }
}
