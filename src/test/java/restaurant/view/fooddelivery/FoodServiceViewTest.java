package restaurant.view.fooddelivery;

import restaurant.controller.fooddelivery.FoodMenu;
import restaurant.controller.fooddelivery.IFood;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class FoodServiceViewTest {
    IFood food;
    IFoodServiceView view;

    public FoodServiceViewTest() {
        food = new FoodMenu();
        view = new FoodServiceView();
    }

    @Test
    public void readFromConsoleTest() {
        InputStream backup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream("1".getBytes());
        System.setIn(in);
        assertEquals("1", view.readFromConsole(), "Input does not match");
        System.setIn(backup);
    }

    @Test
    public void displayFoodServiceTest() {
        Map<Integer, Map<String, Object>> service = new HashMap<>();
        Map<String, Object> serviceType = new HashMap<>();
        serviceType.put("Type", "Dine-In");
        service.put(1, serviceType);
        serviceType = new HashMap<>();
        serviceType.put("Type", "Take-Away");
        service.put(2, serviceType);
        serviceType = new HashMap<>();
        serviceType.put("Type", "Delivery");
        service.put(3, serviceType);

        food.setTable(service);
        view.displayFoodService(food);

        int i = 1;
        for (Map.Entry<Integer, Map<String, Object>> entry : food.getTable().entrySet()) {
            assertEquals(service.get(i++).get("Type"), entry.getValue().get("Type"), "Service does not match");
        }
    }
}
