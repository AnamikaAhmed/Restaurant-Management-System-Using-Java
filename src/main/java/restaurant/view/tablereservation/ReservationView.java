package restaurant.view.tablereservation;

import restaurant.controller.bookinghistory.BookingHistoryController;
import restaurant.controller.tablereservation.TableReservationController;

import java.sql.SQLException;
import java.util.Scanner;

/**
 * Group 1
 *
 * @author: Anamika Ahmed
 * @description: Class that contains view details for table reservation
 */
public class ReservationView implements IReservationView {

  private String getDate = "";
  private int serviceNumber;
  private String chosen_time = "";
  private String chosen_table = "";
  private String reservationID = "";
  String user_id;

  @Override
  public void allOptions(String emailID, int restaurant) throws SQLException {
    boolean exit = false;
    do {
      user_id = emailID;
      System.out.println("===Reservations===");
      System.out.println("Please enter the suitable service number");
      System.out.println("1: View Reservation ");
      System.out.println("2: Make New Reservation ");
      System.out.println("3: Cancel Reservation");
      System.out.println("4: Go Back to Previous Menu");
      Scanner sc = new Scanner(System.in);
      serviceNumber = sc.nextInt();
      if (serviceNumber == 2) {
        checkDate(emailID, restaurant);
      } else if (serviceNumber == 4) {
        exit = true;//allOptions(emailID,restaurant);
      } else if (serviceNumber == 1) {
        BookingHistoryController bh = new BookingHistoryController();
        bh.getHistory(emailID, restaurant);
      } else if (serviceNumber == 3) {
        cancelReservation(emailID, restaurant);
      } else {
        System.out.println("Please enter a valid service number");
      }
    } while (!exit);
  }

  @Override
  public String checkDate(String emailID, int restaurant) throws SQLException {
    System.out.println("Please enter the date you want to select in " +
        "the format YYYY-MM-DD");
    Scanner sc = new Scanner(System.in);
    String chosenDate = sc.next();
    getDate = chosenDate;
    TableReservationController controller = new TableReservationController(restaurant);
    controller.showAvailability(getDate);
    // this matrix gets a matrix from the controller
    // pass the date to show availability of controller
    int[][] matrix = controller.showAvailability(getDate);
    // The matrix is printed
    System.out.println("Slot1(09:00-12:00) | \tSlot2" +
        "(12:00-15:00) | " +
        "\tSlot3" +
        "(15:00-18:00) | \tSlot4(18:00-23:00)");
    System.out.println(
        "------------------------------------------------------------------------------");

    int i = 0;
    for (int[] row : matrix) {
      if (i == 0) {
        i++;
        continue;
      }
      //System.out.print("A");
      // Loop through all columns of current row
      for (int x : row) {
        if (x == 0) {
          System.out.print("Available" + "             ");
        } else if (x == 1) {
          System.out.print("Unavailable" + "             ");

        }
      }
      System.out.println();
    }
    checkTime(emailID, restaurant);

    return getDate;
  }

  @Override
  public void checkTable(String emailID, int restaurant) throws SQLException {
    System.out.println("Please enter the table number you want to book");
    System.out.println("A:Table A, B:Table B, C:Table C, D: Table D");
    Scanner sc = new Scanner(System.in);
    chosen_table = sc.next();

    TableReservationController controller = new TableReservationController(restaurant);

    controller.reserve(user_id, getDate, chosen_time, chosen_table);
    System.out.println("Your reservation is for Table " + chosen_table +
        " on " + getDate + " at Slot " + chosen_time + " (18:00-22:00)");
    System.out.println("Confirm 1: Yes or 2:No");
    Scanner sc1 = new Scanner(System.in);
    int confirmCode = sc.nextInt();
    if (confirmCode == 1) {
      confirmMessage(emailID, restaurant);
    }

  }


  @Override
  public String checkTime(String emailID, int restaurant) throws SQLException {
    System.out.println("Please enter the slot number in which you want to" +
        " book");
    System.out.println("Slot 1: 09:00-12:00");
    System.out.println("Slot 2: 12:00-15:00");
    System.out.println("Slot 3: 15:00-18:00");
    System.out.println("Slot 4: 18:00-22:00");
    Scanner sc = new Scanner(System.in);
    chosen_time = sc.next();
    checkTable(emailID, restaurant);
    return chosen_time;
  }

  @Override
  public void confirmMessage(String emailID, int restaurant) throws SQLException {
    System.out.println("Congratulations!! Your reservation is confirmed.");
    TableReservationController controller = new TableReservationController(restaurant);
    // this matrix gets a matrix from the controller
    int reservationID = controller.getControllerID(getDate, chosen_time,
        chosen_table);
    System.out.println("Your reservation ID is " + reservationID + " Thank " +
        "you for booking with us!");

    allOptions(emailID, restaurant);
  }

  public void cancelReservation(String emailID, int restaurant) throws SQLException {
    System.out.println("Here is your list of reservations from which " +
        "you can cancel");
    System.out.println("---------------------------------------------");
    BookingHistoryController bh = new BookingHistoryController();
    bh.getHistory(emailID, restaurant);

    System.out.println("Enter the reservation code that you want to " +
        "cancel");
    Scanner sc = new Scanner(System.in);
    String code = sc.next();
    TableReservationController tb = new TableReservationController(restaurant);
    tb.cancelReservation(code);
    System.out.println("Your reservation has been successfully cancelled");
    System.out.println("----------------------------------------------");
    System.out.println("Do you wish to get back to reservation page?");
    System.out.println("1: Yes     2: No");
    Scanner sc1 = new Scanner(System.in);
    int x = sc1.nextInt();
    if (x == 1) {
      allOptions(emailID, restaurant);
    } else if (x == 2) {
      System.out.println("Thank you!");
    }
  }
}
