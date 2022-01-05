package restaurant.view.userrating;

import java.util.InputMismatchException;
import java.util.Scanner;


/**
 * Group 1
 * @author: Dinesh Kumar Baalajee Jothi
 * @description: View class for restaurant rating
 */

public class UserRatingView implements IUserRatingView {

    /*
      * View method to display the ratings of the customer experience to select one
      * @params: none
      * @return: int: rating of the customer experience
      */

    public int displayRatings() {

        System.out.println("\n Please enter your experience:" +
                "\n 1.Very Bad" +
                "\n 2.Bad" +
                "\n 3.Okay" +
                "\n 4.Good" +
                "\n 5.Excellent");
        int userRating = 0;
        try {
            Scanner scanner = new Scanner(System.in);
            userRating = scanner.nextInt();
        } catch (InputMismatchException exception) {
            System.out.println("Wrong input");
        }
        return userRating;
    }
}