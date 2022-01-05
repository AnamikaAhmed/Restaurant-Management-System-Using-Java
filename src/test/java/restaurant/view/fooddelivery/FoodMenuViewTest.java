package restaurant.view.fooddelivery;

import restaurant.controller.fooddelivery.FoodMenu;
import restaurant.controller.fooddelivery.IFood;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class FoodMenuViewTest {
    IFood food;
    IFoodMenuView view;

    FoodMenuViewTest() {
        food = new FoodMenu();
        view = new FoodMenuView();
    }

    @Test
    public void readFromConsoleTest() {
        view = new FoodMenuView();
        InputStream backup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream("1".getBytes());
        System.setIn(in);
        assertEquals("1", view.readFromConsole(""), "Input does not match");
        System.setIn(backup);
    }

    @Test
    public void displayFoodMenuTest() {
        Map<Integer, Map<String, Object>> menu = new HashMap<>();
        Map<String, Object> menuItem = new HashMap<>();
        menuItem.put("Name", "Idli");
        menuItem.put("Description", "Sourdough Cake");
        menuItem.put("Price", (float) 1.00);
        menu.put(1, menuItem);

        food.setTable(menu);
        view.displayFoodMenu(food);

        for (Map.Entry<Integer, Map<String, Object>> entry : food.getTable().entrySet()) {
            assertEquals("Idli", entry.getValue().get("Name"), "Name does not match");
            assertEquals("Sourdough Cake", entry.getValue().get("Description"), "Description does not match");
            assertEquals((float) 1.00, entry.getValue().get("Price"), "Price does not match");
        }
    }
}
