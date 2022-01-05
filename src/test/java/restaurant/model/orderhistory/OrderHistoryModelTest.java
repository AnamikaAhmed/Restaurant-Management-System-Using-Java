package restaurant.model.orderhistory;

import org.junit.After;
import org.junit.FixMethodOrder;
import restaurant.controller.orderhistory.IOrderHistory;
import restaurant.controller.orderhistory.OrderHistory;
import restaurant.model.utilities.IDatabase;
import restaurant.model.utilities.SQLDatabase;
import org.junit.jupiter.api.Test;
import restaurant.view.orderhistory.IOrderHistoryView;
import restaurant.view.orderhistory.OrderHistoryView;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@FixMethodOrder
public class OrderHistoryModelTest {
    IDatabase database;
    IOrderHistory history;
    IOrderHistoryModel model;
    IOrderHistoryView view;

    public OrderHistoryModelTest() {
        history = new OrderHistory();
        model = new OrderHistoryModel();
        view = new OrderHistoryView();
        database = new SQLDatabase();
        database.connect("db-5308.cs.dal.ca:3306", "CSCI5308_1_TEST", "CSCI5308_1_TEST_USER", "Uu4mw8bk3qR");
    }

    @Test
    public void getHistoryTest() {
        model.getHistory("1", history, view, database);

        Map<Integer, Map<String, Object>> table = history.getTable();

        assertEquals("Pending", table.get(1).get("Status"), "Status does not match");
        assertEquals((float) 5.0, table.get(1).get("Tax"), "Tax does not match");
        assertEquals((float) 0, table.get(1).get("Discount"), "Discount does not match");
        assertEquals((float) 0, table.get(1).get("DeliveryCharge"), "Delivery Charge does not match");
        assertEquals((float) 1.5, table.get(1).get("TakeAwayCharge"), "Take Away Charge does not match");
        assertEquals(1, table.get(1).get("Eligibility"), "Eligibility does not match");
    }

    @Test
    public void getOrderDetailsTest() {
        model.getHistory("1", history, view, database);
        model.getOrderDetails(history, database);

        Map<Integer, Map<String, Object>> order = history.getOrder();

        assertEquals(5, order.get(1).get("FoodID"), "Food ID does not match");
        assertEquals(1, order.get(1).get("Quantity"), "Quantity does not match");
        assertEquals((float) 4, order.get(1).get("Price"), "Price does not match");
    }

    @Test
    public void updateOrderTest() {
        model.getHistory("1", history, view, database);
        model.getOrderDetails(history, database);

        Map<Integer, Map<String, Object>> order = history.getOrder();
        int orderID = (int) order.get(1).get("OrderID");
        model.updateOrder(orderID, database);

        order = history.getTable();
        assertEquals("Cancelled", order.get(1).get("Status"), "Status does not match");
    }
}
