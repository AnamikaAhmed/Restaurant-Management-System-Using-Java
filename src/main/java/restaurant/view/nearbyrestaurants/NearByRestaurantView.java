package restaurant.view.nearbyrestaurants;

import restaurant.controller.Restaurant;
import java.util.List;
import java.util.Scanner;

/**
 * Group 1
 * @author: Pavan
 * @description: View class to display/read contents on/from the console
 */
public class NearByRestaurantView implements INearByRestaurantView {

    /*
     Reads the category from the console
     @params: none
     @return: String: value entered on the console
     */
    @Override
    public String readFromConsole() {
        System.out.println("Enter Pincode : ");
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    /*
     Displays all nearby restaurants on the console
     and asks for user input further
     @params: List of nearby restaurants
     @return: none
     */
    @Override
    public void printRestaurants(List<Restaurant> nearbyRestaurantList) {
        if(nearbyRestaurantList.isEmpty()){
            System.out.println("No restaurants found, try another pincode");
            return;
        }
        System.out.format("%1s%20s%60s%50s%14s","id","Restaurant","Address","Pincode","Rating\n");
        for (Restaurant restaurant : nearbyRestaurantList) {
            System.out.println(restaurant);
        }
        System.out.println("\nPlease enter the ID of restaurant to proceed further OR Press 'F' to filter restaurants based on ratings OR R for recommendations:");
    }
}
