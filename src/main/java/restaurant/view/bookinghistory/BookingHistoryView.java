package restaurant.view.bookinghistory;

import restaurant.controller.bookinghistory.BookingHistoryController;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

/**
 * Group 1
 *
 * @author: Anamika Ahmed
 * @description: Class that contains model details of booking history
 */
public class BookingHistoryView implements IBookingHistoryView {

  private Map<String, ArrayList<String>> map;

  public BookingHistoryView() {
  }

  @Override
  public void viewHistory(String userID, int restaurant) {
    display("The booking history for user " + userID);
    display("------------------------------------");
    BookingHistoryController bookingController =
        new BookingHistoryController();

    map = bookingController.getHistory(userID, restaurant);
    for (Map.Entry<String, ArrayList<String>> entry : map.entrySet()) {
      String key = entry.getKey();
      ArrayList<String> value = entry.getValue();

      display("Reservation ID: " + key);
      display("Booking Date: " + value.get(0));
      display("Booking Time: Slot " + value.get(1));
      display("Table Number: " + value.get(2));
      display("====================================");
    }

  }

  @Override
  public void display(String text) {
    System.out.println(text);
  }

  @Override
  public String readFromConsole(String text) {
    System.out.print(text);
    Scanner scanner = new Scanner(System.in);
    return scanner.nextLine();

  }

}
