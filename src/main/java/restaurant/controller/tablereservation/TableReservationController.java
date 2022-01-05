package restaurant.controller.tablereservation;
import restaurant.model.tablereservation.ReservationModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Group 1
 *
 * @author: Anamika Ahmed
 * @description: Controller class for table reservation
 */
public class TableReservationController implements ITableReservationController {

  private int restaurantID;

  public TableReservationController(int restaurantID) {
    this.restaurantID = restaurantID;
  }

  Map<Integer, ArrayList<String>> hmap = new HashMap<>();

  // this will display all the tables available on that particular date
  public int[][] showAvailability(String booking_date) {

    //database is connected
    ReservationModel rev = new ReservationModel();

    // Query to retrieve the information on that particular date entered by user
    ResultSet result_set = rev.getAllByDates(booking_date, restaurantID);

    int bookingTime;
    String tableNumber = "";
    // key is the time and value is the table numbers

    try {

      while (result_set.next()) {

        bookingTime = result_set.getInt("booking_time");
        tableNumber = result_set.getString("table_number");

        // if the map does not contain the key
        if (hmap.containsKey(bookingTime) == false) {
          // create a new arraylist that stores all the tables
          ArrayList<String> allTables = new ArrayList<>();
          allTables.add(tableNumber);

          hmap.put(bookingTime, allTables);
        } else {
          ArrayList<String> temp = new ArrayList<>();

          // add that table in the arraylist
          temp = hmap.get(bookingTime);
          temp.add(tableNumber);
          // put the arraylist in the hashmap
          hmap.put(bookingTime, temp);
        }
      }
      System.out.println("--------------");

      Map<String, Integer> indexMap = new HashMap<>();
      List<String> alphabet = new ArrayList<>();
      alphabet.add("A");
      alphabet.add("B");
      alphabet.add("C");
      alphabet.add("D");

      for (int i = 0; i < 4; i++) {
        indexMap.put(alphabet.get(i), i);
      }

      System.out.println("--------------");
      int row = alphabet.size() + 1;
      int column = hmap.keySet().size() + 1;

      //2D Matrix
      int[][] reservationMatrix = new int[row][column];
      for (Map.Entry<Integer, ArrayList<String>> h : hmap.entrySet()) {

        for (String table : h.getValue()) {

          reservationMatrix[h.getKey()][indexMap.get(table)] = 1;
        }
      }
      // the matrix is returned in the view
      return reservationMatrix;
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    return null;
  }

  // this will check if the user is entering valid reservation information
  // returns false if the date, booking time and table number is already
  // unavailable
  public boolean eligibility(String date, String time, String table){
    try {
      ReservationModel rv = new ReservationModel();
      ResultSet rs = rv.eligibility(date, time, table);
      int sizeOfRows = 0;
      while (rs.next()) {
        sizeOfRows++;
      }
      if (sizeOfRows > 0) {
        return false;
      }
    }
    catch(SQLException e){
      e.printStackTrace();
    }
    return true;
  }

  // this will create new reservation
  public boolean reserve(String user_id, String date, String time, String table) {

    // object of model
    ReservationModel rev = new ReservationModel();
    // Query to retrieve the information on that particular date entered by user
    try {
      rev.modelReserve(user_id, date, time, table, restaurantID);
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  // this will cancel the reservation
  public boolean cancelReservation(String code) {
    ReservationModel rev = new ReservationModel();
    rev.delete(code);
    return true;
  }

  // this will fetch the reservation ID
  public int getControllerID(String date, String time, String tableNumber){
    try {

      ReservationModel rev = new ReservationModel();
      // Query to retrieve the information on that particular date entered by user
      ResultSet result_set = rev.confirm(date, time, tableNumber, restaurantID);
      int code = 0;
      while (result_set.next()) {
        code = result_set.getInt("reservationID");
      }
    }
    catch(SQLException e){
      e.printStackTrace();
    }
    return 0;
  }
}


