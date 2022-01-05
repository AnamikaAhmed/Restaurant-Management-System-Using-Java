package restaurant.controller.bookinghistory;

import com.mysql.cj.protocol.Resultset;
import restaurant.model.bookinghistory.BookingHistoryModel;
import restaurant.model.bookinghistory.IBookingHistoryModel;
import restaurant.model.tablereservation.Database;
import restaurant.model.tablereservation.IDatabase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Group 1
 *
 * @author: Anamika Ahmed
 * @description: Class that contains controller for booking history
 */
public class BookingHistoryController {

  private Map<String, ArrayList<String>> map = new HashMap<>();
  private int booking_time = 0;
  private String booking_date = "";
  private String reserveCode = "";
  private String table_number = "";

  public BookingHistoryController() {
  }

  public Map<String, ArrayList<String>> getHistory(String emailID,
      int restaurant) {
    BookingHistoryModel modelBooking = new BookingHistoryModel();
    ResultSet rs = modelBooking.getAllBookings(emailID, restaurant);

    try {
      while (rs.next()) {
        ArrayList<String> list = new ArrayList<>();
        booking_date = rs.getString("booking_date");
        list.add(booking_date);
        booking_time = rs.getInt("booking_time");
        list.add(booking_time + "");
        table_number = rs.getString("table_number");
        list.add(table_number);
        reserveCode = rs.getString("reservationID");
        map.put(reserveCode, list);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return map;
  }
}
