package restaurant.model.recommendrestaurants;

import org.junit.jupiter.api.Test;
import restaurant.controller.Restaurant;
import restaurant.model.utilities.IDatabase;
import restaurant.model.utilities.SQLDatabase;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RecommendRestaurantModelTest {

    Restaurant restaurant1 = new Restaurant(2,"Curry Village Restaurant","1569 Dresden Row, Halifax, Nova Scotia B3J 2K4 Canada",600020,4);
    Restaurant restaurant2 = new Restaurant(5,"Eliot & Vine","2305 Clifton St Corner of Cunard & Clifton, Halifax, Nova Scotia B3K 4T9 Canada",600020,5);

    @Test
    void getRecommendationList_True() {
        List<Restaurant> actualRestaurantList;
        List<Restaurant> expectedRestaurantList;
        List<Restaurant> restaurantList = new ArrayList<>();
        restaurantList.add(restaurant1);
        restaurantList.add(restaurant2);
        expectedRestaurantList = restaurantList;
        IDatabase db = new SQLDatabase();
        RecommendRestaurantModel recommendRestaurantModel = new RecommendRestaurantModel(db);
        actualRestaurantList = recommendRestaurantModel.getRecommendationList("600020","pavan.abburi@gmail.com");
        assertEquals(expectedRestaurantList,actualRestaurantList,"Recommendation list doesnot match");
    }

    @Test
    void getRecommendationList_False() {
        List<Restaurant> actualRestaurantList;
        List<Restaurant> expectedRestaurantList;
        List<Restaurant> restaurantList = new ArrayList<>();
        restaurantList.add(restaurant2);
        expectedRestaurantList = restaurantList;
        IDatabase db = new SQLDatabase();
        RecommendRestaurantModel recommendRestaurantModel = new RecommendRestaurantModel(db);
        actualRestaurantList = recommendRestaurantModel.getRecommendationList("600020","pavan.abburi@gmail.com");
        assertNotEquals(expectedRestaurantList,actualRestaurantList,"Recommendation list matches");
    }
}