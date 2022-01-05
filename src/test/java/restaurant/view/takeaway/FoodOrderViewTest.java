package restaurant.view.takeaway;

import restaurant.controller.takeaway.FoodOrder;
import restaurant.controller.takeaway.IFoodOrder;
import restaurant.model.fooddelivery.FoodChargesModel;
import restaurant.model.fooddelivery.IFoodChargesModel;
import restaurant.model.utilities.IDatabase;
import org.junit.jupiter.api.Test;
import restaurant.model.utilities.SQLDatabase;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class FoodOrderViewTest {
    IFoodOrder order;
    IFoodOrderView orderView;
    IFoodChargesModel chargesModel;
    IDatabase database;

    public FoodOrderViewTest() {
        database = new SQLDatabase();
        database.connect("db-5308.cs.dal.ca:3306", "CSCI5308_1_TEST", "CSCI5308_1_TEST_USER", "Uu4mw8bk3qR");
    }

    @Test
    public void displayOrderStatusTest() {
        order = new FoodOrder();
        orderView = new FoodOrderView();

        Map<Integer, Map<String, Object>> orderStatus = new HashMap<>();
        Map<String, Object> orderStatusItem = new HashMap<>();
        orderStatusItem.put("OrderID", 1);
        orderStatusItem.put("Status", "Pending");
        orderStatus.put(1, orderStatusItem);
        order.setOrderStatus(orderStatus);

        orderView.displayOrderStatus(order);
        assertEquals(1, order.getOrderStatus().get(1).get("OrderID"),
                "Value does not match the Order ID");
        assertEquals("Pending", order.getOrderStatus().get(1).get("Status"),
                "Value does not match the Status");
    }

    @Test
    public void displayOrderTest() {
        order = new FoodOrder();
        orderView = new FoodOrderView();
        assertFalse(orderView.displayOrder(order), "Order list is not empty");

        Map<String, Object> item = new HashMap<>();
        item.put("FoodID", 1);
        item.put("Name", "Pizza");
        item.put("Price", (float) 2.00);
        item.put("Quantity", 2);
        order.addItem(item, orderView);

        assertTrue(orderView.displayOrder(order), "Order list is empty");
    }

    @Test
    public void displayChargesTest() {
        order = new FoodOrder();
        orderView = new FoodOrderView();
        chargesModel = new FoodChargesModel();
        orderView.displayCharges(1, 1, 0, chargesModel, order, database);

        Map<String, Object> items = new HashMap<>();
        items.put("Tax", (float) 5.0);
        items.put("Delivery", (float) 2);
        items.put("Takeaway", (float) 1.5);

        for (Map.Entry<Integer, Map<String, Object>> entry : chargesModel.getTable().entrySet()) {
            assertEquals(entry.getValue().get("Tax"), items.get("Tax"), "Food ID does not match");
            assertEquals(entry.getValue().get("Delivery"), items.get("Delivery"), "Name does not match");
            assertEquals(entry.getValue().get("Takeaway"), items.get("Takeaway"), "Price does not match");
        }
    }

    @Test
    public void readFromConsoleTest() {
        orderView = new FoodOrderView();
        InputStream backup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream("1".getBytes());
        System.setIn(in);
        assertEquals("1", orderView.readFromConsole(""), "Input does not match");
        System.setIn(backup);
    }
}
