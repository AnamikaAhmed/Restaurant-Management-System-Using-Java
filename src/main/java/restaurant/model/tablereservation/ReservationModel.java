package restaurant.model.tablereservation;

import java.sql.ResultSet;

/**
 * Group 1
 *
 * @author: Anamika Ahmed
 * @description: Class that contains model details of table reservation
 */
public class ReservationModel {

  IDatabase database;

  public ReservationModel() {
    database = new Database();
    database.connect(System.getenv("HOST"), System.getenv("DATABASE"),
        System.getenv("USER_NAME"), System.getenv("PASSWORD"));
  }

  public ResultSet getAllByDates(String date, int restaurant) {
    //db.connect("localhost:3306","restaurant1","root","ana1995");
    ResultSet rs = database.retrieve("Select * from reservation3 where " +
        "booking_date='" + date + "' and restaurantID=" + restaurant + ";");
    return rs;
  }

  public ResultSet eligibility(String date, String time,
      String tableNumber) {
    ResultSet rs = database.retrieve("SELECT * from reservation3 where " +
        "booking_date = '" + date + "' and booking_time=" + time + " and " +
        "table_number='" + tableNumber + "';");
    return rs;
  }

  public void modelReserve(String user_id, String date, String time,
      String tableNumber, int restaurantID) {
    database.update("INSERT INTO reservation3 (user_id," +
        "booking_date," +
        "booking_time,table_number,restaurantID)" +
        "VALUES ('" + user_id + "', '" + date + "'," + time + ",'" + tableNumber + "'," + restaurantID + ");"
    );

  }

  public ResultSet confirm(String date, String time,
      String tableNumber, int restaurant) {

    System.out.println("The value of date is " + date);
    ResultSet rs = database.retrieve("select * from reservation3 where " +
        "booking_date" +
        "='" + date + "' and booking_time" + "='" + time + "' and table_number" + " ='" + tableNumber + "' and restaurantID=" + restaurant + ";");
    return rs;
  }

  public void delete(String code) {

    database.update("delete from reservation3 where " +
        "reservationID=" + code + ";");
  }
}
