package restaurant.model.checkrestaurant;

import org.junit.jupiter.api.Test;
import restaurant.controller.Restaurant;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CheckRestaurantModelTest {

    Restaurant restaurant1 = new Restaurant(2,"Curry Village Restaurant","1569 Dresden Row, Halifax, Nova Scotia B3J 2K4 Canada",600020,4);
    Restaurant restaurant2 = new Restaurant(5,"Eliot & Vine","2305 Clifton St Corner of Cunard & Clifton, Halifax, Nova Scotia B3K 4T9 Canada",600020,5);
    Restaurant restaurant3 = new Restaurant(6,"Fredies Fantastic Fishhouse","8 Oland Cres, Halifax, Nova Scotia B3S 1C6 Canada",600020,4);

    @Test
    void isRestaurantValid_True() {
        List<Restaurant> restaurantList = new ArrayList<>();
        restaurantList.add(restaurant1);
        restaurantList.add(restaurant2);
        restaurantList.add(restaurant3);
        CheckRestaurantModel checkRestaurantModel = new CheckRestaurantModel();
        checkRestaurantModel.setRestaurantList(restaurantList);
        boolean actualValue = checkRestaurantModel.isRestaurantValid("2");
        assertTrue(actualValue,"Restaurant ID is not valid");
    }

    @Test
    void isRestaurantValid_False() {
        List<Restaurant> restaurantList = new ArrayList<>();
        restaurantList.add(restaurant1);
        restaurantList.add(restaurant2);
        restaurantList.add(restaurant3);
        CheckRestaurantModel checkRestaurantModel = new CheckRestaurantModel();
        checkRestaurantModel.setRestaurantList(restaurantList);
        boolean actualValue = checkRestaurantModel.isRestaurantValid("7");
        assertFalse(actualValue,"Restaurant ID is valid");
    }
}