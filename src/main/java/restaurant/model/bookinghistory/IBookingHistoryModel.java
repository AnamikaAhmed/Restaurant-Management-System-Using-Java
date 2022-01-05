package restaurant.model.bookinghistory;

import java.sql.ResultSet;

/**
 * Group 1
 *
 * @author: Anamika Ahmed
 * @description: Interface of booking history
 */
public interface IBookingHistoryModel {

  ResultSet getAllBookings(String userID, int restaurant);
}
