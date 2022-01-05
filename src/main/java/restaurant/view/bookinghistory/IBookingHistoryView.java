package restaurant.view.bookinghistory;

/**
 * Group 1
 *
 * @author: Anamika Ahmed
 * @description: Interface for Booking History
 */
public interface IBookingHistoryView {

  void viewHistory(String userID, int restaurant);

  void display(String text);

  String readFromConsole(String text);
}
