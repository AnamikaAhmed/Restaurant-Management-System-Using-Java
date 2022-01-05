package restaurant.controller.recommendrestaurants;

import restaurant.controller.nearbyrestaurants.INearByRestaurantController;
import restaurant.model.recommendrestaurants.IRecommendRestaurantModel;
import restaurant.controller.Restaurant;
import restaurant.view.recommendrestaurants.IRecommendRestaurantView;
import java.util.List;

/**
 * Group 1
 * @author: Pavan Abburi
 * @description: Controller class to show recommendations.
 */
public class RecommendRestaurantController implements IRecommendRestaurantController {

    INearByRestaurantController nearByRestaurantController;         //reference to bearby restaurant controller class
    IRecommendRestaurantModel recommendRestaurantModel;             //reference to model class
    IRecommendRestaurantView recommendRestaurantView;               //reference to view class

    /*
     Method to take input from View class and
     fetch recommendations based on user previous dining history
     @params: INearByRestaurantController: reference to the nearby restaurant controller interface
              IRecommendRestaurantModel: reference to the model interface
              IRecommendRestaurantView: reference to the view interface
              String: EmailID of User
     @return: none
     */
    public void init(INearByRestaurantController nearByRestaurantController, IRecommendRestaurantModel recommendRestaurantModel, IRecommendRestaurantView recommendRestaurantView, String emailID) {
        this.nearByRestaurantController = nearByRestaurantController;
        this.recommendRestaurantModel = recommendRestaurantModel;
        this.recommendRestaurantView = recommendRestaurantView;
        String pincode = nearByRestaurantController.getPinCode();
        List<Restaurant> recommendationList = recommendRestaurantModel.getRecommendationList(pincode,emailID);
        showRecommendations(recommendationList);
    }

    /*
     Private Method to send input to View class
     which shows recommendations to the user
     @params: List: list of recommendations
     @return: none
     */
    private void showRecommendations(List<Restaurant> recommendationList) {
        recommendRestaurantView.printRecommendations(recommendationList);
    }
}
