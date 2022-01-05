package restaurant.model.tablereservation;

import restaurant.controller.tablereservation.ITableReservationController;

import java.sql.ResultSet;

/**
 * Group 1
 *
 * @author: Anamika Ahmed
 * @description: Interface for Reservation Model
 */
public interface IReservationModel {

  public ResultSet getAllByDates(IDatabase db, String date);

  public boolean checkAvailability(IDatabase db,
      ITableReservationController reserveInfo);


}
