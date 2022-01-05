package restaurant.controller.fooddelivery;

import restaurant.model.fooddelivery.FoodMenuModel;
import restaurant.model.fooddelivery.IFoodMenuModel;
import restaurant.model.utilities.IDatabase;
import restaurant.model.utilities.SQLDatabase;
import restaurant.view.fooddelivery.FoodMenuView;
import restaurant.view.fooddelivery.IFoodMenuView;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class FoodMenuTest {
    IFoodMenu menu;
    IFoodMenuModel menuModel;
    IFoodMenuView menuView;
    IDatabase database;

    public FoodMenuTest() {
        menu = new FoodMenu();
        menuModel = new FoodMenuModel();
        menuView = new FoodMenuView();
        database = new SQLDatabase();
        database.connect("db-5308.cs.dal.ca:3306", "CSCI5308_1_TEST", "CSCI5308_1_TEST_USER", "Uu4mw8bk3qR");
    }

    @Test
    public void searchMenuTest() {
        menuModel.getFoodMenu(1, database, menu);
        InputStream backup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream("ice".getBytes());
        System.setIn(in);
        menu.searchMenu(1, menu, database, menuView, menuModel);
        System.setIn(backup);

        Map<Integer, Map<String, Object>> table = new HashMap<>();
        Map<String, Object> row = new HashMap<>();
        row.put("FoodID", 3);
        row.put("Name", "Turtle Ice Cream Sandwich");
        row.put("Description", "Homemade exclusively for Tomavinos.");
        row.put("Price", (float) 6.00);
        table.put(1, row);

        Map<Integer, Map<String, Object>> Table = menu.getTable();
        for (Map.Entry<Integer, Map<String, Object>> entry : Table.entrySet()) {
            assertEquals(entry.getValue().get("FoodID"), table.get(entry.getKey()).get("FoodID"),
                    "Food ID does not match");
            assertEquals(entry.getValue().get("Name"), table.get(entry.getKey()).get("Name"),
                    "Name does not match");
            assertEquals(entry.getValue().get("Description"), table.get(entry.getKey()).get("Description"),
                    "Description does not match");
            assertEquals(entry.getValue().get("Price"), table.get(entry.getKey()).get("Price"),
                    "Price does not match");
        }
    }
}
