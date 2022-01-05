package restaurant.model.nearbyrestaurants;

import restaurant.controller.Restaurant;
import restaurant.model.utilities.IDatabase;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Group 1
 * @author: Pavan Abburi
 * @description: Model class for fetching restaurants that are present
 *               in Pincode entered by the user.
 */
public class NearByRestaurantModel implements  INearByRestuarantModel{

    IDatabase db;                                              // Database Interface to connect to database
    List<Restaurant> restaurantList = new ArrayList<>();       // List for nearby restaurants
    String pincode;                                            // Pincode entered by the user

    public NearByRestaurantModel(IDatabase db){
        this.db = db;
    }

    /*
     Method to fetch nearby restaurants from database
     @params: String: the pincode
     @return: List: returns the list of restaurants
                    that are present in Pincode
    */
    @Override
    public List<Restaurant> getNearByRestaurant(String pincode) {
        restaurantList = new ArrayList<>();
        String query = "select * from restaurants where pincode ="+pincode;
        try {
            db.connect(System.getenv("HOST"), System.getenv("DATABASE"), System.getenv("USER_NAME"), System.getenv("PASSWORD"));
            ResultSet res = db.retrieve(query);
            while (res.next()) {
                int id = res.getInt("RestaurantID");
                String name = res.getString("Restaurant_Name");
                String address = res.getString("Address");
                double rating = res.getDouble("Ratings");
                restaurantList.add(new Restaurant(id, name, address,Integer.parseInt(pincode), rating));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.pincode=pincode;
        return restaurantList;
    }

    /*
     Method to fetch the list of nearby restaurants
     @params: none
     @return: List: returns the list of restaurants
    */
    @Override
    public List<Restaurant> getCurrentNearByRestaurant() {
        return restaurantList;
    }

    /*
     Method to get the Pincode entered by user
     @params: none
     @return: List: returns the Pincode
    */
    public String getPincode() {
        return pincode;
    }
}
