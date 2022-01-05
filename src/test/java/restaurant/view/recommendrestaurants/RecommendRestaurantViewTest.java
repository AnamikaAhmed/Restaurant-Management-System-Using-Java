package restaurant.view.recommendrestaurants;

import org.junit.jupiter.api.Test;
import restaurant.controller.Restaurant;
import restaurant.view.filterrestaurants.FilterRestaurantsView;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RecommendRestaurantViewTest {

    Restaurant restaurant1 = new Restaurant(2,"Curry Village Restaurant","1569 Dresden Row, Halifax, Nova Scotia B3J 2K4 Canada",600020,4);
    Restaurant restaurant2 = new Restaurant(5,"Eliot & Vine","2305 Clifton St Corner of Cunard & Clifton, Halifax, Nova Scotia B3K 4T9 Canada",600020,5);
    Restaurant restaurant3 = new Restaurant(6,"Fredies Fantastic Fishhouse","8 Oland Cres, Halifax, Nova Scotia B3S 1C6 Canada",600020,4);

    @Test
    void printRecommendations_True() {
        List<Restaurant> restaurantList = new ArrayList<>();
        restaurantList.add(restaurant1);
        RecommendRestaurantView recommendRestaurantView = new RecommendRestaurantView();
        recommendRestaurantView.printRecommendations(restaurantList);
        assertEquals(2,restaurant1.getId(),"ID doesnot match");
        assertEquals("Curry Village Restaurant",restaurant1.getRestName(),"Names doesnot match");
        assertEquals(600020,restaurant1.getPincode(),"Pincodes doesnot match");
    }

    @Test
    void printRecommendations_False() {
        List<Restaurant> restaurantList = new ArrayList<>();
        restaurantList.add(restaurant1);
        RecommendRestaurantView recommendRestaurantView = new RecommendRestaurantView();
        recommendRestaurantView.printRecommendations(restaurantList);
        assertNotEquals(7,restaurant1.getId(),"ID match");
        assertNotEquals("Village Restaurant",restaurant1.getRestName(),"Names match");
        assertNotEquals(600021,restaurant1.getPincode(),"Pincodes match");
    }
}