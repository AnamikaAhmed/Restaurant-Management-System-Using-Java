package restaurant.controller.fooddelivery;

import restaurant.model.fooddelivery.FoodCategoryModel;
import restaurant.model.fooddelivery.IFoodCategoryModel;
import restaurant.model.utilities.IDatabase;
import restaurant.model.utilities.SQLDatabase;
import restaurant.view.fooddelivery.FoodCategoryView;
import restaurant.view.fooddelivery.IFoodCategoryView;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class FoodCategoryTest {
    IFoodCategory category;
    IFoodCategoryView categoryView;
    IFoodCategoryModel categoryModel;
    IDatabase database;

    public FoodCategoryTest() {
        category = new FoodCategory();
        categoryView = new FoodCategoryView();
        categoryModel = new FoodCategoryModel();
        database = new SQLDatabase();
        database.connect("db-5308.cs.dal.ca:3306", "CSCI5308_1_TEST", "CSCI5308_1_TEST_USER", "Uu4mw8bk3qR");
    }

    @Test
    public void selectFoodCategoryTest() {
        categoryModel.getFoodCategories(1, category, database);

        InputStream backup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream("1".getBytes());
        System.setIn(in);
        category.selectFoodCategory(categoryView);
        System.setIn(backup);

        assertEquals(1, category.getCategoryID(), "Category ID does not match");
    }
}
