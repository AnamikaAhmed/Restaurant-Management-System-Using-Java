package restaurant.view.fooddelivery;

import restaurant.controller.fooddelivery.FoodMenu;
import restaurant.controller.fooddelivery.IFood;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

public class FoodCategoryViewTest {
    IFood food;
    IFoodCategoryView categoryView;

    public FoodCategoryViewTest() {
        food = new FoodMenu();
        categoryView = new FoodCategoryView();
    }

    @Test
    public void displayFoodCategoriesTest() {
        Map<Integer, Map<String, Object>> categories = new HashMap<>();
        Map<String, Object> categoryItem = new HashMap<>();
        categoryItem.put("Name", "Starters");
        categories.put(1, categoryItem);
        categoryItem = new HashMap<>();
        categoryItem.put("Name", "Appetizers");
        categories.put(2, categoryItem);

        food.setTable(categories);
        categoryView.displayFoodCategories(food);

        int i = 1;
        for (Map.Entry<Integer, Map<String, Object>> entry : food.getTable().entrySet()) {
            assertEquals(i, entry.getKey(), "Id does not match");
            assertEquals(categories.get(i++).get("Name"), entry.getValue().get("Name"), "Name does not match");
        }
    }
}
