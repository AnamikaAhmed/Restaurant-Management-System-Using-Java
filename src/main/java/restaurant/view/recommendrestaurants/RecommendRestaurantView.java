package restaurant.view.recommendrestaurants;

import restaurant.controller.Restaurant;
import java.util.List;

/**
 * Group 1
 * @author: Pavan Abburi
 * @description: View class to display/read contents on/from the console
 */
public class RecommendRestaurantView implements IRecommendRestaurantView {
    /*
     Displays all recommended restaurants on the console
     and asks for user input further
     @params: List of recommendation restaurants
     @return: none
     */
    public void printRecommendations(List<Restaurant> recommendationList) {
        if(recommendationList.isEmpty()){
            System.out.println("No restaurants found, try another pincode");
            return;
        }
        System.out.println("Please find below recommendations for you:");
        System.out.format("%1s%20s%60s%50s%14s","id","Restaurant","Address","Pincode","Rating\n");
        for (Restaurant restaurant : recommendationList) {
            System.out.println(restaurant);
        }
        System.out.println("Please enter the ID of restaurant to proceed further OR Press 'F' to filter restaurants based on ratings OR Press 'R' for recommendations:");
    }
}

