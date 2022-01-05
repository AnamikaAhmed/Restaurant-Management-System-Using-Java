package restaurant.controller.userrating;

import restaurant.model.userrating.IUserRatingModel;
import restaurant.model.userrating.IUserRatingUpdateModel;
import restaurant.model.userrating.UserRatingModel;
import restaurant.model.userrating.UserRatingUpdateModel;
import restaurant.model.utilities.IDatabase;
import restaurant.model.utilities.SQLDatabase;
import restaurant.view.userrating.IUserRatingView;
import restaurant.view.userrating.UserRatingView;

/**
 * Group 1
 * @author: Dinesh Kumar Baalajee Jothi
 * @description: Interface for rating the restaurant
 */

public class UserRating implements IUserRating {

    private final IUserRatingView userRateView;
    private final IDatabase database;
    private final IUserRatingModel rateModel;
    private final IUserRatingUpdateModel rateUpdateModel;

    private int UserRating = 0;

    public UserRating() {
        userRateView = new UserRatingView();
        database = new SQLDatabase();
        database.connect(System.getenv("HOST"), System.getenv("DATABASE"), System.getenv("USER_NAME"), System.getenv("PASSWORD"));
        rateModel = new UserRatingModel();
        rateUpdateModel = new UserRatingUpdateModel();
    }

    /*
     * Method to get the rating from customer and the restaurant rating and updating the average
     * @params: restaurantID: The ID of the restaurant
     * @return: none
     */

    public void rateRestaurant(int restaurantID) {
        UserRating = userRateView.displayRatings();
        int restaurantRating = rateModel.getRating(database,restaurantID);
        int combinedRating = (UserRating+restaurantRating);
        int averageRating = combinedRating/2;
        rateUpdateModel.update(database,averageRating,restaurantID);
    }

    public static void main(String args[]) {
        UserRating userRate = new UserRating();
        userRate.rateRestaurant(4);

    }
}