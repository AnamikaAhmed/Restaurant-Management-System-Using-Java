package restaurant.controller.checkrestaurant;

import restaurant.view.tablereservation.ReservationView;
import restaurant.controller.IOrder;
import restaurant.controller.Order;
import restaurant.controller.Restaurant;
import restaurant.model.checkrestaurant.ICheckRestaurantModel;
import restaurant.view.checkrestaurant.ICheckRestaurantView;

import java.sql.SQLException;
import java.util.List;

/**
 * Group 1
 * @author: Pavan Abburi
 * @description: Controller class to check if user
 *               entered valid restaurant ID or not.
 */
public class CheckRestaurantController implements ICheckRestaurantController{

    ICheckRestaurantView checkRestaurantView;        //Interface for CheckRestaurantView class
    ICheckRestaurantModel checkRestaurantModel;      //Interface for CheckRestaurantModel class
    IOrder order;                                    //Interface for Order class

    public CheckRestaurantController(){
        order = new Order();
    }

    /*
     Method to take input from View class and
     check if restaurant is valid or not
     @params: String: the restaurant ID
              List: list of restaurants
              ICheckRestaurantModel: reference to the model interface
              ICheckRestaurantView: reference to the view interface
              String: user EmailID
     @return: none
     */
    public void init(String restaurantID, List<Restaurant> restaurantList, ICheckRestaurantModel checkRestaurantModel, ICheckRestaurantView checkRestaurantView,String userEmail) throws SQLException {
        this.checkRestaurantModel = checkRestaurantModel;
        this.checkRestaurantView = checkRestaurantView;
        checkRestaurantModel.setRestaurantList(restaurantList);
        boolean isValidRestaurant = checkRestaurantModel.isRestaurantValid(restaurantID);
        checkRestaurant(isValidRestaurant,restaurantID,userEmail,order);
    }

    /*
     Private Method to send input to View class and
     other classes if restaurant ID is valid
     @params: Boolean: true,if the restaurant ID is valid else, false
              String: restaurant ID
              String: user EmailID
              IOrder: reference to the order interface
     @return: none
     */
    private void checkRestaurant(boolean isValidRestaurant,String restaurantID, String emailID, IOrder order) throws SQLException {
        while(isValidRestaurant == false){
            checkRestaurantView.showInvalidRestaurant();
            restaurantID = checkRestaurantView.readFromConsole();
            isValidRestaurant = checkRestaurantModel.isRestaurantValid(restaurantID);
        }
        boolean shouldTakeUserInput = true;
        checkRestaurantView.showValidRestaurant();;
        do {
            String line = checkRestaurantView.readFromConsole();
            switch (line) {
                case "1":
                    order.displayMenu(Integer.parseInt(restaurantID), emailID);
                    shouldTakeUserInput=false;
                    break;
                case "2":
                    ReservationView restaurantView = new ReservationView();
                    restaurantView.allOptions(emailID,Integer.parseInt(restaurantID));
                    shouldTakeUserInput=false;
                    break;

                default:
                    checkRestaurantView.showInvalidRestaurant();
            }
        } while (shouldTakeUserInput);
    }
}
