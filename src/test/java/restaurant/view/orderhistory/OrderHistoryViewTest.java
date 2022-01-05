package restaurant.view.orderhistory;

import org.junit.jupiter.api.Test;
import restaurant.controller.orderhistory.IOrderHistory;
import restaurant.controller.orderhistory.OrderHistory;
import restaurant.model.orderhistory.IOrderHistoryModel;
import restaurant.model.orderhistory.OrderHistoryModel;
import restaurant.model.utilities.IDatabase;
import restaurant.model.utilities.SQLDatabase;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderHistoryViewTest {
    IDatabase database;
    IOrderHistory history;
    IOrderHistoryModel model;
    IOrderHistoryView view;

    public OrderHistoryViewTest() {
        history = new OrderHistory();
        model = new OrderHistoryModel();
        view = new OrderHistoryView();
        database = new SQLDatabase();
        database.connect("db-5308.cs.dal.ca:3306", "CSCI5308_1_TEST", "CSCI5308_1_TEST_USER", "Uu4mw8bk3qR");
    }

    @Test
    public void displayOrdersTest() {
        model.getHistory("1", history, view, database);
        view.displayOrders(history);

        Map<Integer, Map<String, Object>> table = history.getTable();

        assertEquals("Pending", table.get(1).get("Status"), "Status does not match");
        assertEquals((float) 5.0, table.get(1).get("Tax"), "Tax does not match");
        assertEquals((float) 0, table.get(1).get("Discount"), "Discount does not match");
        assertEquals((float) 0, table.get(1).get("DeliveryCharge"), "Delivery Charge does not match");
        assertEquals((float) 1.5, table.get(1).get("TakeAwayCharge"), "Take Away Charge does not match");
        assertEquals(1, table.get(1).get("Eligibility"), "Eligibility does not match");
    }

    @Test
    public void displayChargesTest() {
        model.getHistory("1", history, view, database);
        view.displayOrders(history);

        Map<Integer, Map<String, Object>> table = history.getTable();
        view.displayCharges(history, 1);

        assertEquals((float) 14.2, table.get(1).get("Price"), "Price does not match");
    }

    @Test
    public void readFromConsoleTest() {
        InputStream backup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream("1".getBytes());
        System.setIn(in);
        assertEquals("1", view.readFromConsole("Sample Text"), "Input does not match");
        System.setIn(backup);
    }
}
