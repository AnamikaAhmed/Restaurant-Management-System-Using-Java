package restaurant.model.filterrestaurants;

import org.junit.jupiter.api.Test;
import restaurant.controller.Restaurant;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FilterRestaurantsModelTest {

    Restaurant restaurant1 = new Restaurant(2,"Curry Village Restaurant","1569 Dresden Row, Halifax, Nova Scotia B3J 2K4 Canada",600020,4);
    Restaurant restaurant2 = new Restaurant(5,"Eliot & Vine","2305 Clifton St Corner of Cunard & Clifton, Halifax, Nova Scotia B3K 4T9 Canada",600020,5);
    Restaurant restaurant3 = new Restaurant(6,"Fredies Fantastic Fishhouse","8 Oland Cres, Halifax, Nova Scotia B3S 1C6 Canada",600020,4);

    @Test
    void getFilteredRestaurants_True() {
        List<Restaurant> actualRestaurantList;
        List<Restaurant> expectedRestaurantList;
        List<Restaurant> restaurantList = new ArrayList<>();
        restaurantList.add(restaurant2);
        expectedRestaurantList = restaurantList;
        FilterRestaurantsModel filterRestaurantsModel = new FilterRestaurantsModel();
        filterRestaurantsModel.setRestaurantList(restaurantList);
        actualRestaurantList = filterRestaurantsModel.getFilteredRestaurants("5");
        assertEquals(expectedRestaurantList,actualRestaurantList,"Filter Restaurants doesnot match");

    }

    @Test
    void getFilteredRestaurants_False() {
        List<Restaurant> actualRestaurantList;
        List<Restaurant> expectedRestaurantList;
        List<Restaurant> restaurantList = new ArrayList<>();
        restaurantList.add(restaurant1);
        restaurantList.add(restaurant2);
        restaurantList.add(restaurant3);
        expectedRestaurantList = restaurantList;
        FilterRestaurantsModel filterRestaurantsModel = new FilterRestaurantsModel();
        filterRestaurantsModel.setRestaurantList(restaurantList);
        actualRestaurantList = filterRestaurantsModel.getFilteredRestaurants("5");
        assertNotEquals(expectedRestaurantList,actualRestaurantList,"Filter Restaurants match");

    }

    @Test
    void getCurrentFilteredRest_True() {
        List<Restaurant> actualRestaurantList;
        List<Restaurant> expectedRestaurantList;
        List<Restaurant> restaurantList = new ArrayList<>();
        restaurantList.add(restaurant2);
        expectedRestaurantList = restaurantList;
        FilterRestaurantsModel filterRestaurantsModel = new FilterRestaurantsModel();
        filterRestaurantsModel.setRestaurantList(restaurantList);
        filterRestaurantsModel.getFilteredRestaurants("5");
        actualRestaurantList = filterRestaurantsModel.getCurrentFilteredRest();
        assertEquals(expectedRestaurantList,actualRestaurantList,"Filter Restaurants doesnot match");
    }

    @Test
    void getCurrentFilteredRest_False() {
        List<Restaurant> actualRestaurantList;
        List<Restaurant> expectedRestaurantList;
        List<Restaurant> restaurantList = new ArrayList<>();
        restaurantList.add(restaurant2);
        restaurantList.add(restaurant3);
        expectedRestaurantList = restaurantList;
        FilterRestaurantsModel filterRestaurantsModel = new FilterRestaurantsModel();
        filterRestaurantsModel.setRestaurantList(restaurantList);
        filterRestaurantsModel.getFilteredRestaurants("5");
        actualRestaurantList = filterRestaurantsModel.getCurrentFilteredRest();
        assertNotEquals(expectedRestaurantList,actualRestaurantList,"Filter Restaurants match");
    }

    @Test
    void setRestaurantList_True() {
        List<Restaurant> actualRestaurantList;
        List<Restaurant> expectedRestaurantList;
        List<Restaurant> restaurantList = new ArrayList<>();
        restaurantList.add(restaurant1);
        restaurantList.add(restaurant2);
        restaurantList.add(restaurant3);
        expectedRestaurantList = restaurantList;
        FilterRestaurantsModel filterRestaurantsModel = new FilterRestaurantsModel();
        filterRestaurantsModel.setRestaurantList(restaurantList);
        actualRestaurantList = filterRestaurantsModel.getRestaurantList();
        assertEquals(expectedRestaurantList,actualRestaurantList,"Filter Restaurants doesnot match");
    }

    @Test
    void getRestaurants_True() {
        List<Restaurant> actualRestaurantList;
        List<Restaurant> expectedRestaurantList;
        List<Restaurant> restaurantList = new ArrayList<>();
        restaurantList.add(restaurant2);
        expectedRestaurantList = restaurantList;
        FilterRestaurantsModel filterRestaurantsModel = new FilterRestaurantsModel();
        filterRestaurantsModel.setRestaurantList(restaurantList);
        actualRestaurantList = filterRestaurantsModel.getRestaurantList();
        assertEquals(expectedRestaurantList,actualRestaurantList,"Filter Restaurants doesnot match");

    }
}
