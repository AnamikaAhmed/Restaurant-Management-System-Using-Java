package restaurant.controller.takeaway;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import restaurant.model.utilities.IDatabase;
import restaurant.model.utilities.SQLDatabase;
import restaurant.view.takeaway.FoodOrderView;
import restaurant.view.takeaway.IFoodOrderView;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FoodOrderTest {
    IFoodOrder order;
    IFoodOrderView orderView;
    IDatabase database;

    public FoodOrderTest() {
        database = new SQLDatabase();
        database.connect("db-5308.cs.dal.ca:3306", "CSCI5308_1_TEST", "CSCI5308_1_TEST_USER", "Uu4mw8bk3qR");
    }

    @Test
    public void addItemTest() {
        order = new FoodOrder();
        orderView = new FoodOrderView();

        Map<String, Object> item = new HashMap<>();
        item.put("FoodID", 1);
        item.put("Name", "Pizza");
        item.put("Price", (float) 2.00);
        item.put("Quantity", 2);
        order.addItem(item, orderView);

        for (Map.Entry<Integer, Map<String, Object>> entry : order.getOrder().entrySet()) {
            assertEquals(entry.getValue().get("FoodID"), item.get("FoodID"), "Food ID does not match");
            assertEquals(entry.getValue().get("Name"), item.get("Name"), "Name does not match");
            assertEquals(entry.getValue().get("Price"), item.get("Price"), "Price does not match");
            assertEquals(entry.getValue().get("Quantity"), item.get("Quantity"), "Quantity does not match");
        }

        item = new HashMap<>();
        item.put("FoodID", 1);
        item.put("Name", "Pizza");
        item.put("Price", (float) 2.00);
        item.put("Quantity", 2);
        order.addItem(item, orderView);

        for (Map.Entry<Integer, Map<String, Object>> entry : order.getOrder().entrySet()) {
            assertEquals(entry.getValue().get("FoodID"), item.get("FoodID"), "Food ID does not match");
            assertEquals(entry.getValue().get("Name"), item.get("Name"), "Name does not match");
            assertEquals(entry.getValue().get("Price"), item.get("Price"), "Price does not match");
            assertEquals(entry.getValue().get("Quantity"), 4, "Quantity does not match");
        }
    }

    @Test
    public void addItemMultipleTest() {
        order = new FoodOrder();
        orderView = new FoodOrderView();

        Map<Integer, Map<String, Object>> items = new HashMap<>();
        Map<String, Object> item = new HashMap<>();
        item.put("FoodID", 1);
        item.put("Name", "Pizza");
        item.put("Price", (float) 2.00);
        item.put("Quantity", 2);
        order.addItem(item, orderView);
        items.put(1, item);

        item = new HashMap<>();
        item.put("FoodID", 3);
        item.put("Name", "Calzone");
        item.put("Price", (float) 1.50);
        item.put("Quantity", 1);
        order.addItem(item, orderView);
        items.put(2, item);

        for (Map.Entry<Integer, Map<String, Object>> entry : order.getOrder().entrySet()) {
            assertEquals(entry.getValue().get("FoodID"), items.get(entry.getKey()).get("FoodID"),
                    "Food ID does not match");
            assertEquals(entry.getValue().get("Name"), items.get(entry.getKey()).get("Name"),
                    "Name does not match");
            assertEquals(entry.getValue().get("Price"), items.get(entry.getKey()).get("Price"),
                    "Price does not match");
            assertEquals(entry.getValue().get("Quantity"), items.get(entry.getKey()).get("Quantity"),
                    "Quantity does not match");
        }
    }

    @Test
    public void removeItemTest() {
        order = new FoodOrder();
        orderView = new FoodOrderView();

        Map<Integer, Map<String, Object>> items = new HashMap<>();
        Map<String, Object> item = new HashMap<>();
        item.put("FoodID", 1);
        item.put("Name", "Pizza");
        item.put("Price", (float) 2.00);
        item.put("Quantity", 2);
        order.addItem(item, orderView);
        items.put(1, item);

        item = new HashMap<>();
        item.put("FoodID", 3);
        item.put("Name", "Calzone");
        item.put("Price", (float) 1.50);
        item.put("Quantity", 1);
        order.addItem(item, orderView);
        items.put(2, item);

        InputStream backup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream("2".getBytes());
        System.setIn(in);
        order.removeItem(orderView);
        System.setIn(backup);

        for (Map.Entry<Integer, Map<String, Object>> entry : order.getOrder().entrySet()) {
            assertEquals(entry.getValue().get("FoodID"), items.get(entry.getKey()).get("FoodID"),
                    "Food ID does not match");
            assertEquals(entry.getValue().get("Name"), items.get(entry.getKey()).get("Name"),
                    "Name does not match");
            assertEquals(entry.getValue().get("Price"), items.get(entry.getKey()).get("Price"),
                    "Price does not match");
            assertEquals(entry.getValue().get("Quantity"), items.get(entry.getKey()).get("Quantity"),
                    "Quantity does not match");
        }

        backup = System.in;
        in = new ByteArrayInputStream("1".getBytes());
        System.setIn(in);
        order.removeItem(orderView);
        System.setIn(backup);

        assertTrue(order.getOrder().isEmpty(), "Order list is not empty");

        order.removeItem(orderView);
        assertTrue(order.getOrder().isEmpty(), "Order list is not empty");
    }
}
