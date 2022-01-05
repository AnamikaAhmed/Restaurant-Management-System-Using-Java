package restaurant.view.tablereservation;

import java.sql.SQLException;

/**
 * Group 1
 *
 * @author: Anamika Ahmed
 * @description: Interface for reservation view
 */
public interface IReservationView {

  void allOptions(String emailID, int restaurant) throws SQLException;

  String checkDate(String emailID, int restaurant) throws SQLException;

  void confirmMessage(String emailID, int restaurant) throws SQLException;

  void checkTable(String emailID, int restaurant) throws SQLException;

  String checkTime(String emailID, int restaurant) throws SQLException;

  void cancelReservation(String emailID, int restaurant) throws SQLException;
}
