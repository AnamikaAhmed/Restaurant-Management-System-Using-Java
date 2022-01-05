package restaurant.model.bookinghistory;

import restaurant.model.utilities.IDatabase;
import restaurant.model.utilities.SQLDatabase;

import java.sql.ResultSet;

/**
 * Group 1
 *
 * @author: Anamika Ahmed
 * @description: Class that contains model details of table booking history
 */
public class BookingHistoryModel implements IBookingHistoryModel {

  IDatabase database;

  public BookingHistoryModel() {
    connectDatabase();
  }

  public ResultSet getAllBookings(String userID, int restaurant) {
    ResultSet rs = database.retrieve("Select * from reservation3 where " +
        "restaurantID=" + restaurant + " and " +
        "user_id='" + userID + "';");
    return rs;
  }

  public void connectDatabase() {
    database = new SQLDatabase();
    database.connect(System.getenv("HOST"), System.getenv("DATABASE"), System.getenv("USER_NAME"), System.getenv("PASSWORD"));
  }
}
