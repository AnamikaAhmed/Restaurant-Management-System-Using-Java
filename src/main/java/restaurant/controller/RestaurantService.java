package restaurant.controller;

import restaurant.controller.checkrestaurant.CheckRestaurantController;
import restaurant.controller.checkrestaurant.ICheckRestaurantController;
import restaurant.controller.filterrestaurants.FilterRestaurantsController;
import restaurant.controller.filterrestaurants.IFilterRestaurantsController;
import restaurant.controller.nearbyrestaurants.INearByRestaurantController;
import restaurant.controller.nearbyrestaurants.NearByRestaurantController;
import restaurant.controller.recommendrestaurants.IRecommendRestaurantController;
import restaurant.controller.recommendrestaurants.RecommendRestaurantController;

import restaurant.model.checkrestaurant.CheckRestaurantModel;
import restaurant.model.checkrestaurant.ICheckRestaurantModel;
import restaurant.model.filterrestaurants.FilterRestaurantsModel;
import restaurant.model.filterrestaurants.IFilterRestaurantsModel;
import restaurant.model.nearbyrestaurants.INearByRestuarantModel;
import restaurant.model.nearbyrestaurants.NearByRestaurantModel;
import restaurant.model.recommendrestaurants.IRecommendRestaurantModel;
import restaurant.model.recommendrestaurants.RecommendRestaurantModel;


import restaurant.model.utilities.SQLDatabase;
import restaurant.view.checkrestaurant.CheckRestaurantView;
import restaurant.view.checkrestaurant.ICheckRestaurantView;
import restaurant.view.filterrestaurants.FilterRestaurantsView;
import restaurant.view.filterrestaurants.IFilterRestaurantsView;
import restaurant.view.nearbyrestaurants.INearByRestaurantView;
import restaurant.view.nearbyrestaurants.NearByRestaurantView;
import restaurant.view.recommendrestaurants.IRecommendRestaurantView;
import restaurant.view.recommendrestaurants.RecommendRestaurantView;

import java.sql.SQLException;
import java.util.Scanner;

/**
 * Group 1
 * @author: Pavan Abburi
 * @description: Driver class that handles all the methods related
 *               to finding restaurants.
 */
public class RestaurantService implements IRestaurantService {

    INearByRestaurantView nearByRestaurantView;
    INearByRestuarantModel nearByRestuarantModel;
    INearByRestaurantController nearByRestaurantController;
    IFilterRestaurantsView filterRestaurantsView;
    IFilterRestaurantsModel filterRestaurantsModel;
    IFilterRestaurantsController filterRestaurantsController;
    IRecommendRestaurantController recommendRestaurantController;
    IRecommendRestaurantModel recommendRestaurantModel;
    IRecommendRestaurantView recommendRestaurantView;
    ICheckRestaurantController checkRestaurantController;
    ICheckRestaurantModel checkRestaurantModel;
    ICheckRestaurantView checkRestaurantView;

    public RestaurantService() {
        nearByRestaurantView = new NearByRestaurantView();
        nearByRestuarantModel =  new NearByRestaurantModel( new SQLDatabase());
        nearByRestaurantController = new NearByRestaurantController();
        filterRestaurantsView = new FilterRestaurantsView();
        filterRestaurantsModel =  new FilterRestaurantsModel();
        filterRestaurantsController = new FilterRestaurantsController();
        recommendRestaurantController = new RecommendRestaurantController();
        recommendRestaurantModel = new RecommendRestaurantModel( new SQLDatabase());
        recommendRestaurantView = new RecommendRestaurantView();
        checkRestaurantController = new CheckRestaurantController();
        checkRestaurantModel = new CheckRestaurantModel();
        checkRestaurantView = new CheckRestaurantView();
    }

    /*
     Driver method to show nearby restaurants that
     user wants in a given pincode
     @params: none
     @return: none
     */
    private void showNearByRestaurants() {
        nearByRestaurantController.init(nearByRestuarantModel, nearByRestaurantView);
    }

    /*
     Driver method to show filtered list of restaurants
     that user wants in a given pincode
     @params: none
     @return: none
     */
    private void filterRestaurants() {
        filterRestaurantsController.init(nearByRestaurantController.getNearbyRestaurantList(), filterRestaurantsView, filterRestaurantsModel);
    }

    /*
     Driver method to show list of recommended restaurants
     that user wants in a given pincode based on User's
     previous dining history.
     @params: String: User EmailID
     @return: none
     */
    private void recommendRestaurants(String emailID) {
        recommendRestaurantController.init(nearByRestaurantController,recommendRestaurantModel, recommendRestaurantView, emailID);
    }

    /*
     Driver method to check if user
     selected valid restaurant or not.
     @params: String: User EmailID
              String: RestaurantID
     @return: none
     */
    private void checkRestaurants(String restaurantId, String emailID) throws SQLException {
        checkRestaurantController.init(restaurantId, nearByRestaurantController.getNearbyRestaurantList(),checkRestaurantModel,checkRestaurantView,emailID);
    }

    /*
     Driver method for restaurant finder that
     also calls other methods based on user input
     @params: String : User EmailID
     @return: none
     */
    public void service(String emailID) throws SQLException {
        showNearByRestaurants();

        Scanner scanner = new Scanner(System.in);
        boolean shouldTakeUserInput = true;
        while(shouldTakeUserInput){
            String line = scanner.next();
            switch (line.toUpperCase()) {
                case "F":
                    filterRestaurants();
                    break;
                case "R":
                    recommendRestaurants(emailID);
                    break;
                default:
                    checkRestaurants(line,emailID);
                    shouldTakeUserInput = false;
            }
        }
    }
}