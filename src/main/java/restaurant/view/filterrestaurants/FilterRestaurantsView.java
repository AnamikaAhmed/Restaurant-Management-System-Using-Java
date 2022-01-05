package restaurant.view.filterrestaurants;

import restaurant.controller.Restaurant;
import java.util.List;
import java.util.Scanner;

/**
 * Group 1
 * @author: Pavan Abburi
 * @description: View class to display/read contents on/from the console
 */
public class FilterRestaurantsView implements IFilterRestaurantsView
{
    /*
     Reads the category from the console
     @params: none
     @return: String: value entered on the console
     */
    @Override
    public String readFromConsole() {
        System.out.println("Enter minimum rating of restaurant you want : ");
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    /*
     Displays all filtered restaurants on the console
     and asks for user input further
     @params: List of filtered restaurants
     @return: none
     */
    @Override
    public void printFilterRestaurants(List<Restaurant> filteredrestaurantList) {
        if(filteredrestaurantList.isEmpty()) {
            System.out.println("No restaurants found, try changing minimum number of ratings");
            return;
        }
        System.out.format("%1s%20s%60s%50s%14s","id","Restaurant","Address","Pincode","Rating\n");
        for (Restaurant restaurant : filteredrestaurantList) {
            System.out.println(restaurant);
        }
        System.out.println("Please enter the ID of restaurant to proceed further OR Press 'F' to filter restaurants based on ratings OR Press 'R' for recommendations:");
    }
}
