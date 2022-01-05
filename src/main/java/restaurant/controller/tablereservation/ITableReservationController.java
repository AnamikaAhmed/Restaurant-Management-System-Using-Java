package restaurant.controller.tablereservation;

import java.sql.SQLException;

/**
 * Group 1
 *
 * @author: Anamika Ahmed
 * @description: Interface for table reservation controller
 */
public interface ITableReservationController {

  int[][] showAvailability(String booking_date);

  boolean eligibility(String date, String time,
      String table) throws SQLException;

  boolean reserve(String user_id, String date, String time, String table);

  boolean cancelReservation(String code);

  int getControllerID(String date, String time, String tableNumber) throws SQLException;

}
