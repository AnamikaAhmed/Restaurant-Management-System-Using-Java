package restaurant.controller.bookinghistory;

import java.util.ArrayList;
import java.util.Map;

/**
 * Group 1
 *
 * @author: Anamika Ahmed
 * @description: Class that contains model details of table reservation
 */
public interface IBookingHistoryController {

  Map<String, ArrayList<String>> getHistory(String emailID,
      int restaurant);
}
