package restaurant.controller.tablereservation;

import org.junit.jupiter.api.Test;
import restaurant.model.tablereservation.Database;
import restaurant.model.utilities.IDatabase;

import static org.junit.jupiter.api.Assertions.*;

class TableReservationControllerTest {

  Database db;
  TableReservationController tb;

  public TableReservationControllerTest() {
    db = new Database();
    db.connect("db-5308.cs.dal.ca:3306", "CSCI5308_1_TEST",
        "CSCI5308_1_TEST_USER", "Uu4mw8bk3qR");
    tb = new TableReservationController(1234);
  }

  @Test
  void reserve() {
    assertEquals(true, tb.reserve("Anamika", "2021-09-09", "4", "A"));
    assertEquals(true, tb.reserve("Anik", "2021-09-10", "4", "B"));


  }

  @Test
  void cancelReservation() {
    assertEquals(true, tb.cancelReservation("1234"));
    assertEquals(true, tb.cancelReservation("2345"));

  }

  @Test
  void getControllerID() {
    assertEquals(1, tb.getControllerID("2021-09-09", "3", "A"));
    assertEquals(1, tb.getControllerID("2021-10-09", "4", "A"));

  }
}