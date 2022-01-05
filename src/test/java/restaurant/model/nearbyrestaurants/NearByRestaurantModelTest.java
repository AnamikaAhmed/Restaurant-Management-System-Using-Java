package restaurant.model.nearbyrestaurants;

import restaurant.controller.Restaurant;
import org.junit.jupiter.api.Test;
import restaurant.model.utilities.IDatabase;
import restaurant.model.utilities.SQLDatabase;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NearByRestaurantModelTest {

    Restaurant restaurant1 = new Restaurant(2,"Curry Village Restaurant","1569 Dresden Row, Halifax, Nova Scotia B3J 2K4 Canada",600020,4);
    Restaurant restaurant2 = new Restaurant(5,"Eliot & Vine","2305 Clifton St Corner of Cunard & Clifton, Halifax, Nova Scotia B3K 4T9 Canada",600020,5);
    Restaurant restaurant3 = new Restaurant(6,"Fredies Fantastic Fishhouse","8 Oland Cres, Halifax, Nova Scotia B3S 1C6 Canada",600020,4);

    @Test
    void getNearByRestaurant_True() {
        List<Restaurant> actualRestaurantList;
        List<Restaurant> expectedRestaurantList;
        List<Restaurant> restaurantList = new ArrayList<>();
        restaurantList.add(restaurant1);
        restaurantList.add(restaurant2);
        restaurantList.add(restaurant3);
        expectedRestaurantList = restaurantList;
        IDatabase db = new SQLDatabase();
        NearByRestaurantModel nearByRestaurantModel = new NearByRestaurantModel(db);
        actualRestaurantList = nearByRestaurantModel.getNearByRestaurant("600020");

        assertEquals(expectedRestaurantList,actualRestaurantList,"Nearby Restaurants Lists doesnot match");
    }

    @Test
    void getNearByRestaurant_False() {
        List<Restaurant> actualRestaurantList;
        List<Restaurant> expectedRestaurantList;
        List<Restaurant> restaurantList = new ArrayList<>();
        restaurantList.add(restaurant1);
        restaurantList.add(restaurant2);
        expectedRestaurantList = restaurantList;
        IDatabase db = new SQLDatabase();
        NearByRestaurantModel nearByRestaurantModel = new NearByRestaurantModel(db);
        actualRestaurantList = nearByRestaurantModel.getNearByRestaurant("600020");

        assertNotEquals(expectedRestaurantList,actualRestaurantList,"Nearby Restaurants Lists match");
    }

    @Test
    void getCurrentNearByRestaurant_True() {
        List<Restaurant> actualRestaurantList;
        List<Restaurant> expectedRestaurantList;
        List<Restaurant> restaurantList = new ArrayList<>();
        restaurantList.add(restaurant1);
        restaurantList.add(restaurant2);
        restaurantList.add(restaurant3);
        expectedRestaurantList = restaurantList;
        SQLDatabase db = new SQLDatabase();
        NearByRestaurantModel nearByRestaurantModel = new NearByRestaurantModel(db);
        actualRestaurantList = nearByRestaurantModel.getNearByRestaurant("600020");
        List<Restaurant> restList = nearByRestaurantModel.getCurrentNearByRestaurant();
        assertEquals(expectedRestaurantList,restList,"Current Restaurant Lists doesnot match");
    }

    @Test
    void getCurrentNearByRestaurant_False() {
        List<Restaurant> actualRestaurantList;
        List<Restaurant> expectedRestaurantList;
        List<Restaurant> restaurantList = new ArrayList<>();
        restaurantList.add(restaurant1);
        restaurantList.add(restaurant2);
        expectedRestaurantList = restaurantList;
        SQLDatabase db = new SQLDatabase();
        NearByRestaurantModel nearByRestaurantModel = new NearByRestaurantModel(db);
        actualRestaurantList = nearByRestaurantModel.getNearByRestaurant("600020");
        List<Restaurant> restList = nearByRestaurantModel.getCurrentNearByRestaurant();
        assertNotEquals(expectedRestaurantList,restList,"Current Restaurant Lists match");
    }

    @Test
    void getPincode_True() {
        List<Restaurant> restaurantList = new ArrayList<>();
        restaurantList.add(restaurant1);
        restaurantList.add(restaurant2);
        restaurantList.add(restaurant3);
        SQLDatabase db = new SQLDatabase();
        NearByRestaurantModel nearByRestaurantModel = new NearByRestaurantModel(db);
        nearByRestaurantModel.getNearByRestaurant("600020");
        String actualPincode =nearByRestaurantModel.getPincode();
        String expectedPincode = "600020";

        assertEquals(expectedPincode,actualPincode,"Pincodes doesnot match");
    }

    @Test
    void getPincode_False() {
        List<Restaurant> restaurantList = new ArrayList<>();
        restaurantList.add(restaurant1);
        restaurantList.add(restaurant2);
        restaurantList.add(restaurant3);
        SQLDatabase db = new SQLDatabase();
        NearByRestaurantModel nearByRestaurantModel = new NearByRestaurantModel(db);
        nearByRestaurantModel.getNearByRestaurant("600020");
        String actualPincode =nearByRestaurantModel.getPincode();
        String expectedPincode = "600021";

        assertNotEquals(expectedPincode,actualPincode,"Pincodes match");
    }
}