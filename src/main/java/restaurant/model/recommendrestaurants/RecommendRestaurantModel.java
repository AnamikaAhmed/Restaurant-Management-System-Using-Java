package restaurant.model.recommendrestaurants;

import restaurant.controller.Restaurant;
import restaurant.model.utilities.IDatabase;
import restaurant.model.utilities.SQLDatabase;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Group 1
 * @author: Pavan Abburi
 * @description: Model class to fetch recommended list
 *               of restaurants.
 */
public class RecommendRestaurantModel implements IRecommendRestaurantModel
{
    IDatabase db;                                           //Database interface to connect to database
    List<Restaurant> recommendedList = new ArrayList<>();   // List to store recommendations

    public RecommendRestaurantModel(IDatabase db){
        this.db = db;
    }

    /*
     recommend the restaurants to user based
     on his previous dining experience.
     @params: String: Pincode that entered by the user
              String: EmailID of user
     @return: List: Recommendation Restaurants
     */
    public List<Restaurant> getRecommendationList(String pincode, String emailID) {

        String query = "SELECT r.RestaurantID,r.Restaurant_Name,r.Address,r.Ratings,r.Pincode \n" +
                "FROM restaurants r\n" +
                "JOIN restaurantcuisineinfo rc ON r.RestaurantID = rc.RestaurantID\n" +
                "WHERE r.Pincode='" + pincode +"'"+
                "AND rc.CuisineName IN (\n" +
                "SELECT ri.CuisineName\n" +
                "FROM restaurantcuisineinfo ri\n" +
                "JOIN food_order_history vi ON ri.RestaurantID = vi.RestaurantID\n" +
                "WHERE UserID='" + emailID +"'"+
                ");";

        try {
            IDatabase db = new SQLDatabase();
            db.connect(System.getenv("HOST"), System.getenv("DATABASE"), System.getenv("USER_NAME"), System.getenv("PASSWORD"));
            ResultSet res = db.retrieve(query);
            while (res.next()) {
                int id = res.getInt("RestaurantID");
                String name = res.getString("Restaurant_Name");
                String address = res.getString("Address");
                double rating = res.getDouble("Ratings");
                recommendedList.add(new Restaurant(id, name, address,Integer.parseInt(pincode), rating));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return recommendedList;
    }

}
