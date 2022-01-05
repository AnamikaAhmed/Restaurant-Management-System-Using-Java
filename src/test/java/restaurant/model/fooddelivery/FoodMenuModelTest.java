package restaurant.model.fooddelivery;

import restaurant.controller.fooddelivery.FoodMenu;
import restaurant.controller.fooddelivery.IFoodMenu;
import restaurant.model.utilities.IDatabase;
import org.junit.jupiter.api.Test;
import restaurant.model.utilities.SQLDatabase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

public class FoodMenuModelTest {
    IFoodMenu menu;
    IFoodMenuModel menuModel;
    IDatabase database;

    public FoodMenuModelTest() {
        menu = new FoodMenu();
        menuModel = new FoodMenuModel();
        database = new SQLDatabase();
        database.connect("db-5308.cs.dal.ca:3306", "CSCI5308_1_TEST", "CSCI5308_1_TEST_USER", "Uu4mw8bk3qR");
    }

    @Test
    public void getFoodMenuTest() {
        Map<Integer, Map<String, Object>> table = new HashMap<>();
        Map<String, Object> row = new HashMap<>();
        row.put("FoodID", 1);
        row.put("Name", "Pizza Con Carne 9\"");
        row.put("Description", "For meat lovers: tasty homemade meatballs, Italian sausage and pepperoni.");
        row.put("Price", (float) 12.00);
        table.put(1, row);

        row = new HashMap<>();
        row.put("FoodID", 2);
        row.put("Name", "Tomavinos famous Caesar Salad");
        row.put("Description", "");
        row.put("Price", (float) 10.00);
        table.put(2, row);

        row = new HashMap<>();
        row.put("FoodID", 3);
        row.put("Name", "Turtle Ice Cream Sandwich");
        row.put("Description", "Homemade exclusively for Tomavinos.");
        row.put("Price", (float) 6.00);
        table.put(3, row);

        menuModel.getFoodMenu(1, database, menu);
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

    @Test
    public void getFoodCategoryMenuTest() {
        Map<Integer, Map<String, Object>> table = new HashMap<>();
        Map<String, Object> row = new HashMap<>();
        row.put("FoodID", 5);
        row.put("Name", "Dal Soup");
        row.put("Description", "Lentils and spices, garnished with lemon.");
        row.put("Price", (float) 4.00);
        table.put(1, row);

        menuModel.getFoodMenu(2, database, menu);
        menuModel.getFoodCategoryMenu(2, 1, database, menu);
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

        menuModel.getFoodCategoryMenu(2, 2, database, menu);
        assertTrue(menu.getTable().isEmpty(), "Table is not empty");
    }
}
