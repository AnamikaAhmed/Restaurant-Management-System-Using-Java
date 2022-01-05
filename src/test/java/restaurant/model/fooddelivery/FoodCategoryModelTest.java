package restaurant.model.fooddelivery;

import restaurant.controller.fooddelivery.FoodCategory;
import restaurant.controller.fooddelivery.IFoodCategory;
import restaurant.model.utilities.IDatabase;
import org.junit.jupiter.api.Test;
import restaurant.model.utilities.SQLDatabase;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

public class FoodCategoryModelTest {
    IFoodCategory category;
    IFoodCategoryModel categoryModel;
    IDatabase database;

    public FoodCategoryModelTest() {
        category = new FoodCategory();
        categoryModel = new FoodCategoryModel();
        database = new SQLDatabase();
        database.connect("localhost", "restaurant_test", "root", "m@8roska");
    }

    @Test
    public void getFoodCategoriesTest() {
        Map<Integer, Map<String, Object>> table = new HashMap<>();
        Map<String, Object> row = new HashMap<>();
        row.put("CategoryID", 4);
        row.put("Name", "Beverages");
        table.put(1, row);

        row = new HashMap<>();
        row.put("CategoryID", 1);
        row.put("Name", "Appetizer");
        table.put(2, row);

        row = new HashMap<>();
        row.put("CategoryID", 3);
        row.put("Name", "Dessert");
        table.put(3, row);

        categoryModel.getFoodCategories(2, category, database);
        Map<Integer, Map<String, Object>> Table = category.getTable();
        for (Map.Entry<Integer, Map<String, Object>> entry : Table.entrySet()) {
            assertEquals(entry.getValue().get("CategoryID"), table.get(entry.getKey()).get("CategoryID"),
                    "Category ID does not match");
            assertEquals(entry.getValue().get("Name"), table.get(entry.getKey()).get("Name"),
                    "Name does not match");
        }
    }
}
