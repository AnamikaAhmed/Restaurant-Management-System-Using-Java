package restaurant.model.takeaway;

import restaurant.controller.payment.IPay;
import restaurant.controller.payment.Pay;
import restaurant.controller.takeaway.FoodOrder;
import restaurant.controller.takeaway.IFoodOrder;
import restaurant.controller.utilities.ColumnType;
import restaurant.model.fooddelivery.FoodChargesModel;
import restaurant.model.fooddelivery.IFoodChargesModel;
import restaurant.model.utilities.IDatabase;
import restaurant.model.utilities.SQLDatabase;
import restaurant.view.takeaway.FoodOrderView;
import restaurant.view.takeaway.IFoodOrderView;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class FoodOrderModelTest {
    IFoodOrder order;
    IFoodOrderModel orderModel;
    IFoodOrderView orderView;
    IFoodChargesModel chargesModel;
    IDatabase database;
    IPay pay;

    public FoodOrderModelTest() {
        database = new SQLDatabase();
        database.connect("db-5308.cs.dal.ca:3306", "CSCI5308_1_TEST", "CSCI5308_1_TEST_USER", "Uu4mw8bk3qR");
    }

    @Test
    public void submitOrderTest() {
        order = new FoodOrder();
        orderModel = new FoodOrderModel();
        orderView = new FoodOrderView();
        chargesModel = new FoodChargesModel();
        pay = new Pay();

        // Flush the Food_Order and Food_Order_History tables
        database.insert("delete from food_order");
        database.insert("delete from food_order_history");

        Map<String, Object> foodItem = new HashMap<>();
        foodItem.put("FoodID", 5);
        foodItem.put("Quantity", 1);
        foodItem.put("Price", 4);
        order.addItem(foodItem, orderView);

        chargesModel.getCharges(1, database);

        InputStream backup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream("4".getBytes());
        System.setIn(in);

        orderModel.submitOrder(1, 1, "1", 0, 0, chargesModel, order, database, pay);

        System.setIn(backup);

        Map<String, ColumnType> tableStructure = new HashMap<>();
        tableStructure.put("Status", ColumnType.String);
        tableStructure.put("Tax", ColumnType.Float);
        tableStructure.put("Discount", ColumnType.Float);
        tableStructure.put("DeliveryCharge", ColumnType.Float);
        tableStructure.put("TakeAwayCharge", ColumnType.Float);
        tableStructure.put("Eligibility", ColumnType.Integer);
        tableStructure.put("UserID", ColumnType.String);
        tableStructure.put("RestaurantID", ColumnType.Integer);

        Map<Integer, Map<String, Object>> table = new HashMap<>();
        Map<String, Object> row = new HashMap<>();
        row.put("Status", "Pending");
        row.put("Tax", (float) 5.0);
        row.put("Discount", (float) 0);
        row.put("DeliveryCharge", (float) 0);
        row.put("TakeAwayCharge", (float) 1.5);
        row.put("Eligibility", 1);
        row.put("UserID", "1");
        row.put("RestaurantID", 1);
        table.put(1, row);

        Map<Integer, Map<String, Object>> tableFromDB = database.retrieve("select * from food_order_history",
                tableStructure);
        for (Map.Entry<Integer, Map<String, Object>> entry : tableFromDB.entrySet()) {
            assertEquals(entry.getValue().get("Status"), table.get(entry.getKey()).get("Status"),
                    "Status does not match");
            assertEquals(entry.getValue().get("Tax"), table.get(entry.getKey()).get("Tax"),
                    "Tax does not match");
            assertEquals(entry.getValue().get("Discount"), table.get(entry.getKey()).get("Discount"),
                    "Discount does not match");
            assertEquals(entry.getValue().get("DeliveryCharge"), table.get(entry.getKey()).get("DeliveryCharge"),
                    "Delivery Charge does not match");
            assertEquals(entry.getValue().get("TakeAwayCharge"), table.get(entry.getKey()).get("TakeAwayCharge"),
                    "Take Away Charge does not match");
            assertEquals(entry.getValue().get("Eligibility"), table.get(entry.getKey()).get("Eligibility"),
                    "Eligibility does not match");
            assertEquals(entry.getValue().get("UserID"), table.get(entry.getKey()).get("UserID"),
                    "UserID does not match");
            assertEquals(entry.getValue().get("RestaurantID"), table.get(entry.getKey()).get("RestaurantID"),
                    "RestaurantID does not match");
        }

        tableStructure = new HashMap<>();
        tableStructure.put("FoodID", ColumnType.Integer);
        tableStructure.put("Quantity", ColumnType.Integer);
        tableStructure.put("Price", ColumnType.Float);
        for (Map.Entry<Integer, Map<String, Object>> entry :
                database.retrieve("select * from food_order where OrderID=" + table.get(1).get("OrderID"),
                        tableStructure).entrySet()) {
            assertEquals(entry.getValue().get("FoodID"), foodItem.get("FoodID"), "Food ID does not match");
            assertEquals(entry.getValue().get("Quantity"), foodItem.get("Quantity"),
                    "Quantity ID does not match");
            assertEquals(entry.getValue().get("Price"), foodItem.get("Price"), "Price does not match");
        }
    }

    @Test
    public void getOrderStatusTest() {
        order = new FoodOrder();
        int orderID = order.getOrderID();
        assertEquals(-1, orderID, "Value does not match when no order is available");

        submitOrderTest();
        orderID = order.getOrderID();
        orderModel.getOrderStatus(order, database);
        assertEquals(orderID, order.getOrderStatus().get(1).get("OrderID"),
                "Value does not match when order is available");
    }
}
