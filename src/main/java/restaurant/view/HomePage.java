package restaurant.view;

import restaurant.controller.RestaurantService;
import restaurant.controller.orderhistory.IOrderHistory;
import restaurant.controller.orderhistory.OrderHistory;
import restaurant.controller.payment.IPay;
import restaurant.controller.payment.Pay;
import user.view.Headings;
import user.view.InputViewOperations;
import user.view.InvalidInputException;

import java.sql.SQLException;

public class HomePage {
  public void homePageRestaurant(String emailID) throws InvalidInputException, SQLException {
    boolean exit = false;
    do {
    Headings headings = new Headings();
    headings.homePage();
    System.out.println("Select the corresponding option to enter the desired page: ");
    System.out.println("1: Find restaurants");
    System.out.println("2: Add Payment Method");
    System.out.println("3: Order History");
    System.out.println("4: Exit");
    InputViewOperations ivo = new InputViewOperations();
    String option = ivo.readFromConsole();

      switch (option) {
        case "1": {
          RestaurantService rs = new RestaurantService();
          rs.service(emailID);
        }
        break;
        case "2": {
          IPay pay = new Pay();
          pay.addPayment();
        }
        break;
        case "3": {
          IOrderHistory oh = new OrderHistory();
          oh.historyMenu(emailID);
        }
        break;
        case "4":
          exit = true;
          break;
        default:
          throw new InvalidInputException("Invalid Input: Please select the options from above only!");

      }
    } while (!exit);
  }

//  public void restaurantPage() throws InvalidInputException {
//    Headings headings = new Headings();
//    headings.orderPage();
//    System.out.println("Select the corresponding option to enter the desired page: ");
//    System.out.println("1: Order Online");
//    System.out.println("2: Reserve Table");
//    InputViewOperations ivo2 = new InputViewOperations();
//    String option = ivo2.readFromConsole();
//    switch (option){
//      case "1": {
//      }
//      break;
//      case "2":
//        break;
//      default: throw new InvalidInputException("Invalid Input: Please select the options from above only!");
//
//    }
//  }
}
